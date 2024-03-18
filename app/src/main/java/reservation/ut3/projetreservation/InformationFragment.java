package reservation.ut3.projetreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InformationFragment extends Fragment {

    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextEmail;
    private EditText editTextTelephone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_informations, container, false);

        editTextNom = rootView.findViewById(R.id.editTextNom);
        editTextPrenom = rootView.findViewById(R.id.editTextPrenom);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        editTextTelephone = rootView.findViewById(R.id.editTextTelephone);

        // Ajouter un bouton de validation et définir un OnClickListener pour récupérer les valeurs saisies
        Button buttonValider = rootView.findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour démarrer l'activité de validation
                Intent intent = new Intent(requireContext(), ValidationActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}
