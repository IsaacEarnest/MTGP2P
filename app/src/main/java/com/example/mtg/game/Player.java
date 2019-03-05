package com.example.mtg.game;

import java.util.ArrayList;

public class Player {
    private Deck library;
    private ArrayList<Card> hand, graveyard;
    private ArrayList<Permanent> board;
    public Player(ArrayList cards, String library){
        hand = new ArrayList<>();
        graveyard = new ArrayList<>();
        board = new ArrayList<>();
        initializeDeck(cards, library);
    }
    public void remove(Card c){
        if(handContains(c)){
            hand.remove(hand.indexOf(c));
        }
    }
    public ArrayList getHand(){
        return this.hand;
    }
    public Deck getLibrary(){
        return this.library;
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
    public void initializeDeck(ArrayList cards, String color){
        library = new Deck();
        if(color.equals("Red")){
            
        }else if(color.equals("Blue")){

        }
    }


}
