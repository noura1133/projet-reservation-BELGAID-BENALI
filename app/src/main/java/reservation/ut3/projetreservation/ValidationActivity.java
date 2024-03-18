package reservation.ut3.projetreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import reservation.ut3.projetreservation.R;


public class ValidationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        // Trouver le bouton de retour dans le layout
        Button buttonAccueil = findViewById(R.id.buttonAccueil);

        // Définir un écouteur de clic pour le bouton de retour
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValidationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
