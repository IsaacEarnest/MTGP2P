package com.example.mtg;

public class Permanent {
    boolean canAtk,canBlock,isFlying,isVigilance;
    private enum Type{
        CREATURE, ENCHANTMENT
    }
    Type type;
    public Permanent(Type type){
        this.type = type;
    }
}
