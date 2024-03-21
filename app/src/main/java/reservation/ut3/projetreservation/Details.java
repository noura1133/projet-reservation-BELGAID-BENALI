package reservation.ut3.projetreservation;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class Details extends AppCompatActivity {

    private TextView nom_resto ;
    private TextView add_resto;
    private TextView type_resto;
    private TextView prix_resto;
    private TextView moyens_paiement;
    private TextView services;
    private TextView caracteristiques;
    private TextView transports;


    private TextView txt_afficher_plus_avis ;
    private List<String> fullAvisList = new ArrayList<>();
    private List<String> avisList;
    private boolean areAvisExpanded  = false;
    private RecyclerView avisRecyclerView;
    private AvisAdapter avisAdapter ;

    private List<String> imageDetailsList = new ArrayList<>();
    private RecyclerView photoRecyclerView;
    PhotoAdapter photoAdapter;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ActivityResultLauncher<Intent> takePictureLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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
            // Mettez à jour les vues avec les détails du restaurant
            nom_resto.setText(clickedRestaurant.getNom());
            add_resto.setText(clickedRestaurant.getAdresse());
            type_resto.setText(clickedRestaurant.getType());
            prix_resto.setText(clickedRestaurant.getPrixMoyen());
            moyens_paiement.setText(clickedRestaurant.getMoyensPaiement());
            services.setText(clickedRestaurant.getServices());
            caracteristiques.setText(clickedRestaurant.getCaracteristiques());
            transports.setText(clickedRestaurant.getTransports());

            fullAvisList = convertStringToList(clickedRestaurant.getAvis());
            imageDetailsList = convertStringToList(clickedRestaurant.getImagesDetails());

            // Utiliser Picasso pour charger l'image depuis l'URL dans imageRef
        }

        // Configurez le LinearLayoutManager horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoRecyclerView.setLayoutManager(layoutManager);

        // Créez et configurez votre adaptateur pour la liste de photos (à implémenter)
        photoAdapter = new PhotoAdapter(this, imageDetailsList);
        photoRecyclerView.setAdapter(photoAdapter);

        Button btnReserver = findViewById(R.id.btn_reserver);

        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque le bouton est cliqué, ouvrir ReservationActivity
                Intent intent = new Intent(Details.this, ReservationActivity.class);
                startActivity(intent);
            }
        });

        Button btntakePhoto = findViewById(R.id.btn_take_photo);
        btntakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir la caméra lorsque le bouton est cliqué
                dispatchTakePictureIntent();
            }
        });

        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Récupérer l'URI de l'image capturée depuis les données de résultat
                        Intent data = result.getData();
                        if (data != null) {
                            Bundle extras = data.getExtras();
                            if (extras != null) {
                                // Obtenir l'image capturée en tant qu'objet Bitmap
                                Bitmap imageBitmap = (Bitmap) extras.get("data");
                                // Charger l'image capturée dans ton ImageView
                                ImageView imageViewCaptured = findViewById(R.id.imageViewCaptured);
                                imageViewCaptured.setImageBitmap(imageBitmap);
                                // Rendre l'ImageView visible maintenant que l'image est chargée
                                imageViewCaptured.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        Log.d("DetailsActivity", "Résultat annulé ou non OK: " + result.getResultCode());
                    }
                });



        // Créez et configurez votre adaptateur pour la liste des avis
        LinearLayoutManager avisLayoutManager = new LinearLayoutManager(this);
        avisRecyclerView.setLayoutManager(avisLayoutManager);

        avisList = new ArrayList<>();
        avisList.add(fullAvisList.get(0));

        // Ajoutez d'autres avis si nécessaire
        avisAdapter = new AvisAdapter(this, avisList);
        avisRecyclerView.setAdapter(avisAdapter);

        txt_afficher_plus_avis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifier l'état actuel de l'affichage des avis
                if (areAvisExpanded) {
                    // Afficher moins d'avis
                    avisList.clear(); // Supprimer tous les avis
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