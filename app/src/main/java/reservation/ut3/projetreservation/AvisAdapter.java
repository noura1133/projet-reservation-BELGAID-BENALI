package reservation.ut3.projetreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AvisAdapter extends RecyclerView.Adapter<AvisAdapter.AvisViewHolder> {

    private Context context;
    private List<String> avisList;

    public AvisAdapter(Context context, List<String> avisList) {
        this.context = context;
        this.avisList = avisList;
    }

    @NonNull
    @Override
    public AvisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_avis, parent, false);
        return new AvisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvisViewHolder holder, int position) {
        String avis = avisList.get(position);
        holder.txtAvis.setText(avis);
    }

    @Override
    public int getItemCount() {
        return avisList.size();
    }

    public static class AvisViewHolder extends RecyclerView.ViewHolder {
        TextView txtAvis;

        public AvisViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAvis = itemView.findViewById(R.id.txt_avis);
        }
    }
}