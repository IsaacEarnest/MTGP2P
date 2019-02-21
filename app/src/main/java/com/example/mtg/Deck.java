package com.example.mtg;

import java.util.ArrayList;


//testing
public class Deck {
    ArrayList<Card> deck;
    int cards;
    public Deck(){
        deck = new ArrayList<>();
        cards = 0;
    }
    public void addCard(Card c){
        deck.add(c);
        cards++;
    }
    public int getCardsLeft(){
        return cards;
    }
    public Card drawCard(){
        Card c = deck.remove(0);
        return c;
    }
    public void shuffle(){
        ArrayList<Card> shuffled = new ArrayList<>();
        for(int i = deck.size(); i>0;i--){
            shuffled.add(deck.remove((int)(Math.random()*i)));
        }
        deck = shuffled;
    }
}
