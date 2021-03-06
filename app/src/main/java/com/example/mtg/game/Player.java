package com.example.mtg.game;

import java.util.ArrayList;

public class Player {
    private Deck library;
    private ArrayList<Card> hand;
    public Player(ArrayList cards, String lib){
        hand = new ArrayList<>();
        initializeDeck(cards, lib);
        library.shuffle();
    }
    public void remove(Card c){
        if(handContains(c)){
            hand.remove(hand.indexOf(c));
        }
    }
    public ArrayList getHand(){
        return this.hand;
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

        int deckSplit = (cards.size()/2)+1;
        if(color.equals("red")){

            for(int i = 0; i<deckSplit;i++){
                Card c = new Card(cards.get(i));
                int count = c.parseCount(cards.get(i));
                    while(count>0){
                        library.addCard(c);
                        count--;
                    }

            }
        }else if(color.equals("blue")){
            for(int i = deckSplit; i<cards.size();i++){
                Card c = new Card(cards.get(i));
                int count = c.parseCount(cards.get(i));
                while(count>0){
                    library.addCard(c);
                    count--;
                }

            }
        }
    }


}
