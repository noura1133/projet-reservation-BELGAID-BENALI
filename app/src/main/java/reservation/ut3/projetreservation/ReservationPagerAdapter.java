package reservation.ut3.projetreservation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ReservationPagerAdapter extends FragmentStateAdapter {
    public ReservationPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DateFragment(); // Fragment de la date
            // Ajoute d'autres fragments pour les autres onglets ici si nécessaire
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 1; // Nombre total d'onglets
    }
}
