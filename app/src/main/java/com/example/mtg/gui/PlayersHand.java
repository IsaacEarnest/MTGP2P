package com.example.mtg.gui;

import android.graphics.drawable.Drawable;

import com.example.mtg.R;
import com.example.mtg.game.Card;

import java.util.ArrayList;

public class PlayersHand {

    private ArrayList<Card> hand;
    private int currentCard = 0;

    public PlayersHand(ArrayList<Card> hand){
        this.hand = hand;

    }

    public void updateHand(ArrayList<Card> hand){
        this.hand = hand;
    }

    public Card getFirst(){
        return hand.get(0);
    }

    public Card getNext(){
        if(currentCard < hand.size() - 1){
            currentCard++;
        }else{
            currentCard = 0;
        }
        return hand.get(currentCard);
    }

    public Card getLast(){
        if(currentCard > 0){
            currentCard--;
        }else{
            currentCard = hand.size() - 1;
        }
        return hand.get(currentCard);
    }

    public Card getCurrent() {
        return hand.get(currentCard);
    }
}
