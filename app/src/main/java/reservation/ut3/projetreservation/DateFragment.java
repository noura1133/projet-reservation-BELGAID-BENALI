package reservation.ut3.projetreservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFragment extends Fragment {

    private TextView textViewDate;
    private CalendarView calendarView;

    public DateFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Lie le fichier XML du fragment
        View rootView = inflater.inflate(R.layout.fragment_date, container, false);

        // Initialise les vues
        textViewDate = rootView.findViewById(R.id.textViewDate);
        calendarView = rootView.findViewById(R.id.calendarView);

        // Configure l'écouteur de sélection de date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Affiche la date sélectionnée dans le TextView
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date selectedDate = new Date(view.getDate());
                textViewDate.setText(getString(R.string.selected_date, sdf.format(selectedDate)));
            }
        });

        return rootView;
    }
}
