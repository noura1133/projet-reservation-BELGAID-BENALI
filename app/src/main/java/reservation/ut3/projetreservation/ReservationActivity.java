package reservation.ut3.projetreservation;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ReservationActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ReservationPagerAdapter reservationPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewPager2);

        tabLayout.addTab(tabLayout.newTab().setText("Date"));
        tabLayout.addTab(tabLayout.newTab().setText("Heure"));
        tabLayout.addTab(tabLayout.newTab().setText("Personnes"));
        tabLayout.addTab(tabLayout.newTab().setText("Infos"));

        FragmentManager fm = getSupportFragmentManager();

        reservationPagerAdapter = new ReservationPagerAdapter(fm, getLifecycle());

        viewPager2.setAdapter(reservationPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}
