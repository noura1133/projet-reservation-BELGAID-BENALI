package reservation.ut3.projetreservation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseDatabaseHelper {
    private DatabaseReference mDatabase;

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance("https://projetreservation-36463-default-rtdb.firebaseio.com/").getReference("restaurants");
    }

    public void addRestaurant(String id, String nom, String adresse, String type, String prixMoyen, String moyensPaiement, String services, String caracteristiques, String transports, List<AvisModel> avisResto, String imageRef, String imagesDetails){
        RestaurantModel restaurant = new RestaurantModel(id, nom, adresse, type, prixMoyen, moyensPaiement, services, caracteristiques, transports, avisResto, imageRef, imagesDetails);
        mDatabase.child("restaurants").child(id).setValue(restaurant);
    }


    public void retrieveRestaurants(final DataCallback<List<RestaurantModel>> callback) {
        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    List<RestaurantModel> restaurantList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                        RestaurantModel restaurant = parseRestaurant(dataSnapshot);
                        if (restaurant != null) {
                            restaurantList.add(restaurant);
                        }
                    }
                    callback.onDataFetched(restaurantList);
                } else {
                    Log.e("FirebaseDatabaseHelper", "Erreur lors de la récupération des restaurants : " + task.getException());
                }
            }
        });
    }

    private RestaurantModel parseRestaurant(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        String id = dataSnapshot.child("id").getValue(String.class);
        String nom = dataSnapshot.child("nom").getValue(String.class);
        String adresse = dataSnapshot.child("adresse").getValue(String.class);
        String type = dataSnapshot.child("type").getValue(String.class);
        String prixMoyen = dataSnapshot.child("prixMoyen").getValue(String.class);
        String moyensPaiement = dataSnapshot.child("moyensPaiement").getValue(String.class);
        String services = dataSnapshot.child("services").getValue(String.class);
        String caracteristiques = dataSnapshot.child("caracteristiques").getValue(String.class);
        String transports = dataSnapshot.child("transports").getValue(String.class);
        String imageRef = dataSnapshot.child("imageRef").getValue(String.class);
        String imagesDetails = dataSnapshot.child("imagesDetails").getValue(String.class);

        List<AvisModel> avisList = parseAvisList(dataSnapshot.child("avisResto"));

        return new RestaurantModel(id,nom, adresse, type, prixMoyen, moyensPaiement, services,
                caracteristiques, transports, avisList, imageRef, imagesDetails);
    }

    private List<AvisModel> parseAvisList(DataSnapshot avisSnapshot) {
        List<AvisModel> avisList = new ArrayList<>();
        for (DataSnapshot avisDataSnapshot : avisSnapshot.getChildren()) {
            String avisNom = avisDataSnapshot.child("nom").getValue(String.class);
            String avisDescription = avisDataSnapshot.child("description").getValue(String.class);
            String avisPhoto = avisDataSnapshot.child("photo").getValue(String.class);
            avisList.add(new AvisModel(avisNom, avisDescription, avisPhoto));
        }
        return avisList;
    }

    public void ajouterAvis(String restaurantId ,String avisId, AvisModel nouvelAvis, final UpdateCallback callback) {

        DatabaseReference avisRef = mDatabase.child(restaurantId).child("avisResto");
        Map<String, Object> nouvelAvisValues = nouvelAvis.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + avisId, nouvelAvisValues);

        avisRef.updateChildren(childUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onUpdateSuccess();
                        } else {
                            callback.onUpdateFailed(task.getException().getMessage());
                        }
                    }
                });
    }

    // Interface pour le callback
    public interface UpdateCallback {
        void onUpdateSuccess();
        void onUpdateFailed(String errorMessage);
    }








    public interface DataCallback<T> {
        void onDataFetched(T data);
    }



}
