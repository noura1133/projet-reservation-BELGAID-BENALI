package reservation.ut3.projetreservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class PersonneFragment extends Fragment {

    private Button nextButton;
    private Button lastSelectedButton; // Garder une référence au dernier bouton sélectionné

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personne, container, false);

        LinearLayout buttonsContainer = rootView.findViewById(R.id.buttonsContainer);
        nextButton = rootView.findViewById(R.id.nextButton);

        // Générer les boutons pour choisir le nombre de personnes
        generateNumberButtons(buttonsContainer);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passer au fragment suivant
                ViewPager2 viewPager = getActivity().findViewById(R.id.viewPager2);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        return rootView;
    }

    private void generateNumberButtons(LinearLayout layout) {
        int buttonsPerRow = 4; // Nombre de boutons par ligne
        int maxPeople = 10; // Nombre maximal de personnes

        for (int i = 1; i <= maxPeople; i++) {
            // Créer un nouveau LinearLayout horizontal si nécessaire
            if ((i - 1) % buttonsPerRow == 0) {
                LinearLayout rowLayout = new LinearLayout(requireContext());
                rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                layout.addView(rowLayout);
            }

            Button button = new Button(requireContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, // Width set to 0
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1 // Weight 1 (pour que chaque bouton prenne autant de place que possible dans la ligne)
            );
            params.width = getResources().getDisplayMetrics().widthPixels / buttonsPerRow; // Définir la largeur en fonction de l'écran
            params.setMargins(8, 8, 8, 8); // Ajouter des marges pour espacer les boutons
            button.setLayoutParams(params);
            button.setText(String.valueOf(i));
            int finalI = i; // Déclaration finale de l'index
            button.setOnClickListener(v -> {
                // Désélectionner le dernier bouton sélectionné
                if (lastSelectedButton != null && lastSelectedButton != button) {
                    int defaultColor = ContextCompat.getColor(requireContext(), R.color.button_default_color);
                    lastSelectedButton.setBackgroundColor(defaultColor);
                }
                // Conserver une référence au dernier bouton sélectionné
                lastSelectedButton = button;
                // Gérer la sélection du nombre de personnes ici
                handleNumberOfPeopleSelection(finalI);
                // Changer la couleur du bouton sélectionné en vert
                int color = ContextCompat.getColor(requireContext(), R.color.dark_green);
                button.setBackgroundColor(color);
            });

            // Définir la couleur par défaut du bouton
            int defaultColor = ContextCompat.getColor(requireContext(), R.color.button_default_color);
            button.setBackgroundColor(defaultColor);

            // Trouver le LinearLayout horizontal actuel et ajouter le bouton
            LinearLayout rowLayout = (LinearLayout) layout.getChildAt(layout.getChildCount() - 1);
            if (rowLayout != null) {
                rowLayout.addView(button);
            }
        }
    }



    private void handleNumberOfPeopleSelection(int selectedNumber) {
        // Faire quelque chose avec le nombre de personnes sélectionné
        Toast.makeText(requireContext(), "Nombre de personnes sélectionné : " + selectedNumber, Toast.LENGTH_SHORT).show();
    }
}
