package reservation.ut3.projetreservation;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private DatabaseReference mDatabase;

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addRestaurant(String id, String nom, String adresse, String type, String prixMoyen, String moyensPaiement, String services, String caracteristiques, String transports, String avis) {
        RestaurantModel restaurant = new RestaurantModel(id, nom, adresse, type, prixMoyen, moyensPaiement, services, caracteristiques, transports, avis);
        mDatabase.child("restaurants").child(id).setValue(restaurant);
    }

    public void retrieveRestaurants(final DataCallback<List<RestaurantModel>> callback) {
        DatabaseReference restaurantsRef = mDatabase.child("restaurants");
        restaurantsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<RestaurantModel> restaurantList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    RestaurantModel restaurant = snapshot.getValue(RestaurantModel.class);
                    if (restaurant != null) {
                        restaurantList.add(restaurant);
                    }
                }
                callback.onSuccess(restaurantList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure(databaseError.toException());
            }
        });
    }

    public interface DataCallback<T> {
        void onSuccess(T data);
        void onFailure(Exception exception);
    }



}
