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

        Calendar currentDate = Calendar.getInstance();
        long currentTimeInMillis = currentDate.getTimeInMillis();

        calendarView.setMinDate(currentTimeInMillis);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = sdf.format(currentDate.getTime());
        textViewSelectDate.setText(getString(R.string.selected_date, selectedDate));

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar selectedDateCalendar = Calendar.getInstance();
            selectedDateCalendar.set(year, month, dayOfMonth);
            long selectedTimeInMillis = selectedDateCalendar.getTimeInMillis();
            if (selectedTimeInMillis < currentTimeInMillis) {
                calendarView.setDate(currentTimeInMillis);
            } else {
                String selectedDateString = sdf.format(selectedDateCalendar.getTime());
                textViewSelectDate.setText(getString(R.string.selected_date, selectedDateString));
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager2 viewPager = getActivity().findViewById(R.id.viewPager2);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        return rootView;
    }
}