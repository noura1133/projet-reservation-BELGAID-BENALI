package reservation.ut3.projetreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RestaurantAdapter.OnRestaurantClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<RestaurantModel> restaurantList = generateRestaurantList(); // Méthode pour générer des données de test
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurantList);

        restaurantAdapter.setListener(this);
        recyclerView.setAdapter(restaurantAdapter);
    }

    private List<RestaurantModel> generateRestaurantList() {
        List<RestaurantModel> restaurantList = new ArrayList<>();
        // Ajoute des exemples de restaurants à la liste
        restaurantList.add(new RestaurantModel("Chez Rozine", "123 Main St", R.drawable.restaurant_default_image));
        restaurantList.add(new RestaurantModel("Restaurant B", "456 Oak St", R.drawable.restaurant_default_image));
        restaurantList.add(new RestaurantModel("Restaurant C", "456 Oak St", R.drawable.restaurant_default_image));
        restaurantList.add(new RestaurantModel("Restaurant D", "456 Oak St", R.drawable.restaurant_default_image));

        // Ajoute d'autres restaurants selon tes besoins
        return restaurantList;
    }


    @Override
    public void onRestaurantClick(int position) {
        RestaurantModel clickedRestaurant = generateRestaurantList().get(position);
        Intent intent = new Intent(this, Details.class);
        startActivity(intent);
    }
}