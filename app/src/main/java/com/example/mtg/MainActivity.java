package com.example.mtg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Deck p1Library, p2Library, p1Hand, p2Hand, p1Graveyard, p2Graveyard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gameStart();
    }
    public void gameStart(){
        //p1Library.shuffle();

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
}
