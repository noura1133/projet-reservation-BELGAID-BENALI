package reservation.ut3.projetreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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


    }
}