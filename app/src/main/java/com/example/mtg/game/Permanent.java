package com.example.mtg.game;

public class Permanent {
    private String name;
    private int atk, hp;
    private boolean canAtk,canBlock,isFlying;
    public Permanent(Card c){
      name = c.getName();
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return name+","+hp+","+canAtk+","+canAtk+","+canBlock+","+isFlying;
    }
    public String defend(Permanent other){
        if( ( other.isFlying || this.isFlying ) && ! ( other.isFlying && this.isFlying ) ){

        }else{

        }
        return null;
    }
}
