package reservation.ut3.projetreservation;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class TimeFragment extends Fragment {

    private LinearLayout lunchButtonContainer;
    private LinearLayout dinnerButtonContainer;
    private Button nextButton;

    private final String[] lunchTimes = {"12h", "12h30", "13h", "13h30", "14h"};
    private final String[] dinnerTimes = {"18h", "18h30", "19h", "19h30", "20h", "20h30", "21h", "21h30", "22h"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);

        lunchButtonContainer = rootView.findViewById(R.id.lunchButtonContainer);
        dinnerButtonContainer = rootView.findViewById(R.id.dinnerButtonContainer);
        nextButton = rootView.findViewById(R.id.nextButton);

        generateTimeButtons(lunchButtonContainer, lunchTimes);

        generateTimeButtons(dinnerButtonContainer, dinnerTimes);

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

    private Button lastSelectedButton;

    private void generateTimeButtons(LinearLayout layout, String[] times) {
        int buttonsPerRow = 4;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        for (int i = 0; i < times.length; i++) {
            if (i % buttonsPerRow == 0) {
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
                    screenWidth / buttonsPerRow,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 8, 8, 8);
            button.setLayoutParams(params);
            button.setText(times[i]);
            int finalI = i;
            button.setOnClickListener(v -> {
                if (lastSelectedButton != null && lastSelectedButton != button) {
                    int defaultColor = ContextCompat.getColor(requireContext(), R.color.button_default_color);
                    lastSelectedButton.setBackgroundColor(defaultColor);
                }
                lastSelectedButton = button;
                handleTimeSelection(times[finalI]);
                int color = ContextCompat.getColor(requireContext(), R.color.dark_green);
                button.setBackgroundColor(color);
            });

            int defaultColor = ContextCompat.getColor(requireContext(), R.color.button_default_color);
            button.setBackgroundColor(defaultColor);

            LinearLayout rowLayout = (LinearLayout) layout.getChildAt(layout.getChildCount() - 1);
            if (rowLayout != null) {
                rowLayout.addView(button);
            }
        }
    }



    private void handleTimeSelection(String selectedTime) {
        // Faire quelque chose avec l'heure sélectionnée
        Toast.makeText(requireContext(), "Heure sélectionnée : " + selectedTime, Toast.LENGTH_SHORT).show();
    }
}

