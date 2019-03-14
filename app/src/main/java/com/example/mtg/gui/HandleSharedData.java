package com.example.mtg.gui;

import com.example.mtg.game.Game;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;

public class HandleSharedData implements ServerListener {
    private static final HandleSharedData ourInstance = new HandleSharedData();
    private Game game;
    private String opponentDeckColor = "red";


    public static HandleSharedData getInstance() {
        return ourInstance;
    }

    private HandleSharedData() {
        Singleton.getInstance().addlistener(this);

    }

    public void setGame(Game game){this.game = game;}


    public void changePhase(){
        game.toNextPhase();
    }

    public String getOppenentDeckColor(){
        return opponentDeckColor;
    }

    @Override
    public void notifyMessage(String msg) {
       if(msg.startsWith("DECKCOLOR&")){
            String[] split = msg.split("&");
            String value = split[1];
            opponentDeckColor = value;
        }
    }
}
