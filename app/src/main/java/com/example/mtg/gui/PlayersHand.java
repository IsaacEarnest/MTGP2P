package com.example.mtg.gui;

import android.graphics.drawable.Drawable;

import com.example.mtg.R;

import java.util.ArrayList;

public class PlayersHand {

    private ArrayList<Drawable> hand;
    private int currentCard = 0;

    public PlayersHand(ArrayList<Drawable> hand){
        this.hand = hand;

    }

    public Drawable getFirst(){
        return hand.get(0);
    }

    public Drawable getNext(){
        if(currentCard < hand.size() - 1){
            currentCard++;
        }else{
            currentCard = 0;
        }
        return hand.get(currentCard);
    }

    public Drawable getLast(){
        if(currentCard > 0){
            currentCard--;
        }else{
            currentCard = hand.size() - 1;
        }
        return hand.get(currentCard);
    }

}
