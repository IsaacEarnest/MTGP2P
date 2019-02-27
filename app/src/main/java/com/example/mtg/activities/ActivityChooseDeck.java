package com.example.mtg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mtg.R;

public class ActivityChooseDeck extends AppCompatActivity {

    private Button clickRedDeck;
    private Button clickBlueDeck;
    private Button clickPlayGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deck2);
        clickRedDeck = findViewById(R.id.choose_redDeck);
        clickBlueDeck = findViewById(R.id.choose_blueDeck);
        clickPlayGame = findViewById(R.id.chooseDeck_PlayGame);
        clickPlayGame.setVisibility(View.INVISIBLE);

    }

    private void openActivityGameBoard() {
        Intent intent = new Intent(this, ActivityGameBoard.class);
        startActivity(intent);
    }

    public void clickRedDeck(View view) {
        clickPlayGame.setVisibility(View.VISIBLE);

    }

    public void clickBlueDeck(View view) {
        clickPlayGame.setVisibility(View.VISIBLE);
    }

    public void clickPlayGame(View view) {
        openActivityGameBoard();
    }
}
