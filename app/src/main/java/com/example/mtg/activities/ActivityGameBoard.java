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
import com.example.mtg.game.Permanent;
import com.example.mtg.game.Player;
import com.example.mtg.game.UpdateLibrary;
import com.example.mtg.gui.ImageHandler;
import com.example.mtg.gui.PlayersHand;
import com.example.mtg.networking.ServerListener;

import java.util.ArrayList;

public class ActivityGameBoard extends AppCompatActivity implements ServerListener {
    private String deckColor;
    private static String TAG = "GAMEBOARD";
    private ArrayList cards;
    private ImageView currentCardIMG;
    private ImageView testingIMG;
    private PlayersHand handGUI;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);


        Intent intent = getIntent();
        deckColor = intent.getStringExtra(ActivityChooseDeck.DECK_CHOOSE);
        Log.d(TAG, deckColor);

        if(deckColor.equals("red")) cards = MasterCardClass.getInstance().getRedCards();
        else cards = MasterCardClass.getInstance().getBlueCards();

        Log.d(TAG,cards.toString());




        ImageHandler imageHandler = new ImageHandler(this);




        game = new Game(cards, deckColor);

        ArrayList<Card> playerHand = game.getpHand();
        ArrayList<Permanent> playerBoard = game.getpPermanents();
        ArrayList<Permanent> opponentBoard = game.getoPermanents();
        int playerMana = game.getpMana();
        int opponentMana = game.getoMana();
        int playerHP = game.getpHP();
        int opponentHP = game.getoHP();
        Game.State state = game.getState();
        ArrayList<Card> playerGraveyard = game.getPlayer().getGraveyard();


        //this will work if hand is not empty
        handGUI = new PlayersHand(playerHand);
        currentCardIMG.setImageDrawable(ImageHandler.getImage(this, handGUI.getFirst().getDrawableName()));


        //this is for testing purposes
        testingIMG = findViewById(R.id.playerBoard0);
        testingIMG.setImageDrawable(ImageHandler.getImage(this, "blue_island"));
        testingIMG = findViewById(R.id.playerBoard5);
        testingIMG.setImageDrawable(ImageHandler.getImage(this, "red_wrangle"));


    }


    //called in xml file
    public void nextCard(View view) {
        currentCardIMG.setImageDrawable(ImageHandler.getImage(this, handGUI.getNext().getDrawableName()));

    }

    //called in xml file
    public void lastCard(View view) {
        currentCardIMG.setImageDrawable(ImageHandler.getImage(this, handGUI.getLast().getDrawableName()));
    }



    @Override
    public void notifyMessage(String msg) {
        //parse name
        //if its an attacking player
        //check with cards, do math
        //return object of the deck, players field, the other players field
        //runs on UI thread updates based off of those fields.
    }

    //called in xml file
    public void playLand(View view) {

        // move land to field
    }

    //called in xml file
    public void playCard(View view) {
        //move a card to the field
    }

    //called in xml file
    public void nextPhase(View view) {
        //don't know
    }

    //called in xml file
    public void confirmMove(View view) {
        //send over socket card
    }
}

