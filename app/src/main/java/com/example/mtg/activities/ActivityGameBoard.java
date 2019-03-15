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
import com.example.mtg.gui.GameTimer;
import com.example.mtg.gui.HandleSharedData;
import com.example.mtg.gui.ImageHandler;
import com.example.mtg.gui.PlayersHand;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;
import com.example.mtg.networking.Utilities;

import java.util.ArrayList;

public class ActivityGameBoard extends AppCompatActivity implements ServerListener, GameTimer.timerAction {
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
    private TextView timer;
    private TextView oppATT;
    private TextView oppDEF;
    private TextView playerATT;
    private TextView playerDEF;


    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);


        recieveIntent();
        Singleton.getInstance().addlistener(this);
        setDeckColor();
        setUpAllItemsToScreen();
        createGame();
        buildHand();
        setPhaseMana();
        setCardIndex();
        isCardPlayable();
        sendHandToOtherPlayer();
        createTimer();



    }

    private void setDeckColor(){
        if(deckColor.equals("red")) cards = MasterCardClass.getInstance().getRedCards();
        else cards = MasterCardClass.getInstance().getBlueCards();
    }

    private void recieveIntent(){
        Intent intent = getIntent();
        deckColor = intent.getStringExtra(ActivityChooseDeck.DECK_CHOOSE);
        opponentDeckColor = intent.getStringExtra(ActivityChooseDeck.OPPONENT_DECK_COLOR);
        Log.d(TAG, deckColor);
    }

    private void createTimer(){
        timer = findViewById(R.id.Timer);


        GameTimer gameTimer = new GameTimer(this, timer);
        gameTimer.startTimer();
    }

    private void setPhaseMana(){
        phaseStatus.setText(game.getState().toString());
        playermana.setText(String.valueOf(game.getpMana()));
    }

    private void sendHandToOtherPlayer(){
        String message = "DECKCOLOR&" +deckColor;
        Singleton.getInstance().sendOverSocket(message, this);
    }

    private void buildHand(){
        ArrayList<Card> playerHand = game.getpHand();
        HandleSharedData.getInstance().setGame(game);
        handhelper(playerHand);


        //this will work if hand is not empty

//        Log.d(TAG, String.valueOf(playerHand.size()));


    }


    private void handhelper(ArrayList<Card> playerHand){
        if(playerHand.size() > 0){
            handGUI = new PlayersHand(playerHand);
            currentCardIMG.setImageDrawable(getDrawable(handGUI.getFirst().getDrawableName()));
        }else {
            currentCardIMG.setImageDrawable(ImageHandler.getImage(this, "card_back"));
        }
    }

    private void createGame(){
        game = new Game(cards, deckColor);
        game.initialDraw();
    }

    private void setUpAllItemsToScreen(){
        currentCardIMG = findViewById(R.id.currentCard);
        playermana = findViewById(R.id.playerMana);
        playLand = findViewById(R.id.playLand);
        playCard = findViewById(R.id.playCard);
        confirm = findViewById(R.id.confirm);
        cardIndex = findViewById(R.id.cardIndex);
        phaseStatus = findViewById(R.id.phaseStatus);
        opponentMana = findViewById(R.id.opponentMana);


        useCard = findViewById(R.id.playedCardPlayer);
        opponentuseCard = findViewById(R.id.playedCardOpp);

        oppATT = findViewById(R.id.oppATT);
        oppDEF = findViewById(R.id.oppDEF);
        playerATT = findViewById(R.id.playerATT);
        playerDEF = findViewById(R.id.playerDEF);

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
            playLandGUIupdate();
            setCardIndex();
            Singleton.getInstance().sendOverSocket("LANDVALUE: " + game.getpMana(), this);
        }

    }

    public void playLandGUIupdate(){
        handGUI.updateHand(game.getpHand());
        playermana.setText(String.valueOf(game.getpMana()));
        currentCardIMG.setImageDrawable(getDrawable(handGUI.getCurrent().getDrawableName()));
    }

    //called in xml file
    public void playCard(View view) {
        //move a card to the field
        Card c = handGUI.getCurrent();
        if(game.isCardPlayable(c)){
            Log.d(TAG,""+game.isCardPlayable(c));
            playCardHandler(c);
        }
    }

    public void playCardHandler(Card c){
        game.playCard(c);
        playCardGUIupdate(c);
        setCardIndex();
        Singleton.getInstance().sendOverSocket("CARD&" + c.toString(), this);
        setPlayerStats();
    }

    public void playCardGUIupdate(Card c){
        handGUI.updateHand(game.getpHand());
        currentCardIMG.setImageDrawable(getDrawable(handGUI.getCurrent().getDrawableName()));
        playermana.setText(String.valueOf(game.getpMana()));
        useCard.setImageDrawable(getDrawable(c.getDrawableName()));
    }

    public void setPlayerStats(){
        playerATT.setText(Integer.toString(game.getpAtk()));
        playerDEF.setText(Integer.toString(game.getpHP()));
    }

    //called in xml file
    public void confirmMove(View view) {
        //send over socket card

        isCardPlayable();
    }

    @Override
    public void notifyMessage(String msg) {



        if(msg.startsWith("LANDVALUE: ")){
            handleLandValueMessage(msg);
        }else if(msg.startsWith("CARD&")){
            handleCardMessage(msg);

        }else if(msg.startsWith("DECKCOLOR&")){
            handleDeckColorMessage(msg);
        }

    }

    private void handleLandValueMessage(String msg){
        String[] split = msg.split(" ");
        final String landvalue = split[1];
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentMana.setText(landvalue);
            }
        });

    }

    private void handleDeckColorMessage(String msg){
        String[] split = msg.split("&");
        String value = split[1];
        Log.d(TAG, "ODECKCOLOR: "+ value );

        opponentDeckColor = value;
    }

    private void handleCardMessage(String msg){
        String[] split = msg.split("&");
        String value = split[1];
        runCardMessageOnGUI(value);
    }

    private void runCardMessageOnGUI(String value){
        final Card c = new Card(value);
        game.oCardPlayed(c);
        //Log.d(TAG,c.getDrawableName());
        Log.d(TAG, HandleSharedData.getInstance().getOppenentDeckColor() + "_" + c.getDrawableName());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                opponentuseCard.setImageDrawable(getODrawable(c.getDrawableName()));
                oppATT.setText(Integer.toString(game.getoAtk()));
                oppDEF.setText(Integer.toString(game.getoHP()));
            }
        });
    }


    private Drawable getODrawable(String cardname){
        return ImageHandler.getImage(this, HandleSharedData.getInstance().getOppenentDeckColor() + "_" + cardname);
    }

    @Override
    public void endTimer() {
        game.toNextPhase();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                phaseStatus.setText(game.getState().toString());
                String who_won = Game.isGameOver();
                Utilities.endGame(ActivityGameBoard.this, who_won);

            }
        });

    }
}

