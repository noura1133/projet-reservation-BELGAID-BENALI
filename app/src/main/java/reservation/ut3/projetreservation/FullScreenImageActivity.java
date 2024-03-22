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

        Uri imageUri = getIntent().getParcelableExtra("imageUri");

        if (imageUri != null) {
            ImageView fullScreenImageView = findViewById(R.id.imageViewFullScreen);

            Picasso.get().load(imageUri).into(fullScreenImageView);
        } else {
            Log.e("FullScreenImageActivity", "L'URI de l'image est nul");
            Toast.makeText(this, "URI de l'image est nul", Toast.LENGTH_SHORT).show();
        }
    }
}
