package reservation.ut3.projetreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private int[] photos = { R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4  };

    public PhotoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.photoImageView.setImageResource(photos[position]);
    }

    @Override
    public int getItemCount() {
        return photos.length;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }
    }
}