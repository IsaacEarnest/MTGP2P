package com.example.mtg.game;

import android.graphics.drawable.Drawable;

public class Card {
    enum Type{
        LAND{
            @Override
            void playCard(Card c) {
                //increase mana by 1, remove from hand

            }
        },
        CREATURE{
            @Override
            void playCard(Card c) {
                //add to stack
            }
        },
        ENCHANTMENT{
            @Override
            void playCard(Card c) {
                //add to stack
            }
        },
        INSTANT{
            @Override
            void playCard(Card c) {
                //add to stack
            }
        },
        SORCERY{
            @Override
            void playCard(Card c) {
                //add to stack
            }
        };
        abstract void playCard(Card c);
    }
    String name;
    Type type;
    int cost;
    Drawable card;
    public Card(String name, Type type,int cost , Drawable card){
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.card = card;
    }
    public int getCost(){
        return cost;
    }
    @Override
    public String toString(){
        return name+"_"+type+"_"+cost+"_"+card;
    }
    public Card parse(String str){
        String[] parsed = str.split("_");
        return new Card(parsed[0],parsed[1],Integer.parseInt(parsed[2]),parsed[3].);
    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Card){
            Card that = (Card) other;
            return this.name.equals(that.name) && this.type == that.type && this.cost == that.cost && this.card == that.card;
        }else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    public Type getType() {
        return type;
    }




}
