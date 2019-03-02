package com.example.mtg.game;

import com.example.mtg.activities.ActivityGameBoard;

import java.util.ArrayList;

public class Player {
    Deck library;
    ArrayList<Card> hand, graveyard;
    ArrayList<Permanent> board;
    public Player(String library){
        hand = new ArrayList<>();
        graveyard = new ArrayList<>();
        board = new ArrayList<>();
        initializeDeck(library);
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
    public void initializeDeck(String color){
        if(color.equals("Red")){
          //  library = new Deck(//);
        }else if(color.equals("Blue")){

        }
    }


}
