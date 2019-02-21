package com.example.mtg;

public class Permanent {
    private enum Type{
        CREATURE, ENCHANTMENT
    }
    Type type;
    public Permanent(Type type){
        this.type = type;
    }
}
