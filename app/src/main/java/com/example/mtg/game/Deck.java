package com.example.mtg.game;

import com.example.mtg.game.Card;

import java.util.ArrayList;

import static java.lang.Math.min;


//testing
public class Deck {
    private ArrayList<Card> deck;
    private int cards;
    public Deck(){
        deck = new ArrayList<>();
        cards = 0;
    }
    public void addCard(Card c){
        deck.add(c);
        cards++;
    }
    public boolean contains(Card c){
        for (Card card:deck) {
            if(card.equals(c)){
                return true;
            }
        }
        return false;
    }
    public void remove(Card c){
        deck.remove(c);

    }
    public Card getCard(int index){
        return deck.get(index);
    }
    public int getCardsLeft(){
        return cards;
    }
    public Card drawCard(){
        if(cards<0) {
            cards--;
            return deck.remove(0);
        }else{
            return null;
        }
    }
    public void shuffle(){
        ArrayList<Card> shuffled = new ArrayList<>();
        for(int i = deck.size(); i>0;i--){
            shuffled.add(deck.remove((int)(Math.random()*i)));
        }
        deck = shuffled;
    }
    public Deck parse(String str){
        String[] parsed = str.split(";");
        Deck d = new Deck();
        for (String s:parsed) {
           d.addCard(new Card(s));
        }
        return d;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Card c:deck) {
            sb.append(c.toString()+";");
        }
        String cards = sb.toString();
        return cards;
    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Deck){
            Deck that = (Deck) other;
            for (int i = 0; i < min(that.getCardsLeft(),this.cards); i++) {
                if(this.deck.get(i)!= that.getCard(i)){
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
