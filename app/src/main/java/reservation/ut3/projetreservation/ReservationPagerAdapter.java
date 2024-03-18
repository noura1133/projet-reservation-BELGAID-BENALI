package reservation.ut3.projetreservation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReservationPagerAdapter extends FragmentStateAdapter {
    public ReservationPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DateFragment();
            case 1:
                return new TimeFragment();
            case 2:
                return new PersonneFragment();
            case 3:
                return new InformationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
