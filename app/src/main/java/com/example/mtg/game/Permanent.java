package com.example.mtg.game;

import android.util.Log;

public class Permanent {
    private int atk, hp;

    public Permanent(){
        this.atk = 0;
        this.hp = 0;
    }
    public void addStats(int addAtk, int addHp){
        Log.d("PERMANENT","ATK ADDED: "+ addAtk+", HP ADDED: "+ addHp);
        this.atk += addAtk;
        this.hp += addHp;
        Log.d("PERMANENT", "ATK"+atk+"HP"+hp);
    }
    @Override
    public String toString(){
        return ""+atk+","+hp;
    }

    public String calculate(Permanent other){
        Log.d("PERMANENT","YOU: "+hp+","+atk+","+other.hp+","+other.atk);
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
}
