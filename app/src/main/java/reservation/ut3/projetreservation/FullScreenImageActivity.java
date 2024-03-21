package reservation.ut3.projetreservation;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_viewer);

        // Récupérer l'URI de l'image à afficher en plein écran depuis l'intent
        Uri imageUri = getIntent().getParcelableExtra("imageUri");

        // Vérifier si l'URI est nul
        if (imageUri != null) {
            // Trouver l'ImageView dans le layout
            ImageView fullScreenImageView = findViewById(R.id.imageViewFullScreen);

            // Charger l'image dans l'ImageView en utilisant Picasso ou toute autre bibliothèque de votre choix
            Picasso.get().load(imageUri).into(fullScreenImageView);
        } else {
            // Afficher un message d'erreur ou effectuer une action appropriée si l'URI est nul
            Log.e("FullScreenImageActivity", "L'URI de l'image est nul");
            Toast.makeText(this, "URI de l'image est nul", Toast.LENGTH_SHORT).show();
        }
    }
}
