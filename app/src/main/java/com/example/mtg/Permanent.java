package com.example.mtg;

public class Permanent {
    private enum Type{
        LAND, CREATURE, ENCHANTMENT
    }
    Type type;
    public Permanent(Type type){
        this.type = type;
    }
}
