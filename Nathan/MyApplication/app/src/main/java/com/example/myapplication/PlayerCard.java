package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class PlayerCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_card);

        Intent playerIntent = getIntent( );

        int player_card_int = playerIntent.getIntExtra("IMAGE_NAME", 0);

        ImageView myImage = (ImageView) findViewById(R.id.player_card);

        myImage.setImageResource( player_card_int);
    }
}