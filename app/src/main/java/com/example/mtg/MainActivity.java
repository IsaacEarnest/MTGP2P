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
        p1Library.shuffle();
<<<<<<< HEAD
=======
    }

    public void createLobby(View view) {
        openActivityCreateLobby();

    }

    private void openActivityCreateLobby() {
        Intent intent = new Intent(this, ActivityCreateLobby.class);
        startActivity(intent);

>>>>>>> c809c515795f033fbc314e2d99cfe891e6351c9a
    }
}
