package com.example.mtg.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.game.Card;
import com.example.mtg.game.Deck;
import com.example.mtg.game.Game;
import com.example.mtg.game.JSON;
import com.example.mtg.game.MasterCardClass;
import com.example.mtg.game.Player;
import com.example.mtg.game.UpdateLibrary;
import com.example.mtg.gui.ImageHandler;
import com.example.mtg.gui.PlayersHand;
import com.example.mtg.networking.ServerListener;

import java.util.ArrayList;

public class ActivityGameBoard extends AppCompatActivity implements ServerListener {
    private String deckColor = "Red";
    private static String TAG = "GAMEBOARD";
    private ArrayList cards;
    private ImageView currentCardIMG;
    private ImageView testingIMG;
    private PlayersHand playersHandGUI;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        if(deckColor.equals("Red")) cards = MasterCardClass.getInstance().getRedCards();
        else cards = MasterCardClass.getInstance().getBlueCards();
        Log.d(TAG,cards.toString());

        Intent intent = getIntent();
        deckColor = intent.getStringExtra(ActivityChooseDeck.DECK_CHOOSE);
        Log.d(TAG, deckColor);



        ImageHandler imageHandler = new ImageHandler(this);
        ArrayList<Drawable> red = imageHandler.buildRedDeck();
        playersHandGUI = new PlayersHand(red);


        //this is for testing purposes
        currentCardIMG = findViewById(R.id.currentCard);
        currentCardIMG.setImageDrawable(playersHandGUI.getFirst());

        game = new Game(deckColor);
        //button


        //this is for testing purposes
        testingIMG = findViewById(R.id.playerBoard0);
        testingIMG.setImageDrawable(ImageHandler.getImage(this, "blue_island"));
        testingIMG = findViewById(R.id.playerBoard5);
        testingIMG.setImageDrawable(ImageHandler.getImage(this, "red_wrangle"));

    }


    public void nextCard(View view) {
        currentCardIMG.setImageDrawable(playersHandGUI.getNext());

    }

    public void lastCard(View view) {
        currentCardIMG.setImageDrawable(playersHandGUI.getLast());
    }



    @Override
    public void notifyMessage(String msg) {
        //if its an attacking player
        //check with cards, do math
        //return object of the deck, players field, the other players field
        //runs on UI thread updates based off of those fields.
    }
}

