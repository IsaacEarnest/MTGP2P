package com.example.mtg;

import android.graphics.drawable.Drawable;

public class Card {
    String type;
    enum CostColor{
        RED, BLUE, GREEN, WHITE, BLACK;
    }
    CostColor cost;
    Drawable card;
    public Card(String type,CostColor c , Drawable card){
        this.type = type;
        this.cost = c;
        this.card = card;
    }
    public int getCost(){

    }

}
