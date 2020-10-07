package com.example.favouritelist;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private ArrayList<PlayerItem> playerItems;
    private Context context;
    private FavDB favDB;

    public PlayerAdapter(ArrayList<PlayerItem> playerItems, Context context) {
        this.playerItems = playerItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB   = new FavDB(context);
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        final PlayerItem playerItem = playerItems.get(position);

        readCursorData(playerItem, holder);
        holder.imageView.setImageResource(playerItem.getImageResource());
        holder.titleTextView.setText(playerItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return playerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.favBtn);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    PlayerItem playerItem = playerItems.get(position);

                    if (playerItem.getFavStatus().equals("0")) {
                        playerItem.setFavStatus("1");
                        favDB.insertIntoTheDatabase(playerItem.getTitle(), playerItem.getImageResource(),
                                playerItem.getKey_id(), playerItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_star_shadow_24dp);
                    } else {
                        playerItem.setFavStatus("0");
                        favDB.remove_fav(playerItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_star_outline_24dp);
                    }
                }
            });

        }
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(PlayerItem playerItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(playerItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                playerItem.setFavStatus(item_fav_status);

                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_star_shadow_24dp);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_star_outline_24dp);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }


    }

}
