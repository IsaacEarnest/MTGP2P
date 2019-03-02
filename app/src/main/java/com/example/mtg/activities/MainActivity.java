package com.example.mtg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mtg.R;
import com.example.mtg.game.JSON;
import com.example.mtg.game.MasterCardClass;
import com.example.mtg.networking.Singleton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JSON.updateList {
    private ArrayList<String> cards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //only needs to be called once
        MasterCardClass.getInstance().loadAllCards(this);

        Singleton.getInstance();
    }

    //This is called in the xml file
    public void createLobby(View view) {
        openActivityCreateLobby();

    }

    private void openActivityCreateLobby() {
        Intent intent = new Intent(this, ActivityCreateLobby.class);
        startActivity(intent);


    }

    //This is called in the xml file
    public void joinLobby(View view) {
        onActivityJoinLobby();
    }

    private void onActivityJoinLobby() {
        Intent intent = new Intent(this, ActivityJoinLobby.class);
        startActivity(intent);
    }
    @Override
    public void update(String id) {
        cards.add(id);
    }
}
