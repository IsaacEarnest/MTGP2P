package com.example.mtg.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.mtg.networking.Singleton;

import java.util.ArrayList;

public class ActivityGameBoard extends AppCompatActivity implements ServerListener {
    private String deckColor;
    private static String TAG = "GAMEBOARD";
    private ArrayList cards;
    private ImageView currentCardIMG;
    private ImageView testingIMG;
    private PlayersHand handGUI;
    private TextView playermana;
    private Button playLand;
    private Button playCard;
    private Button nextPhase;
    private TextView cardIndex;
    private TextView phaseStatus;
    private ImageView useCard;
    private TextView opponentMana;
    private ImageView opponentuseCard;
    private String opponentDeckColor;
    private Button confirm;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);


        Intent intent = getIntent();
        deckColor = intent.getStringExtra(ActivityChooseDeck.DECK_CHOOSE);
        opponentDeckColor = intent.getStringExtra(ActivityChooseDeck.OPPONENT_DECK_COLOR);
        Log.d(TAG, deckColor);
        Singleton.getInstance().addlistener(this);

        if(deckColor.equals("red")) cards = MasterCardClass.getInstance().getRedCards();
        else cards = MasterCardClass.getInstance().getBlueCards();

        Log.d(TAG,cards.toString());

        currentCardIMG = findViewById(R.id.currentCard);
        playermana = findViewById(R.id.playerMana);
        playLand = findViewById(R.id.playLand);
        playCard = findViewById(R.id.playCard);
        nextPhase = findViewById(R.id.nextPhase);
        confirm = findViewById(R.id.confirm);
        cardIndex = findViewById(R.id.cardIndex);
        phaseStatus = findViewById(R.id.phaseStatus);
        useCard = findViewById(R.id.playerBoard2);
        opponentMana = findViewById(R.id.opponentMana);
        opponentuseCard = findViewById(R.id.opponentBoard2);



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

            currentCardIMG.setImageDrawable(ImageHandler.getImage(this, "card_back"));

        }

        phaseStatus.setText(game.getState().toString());
        playermana.setText("5");
        setCardIndex();
        isCardPlayable();




        Singleton.getInstance().sendOverSocket("DECKCOLOR&"+deckColor, this);


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

    private void isCardPlayable(){
        Card c = handGUI.getCurrent();
        if(game.isLandPlayable(c)){
            playLand.setEnabled(true);
            playCard.setEnabled(false);
        }
        if(game.isCardPlayable(c)){
            playCard.setEnabled(true);
            playLand.setEnabled(false);
        }
    }
    private void setCardIndex(){
        cardIndex.setText(String.valueOf(handGUI.getCurrentIndex()));
    }

    //called in xml file
    public void nextCard(View view) {
        String name = handGUI.getNext().getDrawableName();
        currentCardIMG.setImageDrawable(getDrawable(name));
        isCardPlayable();
        setCardIndex();

    }

    //called in xml file
    public void lastCard(View view) {
        //tell the user what card its at
        currentCardIMG.setImageDrawable(getDrawable(handGUI.getLast().getDrawableName()));
        isCardPlayable();
        setCardIndex();
    }

    //called in xml file
    public void playLand(View view) {
        Card c = handGUI.getCurrent();
        if(game.isLandPlayable(c)){
            //this deletes it from your hand
            game.playLand(c);
            //this updates the hand
            handGUI.updateHand(game.getpHand());
            playermana.setText(String.valueOf(game.getpMana()));
            currentCardIMG.setImageDrawable(getDrawable(handGUI.getCurrent().getDrawableName()));
            setCardIndex();
            Singleton.getInstance().sendOverSocket("LANDVALUE: " + game.getpMana(), this);
        }
        // move land to  field
    }

    //called in xml file
    public void playCard(View view) {
        //move a card to the field
        Card c = handGUI.getCurrent();
        if(game.isCardPlayable(c)){
            game.playCard(c);
            handGUI.updateHand(game.getpHand());
            currentCardIMG.setImageDrawable(getDrawable(handGUI.getCurrent().getDrawableName()));
            playermana.setText(String.valueOf(game.getpMana()));
            useCard.setImageDrawable(getDrawable(c.getDrawableName()));
            setCardIndex();
            Singleton.getInstance().sendOverSocket("CARD&" + c.toString(), this);
        }
    }

    //called in xml file
    public void nextPhase(View view) {
        game.toNextPhase();

        phaseStatus.setText(game.getState().toString());
        isCardPlayable();


    }

    //called in xml file
    public void confirmMove(View view) {
        //send over socket card
        isCardPlayable();

    }

    @Override
    public void notifyMessage(String msg) {

        Log.d("MESSAGE",msg);


        if(msg.startsWith("LANDVALUE: ")){
            String[] split = msg.split(" ");
            final String landvalue = split[1];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    opponentMana.setText(landvalue);
                }
            });
        }else if(msg.startsWith("CARD&")){
            String[] split = msg.split("&");
            String value = split[1];
            final Card c = new Card(value);
            Log.d(TAG,c.getDrawableName());
            Log.d(TAG,opponentDeckColor + "_" + c.getDrawableName());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    opponentuseCard.setImageDrawable(getODrawable(c.getDrawableName()));
                }
            });
        }else if(msg.startsWith("DECKCOLOR&")){
            String[] split = msg.split("&");
            String value = split[1];
            opponentDeckColor = value;
        }
        //parse name
        //if its an attacking player
        //check with cards, do math
        //return object of the deck, players field, the other players field
        //runs on UI thread updates based off of those fields.
    }
    private Drawable getODrawable(String cardname){
        return ImageHandler.getImage(this,opponentDeckColor + "_" + cardname);
    }
}

