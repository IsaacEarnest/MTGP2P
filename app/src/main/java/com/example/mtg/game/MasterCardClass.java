package com.example.mtg.game;

import android.content.Context;

import java.util.ArrayList;

public class MasterCardClass {
    ArrayList redCards,blueCards;
    private static final MasterCardClass ourInstance = new MasterCardClass();

    public static MasterCardClass getInstance() {
        return ourInstance;
    }
    public void loadAllCards(Context con){
        if(getRedCards()==null && getBlueCards()==null) {
            JSON json = new JSON(con);
            redCards = json.parseJSON("redDeck");
            blueCards = json.parseJSON("blueDeck");
        }
    }
    public ArrayList getRedCards(){
        return redCards;
    }
    public ArrayList getBlueCards(){
        return blueCards;
    }

    private MasterCardClass() {
    }
}
