package com.example.mtg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mtg.R;
import com.example.mtg.gui.ImageHandler;
import com.example.mtg.networking.ServerListener;

public class ActivityChooseDeck extends AppCompatActivity implements ServerListener {

    private Button clickRedDeck;
    private Button clickBlueDeck;
    private Button clickPlayGame;
    private ListView listViewRed;
    private ListView listViewBlue;

    public static final String DECK_CHOOSE = "com.example.mtg.DECK_CHOOSE";
    public static final String OPPONENT_DECK_COLOR = "com.example.mtg.OPPONENT_DECK_COLOR";
    public static String TAG = "CHOOSEDECK";
    private String deckColor = "none";
    private String opponentDeckColor;


    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choose_deck2);
            setUpButtons();
            setUpListViews();

    }

    private void setUpListViews(){
        ImageHandler imgs = new ImageHandler(this);
        setUpBlueDeck(imgs);
        setUpRedDeck(imgs);
    }

    private void setUpBlueDeck(ImageHandler imgs){
        listViewBlue = findViewById(R.id.ListView_blueDeck);
        CustomListViewAdapter adapter_blue = new CustomListViewAdapter(this,
            R.layout.listview_list_decks, imgs.buildBlueDeck());
        listViewBlue.setAdapter(adapter_blue);
    }

    private void setUpRedDeck(ImageHandler imgs) {
        listViewRed = findViewById(R.id.ListView_redDeck);
        CustomListViewAdapter adapter_red = new CustomListViewAdapter(this,
                R.layout.listview_list_decks, imgs.buildRedDeck());
        listViewRed.setAdapter(adapter_red);
    }

    private void setUpButtons(){
        clickRedDeck = findViewById(R.id.choose_redDeck);
        clickBlueDeck = findViewById(R.id.choose_blueDeck);
        clickPlayGame = findViewById(R.id.chooseDeck_PlayGame);
        clickPlayGame.setVisibility(View.INVISIBLE);
    }

    private void openActivityGameBoard() {
        Intent intent = new Intent(this, ActivityGameBoard.class);
        intent.putExtra(DECK_CHOOSE, deckColor);
        intent.putExtra(OPPONENT_DECK_COLOR, opponentDeckColor);
        startActivity(intent);
    }

    public void clickRedDeck(View view) {
        deckColor = "red";
        clickPlayGame.setVisibility(View.VISIBLE);
        clickRedDeck.setEnabled(false);
        clickBlueDeck.setEnabled(true);


    }

    public void clickBlueDeck(View view) {
        deckColor = "blue";
        clickPlayGame.setVisibility(View.VISIBLE);
        clickBlueDeck.setEnabled(false);
        clickRedDeck.setEnabled(
                true);
    }

    public void clickPlayGame(View view) {

        openActivityGameBoard();
    }

    @Override
    public void notifyMessage(String msg) {
        if(msg.startsWith("DECKCOLOR&")){
            String[] split = msg.split("&");
            String value = split[1];
            Log.d(TAG, "ODECKCOLOR: "+ value );
            opponentDeckColor = value;
        }
    }
}
