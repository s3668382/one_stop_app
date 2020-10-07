package com.example.favouritelist.ui.home;

import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favouritelist.PlayerAdapter;
import com.example.favouritelist.PlayerItem;
import com.example.favouritelist.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<PlayerItem> playerItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PlayerAdapter(playerItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        playerItems.add(new PlayerItem(R.drawable.ronaldo, "Cristiano Ronaldo","0","0"));
        playerItems.add(new PlayerItem(R.drawable.messi, "Lionel Messi","1","0"));

        return root;
    }
}