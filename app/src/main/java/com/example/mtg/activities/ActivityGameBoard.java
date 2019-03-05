package com.example.mtg.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.game.Card;
import com.example.mtg.game.Game;
import com.example.mtg.game.MasterCardClass;
import com.example.mtg.game.Permanent;
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
    private TextView playermana;
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

        currentCardIMG = findViewById(R.id.currentCard);
        playermana = findViewById(R.id.playerMana);


        ImageHandler imageHandler = new ImageHandler(this);




        game = new Game(cards, deckColor);
        game.toNextPhase();

        ArrayList<Card> playerHand = game.getpHand();
        ArrayList<Permanent> playerBoard = game.getpPermanents();
        ArrayList<Permanent> opponentBoard = game.getoPermanents();
        int playerMana = game.getpMana();
        int opponentMana = game.getoMana();
        int playerHP = game.getpHP();
        int opponentHP = game.getoHP();
        Game.Phase state = game.getState();
        ArrayList<Card> playerGraveyard = game.getPlayer().getGraveyard();


        //this will work if hand is not empty

//        Log.d(TAG, String.valueOf(playerHand.size()));

        if(playerHand.size() > 0){
            handGUI = new PlayersHand(playerHand);
            currentCardIMG.setImageDrawable(getDrawable(handGUI.getFirst().getDrawableName()));
        }else {
            Log.d(TAG, String.valueOf(playerHand.size()));
            currentCardIMG.setImageDrawable(ImageHandler.getImage(this, "card_back"));

        }

        Log.d(TAG, game.getpHand().toString());

        //this is for testing purposes
        testingIMG = findViewById(R.id.playerBoard0);
        testingIMG.setImageDrawable(ImageHandler.getImage(this, "blue_island"));
        testingIMG = findViewById(R.id.playerBoard5);
        testingIMG.setImageDrawable(ImageHandler.getImage(this, "red_wrangle"));


    }

    private Drawable getDrawable(String cardname){
        return ImageHandler.getImage(this,deckColor + "_" + cardname);
    }


    //called in xml file
    public void nextCard(View view) {
        currentCardIMG.setImageDrawable(getDrawable(handGUI.getNext().getDrawableName()));
    }

    //called in xml file
    public void lastCard(View view) {
        //tell the user what card its at
        currentCardIMG.setImageDrawable(getDrawable(handGUI.getLast().getDrawableName()));
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
        Card c = handGUI.getCurrent();
        Log.d(TAG, String.valueOf(c.getCost()));
        if(game.isPlayable(c)){

            game.playLand(c);
//            handGUI.updateHand(game.getpHand());
//            playermana.setText(game.getpMana());
//            currentCardIMG.setImageDrawable(getDrawable(handGUI.getCurrent().getDrawableName()));
        }
        // move land to  field
    }

    //called in xml file
    public void playCard(View view) {
        //move a card to the field
    }

    //called in xml file
    public void nextPhase(View view) {
        game.toNextPhase();
    }

    //called in xml file
    public void confirmMove(View view) {
        //send over socket card
    }
}

