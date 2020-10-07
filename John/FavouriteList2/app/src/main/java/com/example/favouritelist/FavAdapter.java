package com.example.favouritelist;

import android.content.Context;
import android.service.controls.templates.ControlTemplate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context context;
    private List<FavPlayer> favPlayerList;
    private FavDB favDB;

    public FavAdapter(Context context, List<FavPlayer> favPlayerList) {
        this.context = context;
        this.favPlayerList = favPlayerList;
    }


    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_player,
                parent, false);
        favDB = new FavDB(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.favTextView.setText(favPlayerList.get(position).getPlayer_title());
        holder.favImageView.setImageResource(favPlayerList.get(position).getItem_image());

    }

    @Override
    public int getItemCount() {
        return favPlayerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView favTextView;
        Button favBtn;
        ImageView favImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favTextView = itemView.findViewById(R.id.favTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            favImageView = itemView.findViewById(R.id.favImageView);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavPlayer favPlayer = favPlayerList.get(position);
                    favDB.remove_fav(favPlayer.getKey_id());
                    removePlayer(position);
                }
            });
        }
    }

    private void removePlayer(int position) {
        favPlayerList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,favPlayerList.size());
    }
}
