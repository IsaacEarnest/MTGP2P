package com.example.mtg;

import android.graphics.drawable.Drawable;

public class Card {
    enum Type{
        LAND, CREATURE, ENCHANTMENT, INSTANT, SORCERY, ARTIFACT
    }
    enum CostColor{
        RED, BLUE, GREEN, WHITE, BLACK
    }
    String name;
    Type type;
    CostColor color;
    Drawable card;
    public Card(String name, Type type,CostColor c , Drawable card){
        this.name = name;
        this.type = type;
        this.color = c;
        this.card = card;
    }
    public CostColor getColor(){
        return color;
    }
    public String toString(){
        return name+"_"+type+"_"+color+"_"+card;
    }

    public boolean equals(Card other) {
        return false;
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
