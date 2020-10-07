package com.example.onestopbase.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.onestopbase.R;
import com.example.onestopbase.ui.accounts.AccountsViewModel;

public class FavouritesFragment extends Fragment {

    private FavouritesViewModel FavouritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavouritesViewModel =
                ViewModelProviders.of(this).get(FavouritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourites, container, false);
        final TextView textView = root.findViewById(R.id.text_favourites);
        FavouritesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}