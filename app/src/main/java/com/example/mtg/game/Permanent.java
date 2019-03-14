package com.example.mtg.game;

import android.util.Log;

public class Permanent {
    private String name;
    private int atk, hp;
    public Permanent(Card c){
      name = c.getName();
      if(c.getType()== Card.Type.CREATURE) {
          hp = c.getPermanentHealth();
          atk = c.getPermanentPower();
      }
    }
    public Permanent(){
        this.atk = 0;
        this.hp = 0;
    }
    public String getName(){
        return name;
    }
    public void addStats(int addAtk, int addHp){
        Log.d("PERMANENT","ATK ADDED: "+ addAtk+", HP ADDED: "+ addHp);
        this.atk += addAtk;
        this.hp += addHp;
    }
    @Override
    public String toString(){
        return name+","+hp;
    }

    public String calculate(Permanent other){
        Log.d("PERMANENT","YOU: "+hp+","+other.atk+","+atk+","+other.hp);
                if (hp - other.atk < 1 && other.hp - atk < 1) {
                    return "TIE";
                } else if (hp - other.atk < 1 && other.hp - atk >= 1) {

                    return "YOU LOSE";
                } else if (hp - other.atk >= 1 && other.hp - atk < 1) {
                    return "YOU WIN";
                } else {
                    return "TIE";
                }
        }

    public void setHp(int i){
        hp = i;
    }
    public boolean isDead(){
        return (hp<1);
    }
    public void setStats(int atk, int hp){
        this.atk = atk;
        this.hp = hp;
    }
}
