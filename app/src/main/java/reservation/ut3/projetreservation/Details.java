package reservation.ut3.projetreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    private ImageView image_resto;
    private TextView nom_resto ;
    private RecyclerView photoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //image_resto = findViewById(R.id.image_resto);
        //nom_resto = findViewById(R.id.nom_resto);

        photoRecyclerView = findViewById(R.id.photoRecyclerView);

        // Configurez le LinearLayoutManager horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoRecyclerView.setLayoutManager(layoutManager);

        // Créez et configurez votre adaptateur pour la liste de photos (à implémenter)
        PhotoAdapter photoAdapter = new PhotoAdapter(this);
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


    }
}