package reservation.ut3.projetreservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFragment extends Fragment {

    private TextView textViewSelectDate;
    private CalendarView calendarView;
    private Button buttonNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_date, container, false);

        textViewSelectDate = rootView.findViewById(R.id.textViewSelectDate);
        calendarView = rootView.findViewById(R.id.calendarView);
        buttonNext = rootView.findViewById(R.id.buttonNext);

        // Obtenir la date actuelle
        Calendar currentDate = Calendar.getInstance();
        long currentTimeInMillis = currentDate.getTimeInMillis();

        // Définir la date minimale du CalendarView sur la date actuelle
        calendarView.setMinDate(currentTimeInMillis);


        // Afficher la date actuelle dans le TextView
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = sdf.format(currentDate.getTime());
        textViewSelectDate.setText(getString(R.string.selected_date, selectedDate));

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Empêcher la sélection de dates antérieures à la date actuelle
            Calendar selectedDateCalendar = Calendar.getInstance();
            selectedDateCalendar.set(year, month, dayOfMonth);
            long selectedTimeInMillis = selectedDateCalendar.getTimeInMillis();
            if (selectedTimeInMillis < currentTimeInMillis) {
                // Si la date sélectionnée est antérieure à la date actuelle, réinitialiser le CalendarView à la date actuelle
                calendarView.setDate(currentTimeInMillis);
            } else {
                // Afficher la date sélectionnée dans le TextView
                String selectedDateString = sdf.format(selectedDateCalendar.getTime());
                textViewSelectDate.setText(getString(R.string.selected_date, selectedDateString));
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passer au fragment suivant
                ViewPager2 viewPager = getActivity().findViewById(R.id.viewPager2);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        return rootView;
    }
}