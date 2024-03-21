package reservation.ut3.projetreservation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements RestaurantAdapter.OnRestaurantClickListener{

    private FirebaseDatabaseHelper firebaseDatabaseHelper;
    private RestaurantAdapter restaurantAdapter;
    private List<RestaurantModel> restaurantList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        restaurantList = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(restaurantList);
        restaurantAdapter.setListener(this);
        recyclerView.setAdapter(restaurantAdapter);

        retrieveRestaurantsFromFirebase();
    }

    private void retrieveRestaurantsFromFirebase() {
        firebaseDatabaseHelper.retrieveRestaurants(new FirebaseDatabaseHelper.DataCallback<List<RestaurantModel>>() {
            @Override
            public void onDataFetched(List<RestaurantModel> data) {
                restaurantList.clear();
                restaurantList.addAll(data);
                restaurantAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onRestaurantClick(int position) {

        RestaurantModel clickedRestaurant = restaurantList.get(position);
        Intent intent = new Intent(this, Details.class);
        intent.putExtra("restaurant", clickedRestaurant);
        startActivity(intent);

    }
}


