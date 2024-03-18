package reservation.ut3.projetreservation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{

    public void setListener(OnRestaurantClickListener listener) {
        this.listener = listener;
    }

    public interface OnRestaurantClickListener {
        void onRestaurantClick(int position);
    }


    private List<RestaurantModel> restaurantList;
    private OnRestaurantClickListener listener;

    public RestaurantAdapter(List<RestaurantModel> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RestaurantModel restaurantModel = restaurantList.get(position);
        holder.nameTextView.setText(restaurantModel.getNom());
        holder.addressTextView.setText(restaurantModel.getAdresse());
        //holder.imageView.setImageResource(restaurantModel.getImageRef());
        // Utiliser Picasso pour charger l'image depuis l'URL dans imageRef
        if (restaurantModel.getImageRef() != null) {
            Picasso.get().load(restaurantModel.getImageRef()).into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRestaurantClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView addressTextView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
