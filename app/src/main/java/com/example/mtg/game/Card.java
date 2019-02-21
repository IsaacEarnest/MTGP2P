package com.example.mtg.game;

import android.graphics.drawable.Drawable;

public class Card {
    enum Type{
        LAND, CREATURE, ENCHANTMENT, INSTANT, SORCERY
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
    public String toString(){
        return name+"_"+type+"_"+cost+"_"+card;
    }

    public boolean equals(Card other) {
        return false;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    public void setOnCastEffect(){

    }
    public void setOnAtkEffect(){

    }

}
