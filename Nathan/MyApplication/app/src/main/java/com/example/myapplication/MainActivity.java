package com.example.myapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView myListView;
    ArrayList<RowItem> myRowItems;
    private DrawerLayout mDrawerlayout ;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.playerlist);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myRowItems = new ArrayList<RowItem>();
        myListView = (ListView) findViewById(R.id.myListView);

        fillArrayList( );

        CustomAdapter myAdapter = new CustomAdapter(getApplicationContext(), myRowItems);

        myListView.setAdapter( myAdapter );

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                RowItem list_row = myRowItems.get(position);
                Intent playerCardIntent = new Intent( getApplicationContext( ), PlayerCard.class );
                playerCardIntent.putExtra( "IMAGE_NAME", list_row.getBigImageName() );
                startActivity( playerCardIntent );
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillArrayList() {
        RowItem row_one = new RowItem( );
        row_one.setHeading("Cristiano Ronaldo");
        row_one.setSubHeading("Juventus");
        row_one.setSmallImageName( R.drawable.ronaldo );
        row_one.setBigImageName( R.drawable.player_card );
        myRowItems.add( row_one );

        RowItem row_two = new RowItem( );
        row_two.setHeading("Lionel Messi");
        row_two.setSubHeading("Barcelona");
        row_two.setSmallImageName( R.drawable.messi );
        row_two.setBigImageName( R.drawable.player_card );
        myRowItems.add( row_two );

        RowItem row_three = new RowItem( );
        row_three.setHeading("Mesut Ozil");
        row_three.setSubHeading("Arsenal");
        row_three.setSmallImageName( R.drawable.ozil );
        row_three.setBigImageName( R.drawable.player_card );
        myRowItems.add( row_three );

        RowItem row_four = new RowItem( );
        row_four.setHeading("Sergio Ramos");
        row_four.setSubHeading("Real Madrid");
        row_four.setSmallImageName( R.drawable.ramos );
        row_four.setBigImageName( R.drawable.player_card );
        myRowItems.add( row_four );

        RowItem row_five = new RowItem( );
        row_five.setHeading("James Rodriguez");
        row_five.setSubHeading("Bayern Munich");
        row_five.setSmallImageName( R.drawable.rodriguez );
        row_five.setBigImageName( R.drawable.player_card );
        myRowItems.add( row_five );


    }




}