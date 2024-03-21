package reservation.ut3.projetreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Details extends AppCompatActivity {

    FirebaseDatabaseHelper firebaseDatabaseHelper = new FirebaseDatabaseHelper();

    private Button  btnTest;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btnTest = findViewById(R.id.btn_test);

        btnReserver = findViewById(R.id.btn_reserver);

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

            // Utiliser Picasso pour charger l'image depuis l'URL dans imageRef
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoRecyclerView.setLayoutManager(layoutManager);


        photoAdapter = new PhotoAdapter(this, imageDetailsList);
        photoRecyclerView.setAdapter(photoAdapter);

        LinearLayoutManager avisLayoutManager = new LinearLayoutManager(this);
        avisRecyclerView.setLayoutManager(avisLayoutManager);


        avisList.add(fullAvisList.get(0));

        // Ajoutez d'autres avis si nécessaire
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

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvisModel nouvelAvisTest = new AvisModel("Auteur de test", "Description de test", "URL de test");
                String restaurantId = clickedRestaurant.getId();

                int nombreAvis = fullAvisList.size() + 1;
                String nouvelAvisKey = String.format("%02d", nombreAvis);

                firebaseDatabaseHelper.ajouterAvis(restaurantId,nouvelAvisKey,nouvelAvisTest, new FirebaseDatabaseHelper.UpdateCallback() {
                    @Override
                    public void onUpdateSuccess() {
                        Log.d("FirebaseDatabaseHelper", "Avis de test ajouté avec succès.");
                    }
                    @Override
                    public void onUpdateFailed(String errorMessage) {
                        Log.e("FirebaseDatabaseHelper", "Erreur lors de l'ajout de l'avis de test : " + errorMessage);
                    }
                });
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



}