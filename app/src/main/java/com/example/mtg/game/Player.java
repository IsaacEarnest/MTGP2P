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
    public void initializeDeck(ArrayList<String> cards, String color){
        library = new Deck();

        if(color.equals("Red")){
            for(int i = 0; i<(cards.size()/2);i++){
                library.addCard(new Card(cards.get(i)));
            }
        }else if(color.equals("Blue")){
            for(int i = (cards.size()/2); i<cards.size();i++){
                library.addCard(new Card(cards.get(i)));
            }
        }
    }
    public ArrayList<Card> getGraveyard(){
        return graveyard;
    }


}
