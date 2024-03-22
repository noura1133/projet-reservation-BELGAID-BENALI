package reservation.ut3.projetreservation;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {

    FirebaseDatabaseHelper firebaseDatabaseHelper = new FirebaseDatabaseHelper();

    private EditText editTextNomAvis ;
    private EditText editTextDescriptionAvis ;

    private Button  btn_ajouter_avis;

    private Button btnReserver ;

    private TextView nom_resto ;
    private TextView add_resto;
    private TextView type_resto;
    private TextView prix_resto;
    private TextView moyens_paiement;
    private TextView services;
    private TextView caracteristiques;
    private TextView transports;


    private TextView txt_afficher_plus_avis ;
    private List<AvisModel> fullAvisList = new ArrayList<>();
    private List<AvisModel> avisList = new ArrayList<>();
    private boolean areAvisExpanded  = false;
    private RecyclerView avisRecyclerView;
    private AvisAdapter avisAdapter ;

    private List<String> imageDetailsList = new ArrayList<>();
    private RecyclerView photoRecyclerView;
    PhotoAdapter photoAdapter;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ActivityResultLauncher<Intent> takePictureLauncher;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btn_ajouter_avis = findViewById(R.id.btn_ajouter_avis);
        btnReserver = findViewById(R.id.btn_reserver);

        editTextNomAvis = findViewById(R.id.editTextNomAvis);
        editTextDescriptionAvis = findViewById(R.id.editTextDescriptionAvis);

        nom_resto = findViewById(R.id.nom_resto);
        add_resto = findViewById(R.id.add_resto);
        type_resto = findViewById(R.id.type_resto);
        prix_resto = findViewById(R.id.prix_resto);
        moyens_paiement = findViewById(R.id.moyens_paiement);
        services = findViewById(R.id.services);
        caracteristiques = findViewById(R.id.caracteristiques);
        transports = findViewById(R.id.transports);

        avisRecyclerView = findViewById(R.id.avisRecyclerView);
        txt_afficher_plus_avis = findViewById(R.id.txt_afficher_plus_avis);

        photoRecyclerView = findViewById(R.id.photoRecyclerView);

        RestaurantModel clickedRestaurant = (RestaurantModel) getIntent().getSerializableExtra("restaurant");
        if (clickedRestaurant != null) {
            nom_resto.setText(clickedRestaurant.getNom());
            add_resto.setText(clickedRestaurant.getAdresse());
            type_resto.setText(clickedRestaurant.getType());
            prix_resto.setText(clickedRestaurant.getPrixMoyen());
            moyens_paiement.setText(clickedRestaurant.getMoyensPaiement());
            services.setText(clickedRestaurant.getServices());
            caracteristiques.setText(clickedRestaurant.getCaracteristiques());
            transports.setText(clickedRestaurant.getTransports());

            fullAvisList = clickedRestaurant.getAvisResto();
            imageDetailsList = convertStringToList(clickedRestaurant.getImagesDetails());
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoRecyclerView.setLayoutManager(layoutManager);


        photoAdapter = new PhotoAdapter(this, imageDetailsList);
        photoRecyclerView.setAdapter(photoAdapter);


        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, ReservationActivity.class);
                startActivity(intent);
            }
        });

        Button btntakePhoto = findViewById(R.id.btn_take_photo);
        btntakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Bundle extras = data.getExtras();
                            if (extras != null) {
                                Bitmap imageBitmap = (Bitmap) extras.get("data");
                                ImageView imageViewCaptured = findViewById(R.id.imageViewCaptured);
                                imageViewCaptured.setImageBitmap(imageBitmap);
                                imageViewCaptured.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        Log.d("DetailsActivity", "Résultat annulé ou non OK: " + result.getResultCode());
                    }
                });


        LinearLayoutManager avisLayoutManager = new LinearLayoutManager(this);
        avisRecyclerView.setLayoutManager(avisLayoutManager);


        avisList.add(fullAvisList.get(0));
        avisAdapter = new AvisAdapter(this, avisList);
        avisRecyclerView.setAdapter(avisAdapter);

        txt_afficher_plus_avis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (areAvisExpanded) {
                        avisList.clear();
                        avisList.add(fullAvisList.get(0));
                        avisAdapter.notifyDataSetChanged();
                        ViewGroup.LayoutParams params = avisRecyclerView.getLayoutParams();
                        params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
                        avisRecyclerView.setLayoutParams(params);
                        txt_afficher_plus_avis.setText("Afficher plus d'avis");
                        areAvisExpanded = false;
                    } else {
                        for (int i = 1; i < fullAvisList.size(); i++) {
                            avisList.add(fullAvisList.get(i));
                        }
                        avisAdapter.notifyDataSetChanged();
                        ViewGroup.LayoutParams params = avisRecyclerView.getLayoutParams();
                        params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
                        avisRecyclerView.setLayoutParams(params);
                        txt_afficher_plus_avis.setText("Afficher moins d'avis");
                        areAvisExpanded = true;
                    }
                }
        });

        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, ReservationActivity.class);
                startActivity(intent);
            }
        });

        btn_ajouter_avis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomAuteur = editTextNomAvis.getText().toString().trim();
                String description = editTextDescriptionAvis.getText().toString().trim();
                String url = "URL de test";

                if (!nomAuteur.isEmpty() && !description.isEmpty()) {
                    AvisModel nouvelAvis = new AvisModel(nomAuteur, description, url);
                    String restaurantId = clickedRestaurant.getId();

                    int nombreAvis = fullAvisList.size() + 1;
                    String nouvelAvisKey = String.format("%02d", nombreAvis);

                    firebaseDatabaseHelper.ajouterAvis(restaurantId, nouvelAvisKey, nouvelAvis, new FirebaseDatabaseHelper.UpdateCallback() {
                        @Override
                        public void onUpdateSuccess() {
                            Toast.makeText(Details.this, "Avis enregistré avec succès !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Details.this, Details.class);
                            intent.putExtra("restaurant", clickedRestaurant);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onUpdateFailed(String errorMessage) {
                            Log.e("FirebaseDatabaseHelper", "Erreur lors de l'ajout de l'avis : " + errorMessage);
                            Toast.makeText(Details.this, "Erreur lors de l'enregistrement de l'avis.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(Details.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private List<String> convertStringToList(String firebaseString) {
        List<String> contentList = new ArrayList<>();
        if (firebaseString != null && !firebaseString.isEmpty()) {
            String[] contentArray = firebaseString.split(",");
            for (String content : contentArray) {
                contentList.add(content.trim());
            }
        }
        return contentList;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureLauncher.launch(takePictureIntent);
        }
    }
}