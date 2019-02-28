package com.example.mtg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mtg.R;

import java.util.ArrayList;

public class ActivityChooseDeck extends AppCompatActivity {

    private Button clickRedDeck;
    private Button clickBlueDeck;
    private Button clickPlayGame;
    private ListView listViewRed;
    private ListView listViewBlue;
    private CustomListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_deck2);
        setUpButtons();
        setUpListViews();

    }

    private void setUpListViews(){
        listViewBlue = findViewById(R.id.ListView_blueDeck);
        listViewRed = findViewById(R.id.ListView_redDeck);

        ArrayList<String> a = new ArrayList<>();
        for(int i = 0; i < 20;i++){
            a.add("a");
        }

        adapter = new CustomListViewAdapter(this,
                R.layout.listview_list_decks, a);

        listViewBlue.setAdapter(adapter);
        listViewRed.setAdapter(adapter);
    }



    private void setUpButtons(){
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
