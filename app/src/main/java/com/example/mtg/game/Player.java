package com.example.mtg.game;

import java.util.ArrayList;

public class Player {
    Deck library;
    ArrayList<Card> hand, graveyard, board;
    public Player(){

    }
    public void remove(Card c){
        if(handContains(c)){
            hand.remove(hand.indexOf(c));
            //update UI- remove card from player hand
        }
    }
    public ArrayList getHand(){
        return hand;
    }
    public void drawCard(){
        hand.add(library.drawCard());
    }
    public boolean handContains(Card c){
        for (Card card:hand) {
            if(card.equals(c)){
                return true;
            }
        }
        return false;
    }


}
