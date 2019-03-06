package com.example.mtg.game;

public class Permanent {
    private String name;
    private int atk, hp;
    private boolean canAtk,canBlock,isFlying,isHaste;
    public Permanent(Card c){
      name = c.getName();
      if(c.getType()== Card.Type.CREATURE) {
          hp = c.getPermanentHealth();
          atk = c.getPermanentPower();
      }
      isHaste = false;
      isFlying = false;
      canAtk = isHaste;
      canBlock = true;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return name+","+hp+","+canAtk+","+canAtk+","+canBlock+","+isFlying;
    }

    public String defend(Permanent other){
        if(this.canBlock && other.canAtk) {
            if ((other.isFlying || this.isFlying) && !(other.isFlying && this.isFlying)) {
                other.attacking();
                return "DEFENDER/" + hp + "/ATTACKER/" + other.hp;
            } else {
                if (hp - other.atk < 1 && other.hp - atk < 1) {
                    hp = 0;
                    other.setHp(0);
                    return "DEFENDER/" + 0 + "/ATTACKER/" + 0;
                } else if (hp - other.atk < 1 && other.hp - atk > 1) {
                    hp = 0;
                    other.setHp(other.hp - atk);
                    return "DEFENDER/" + 0 + "/ATTACKER/" + other.hp;
                } else if (hp - other.atk > 1 && other.hp - atk < 1) {
                    hp -= other.atk;
                    other.setHp(0);
                    return "DEFENDER/" + hp + "/ATTACKER/" + 0;
                } else {
                    hp -= other.atk;
                    other.setHp(other.hp - atk);
                    return "DEFENDER/" + (hp - other.atk) + "/ATTACKER/" + (other.hp - atk);
                }
            }
            //TODO what to send here
        }else return "";
    }
    public void attacking(){
        canAtk = false;
    }
    public void setHp(int i){
        hp = i;
    }
    public boolean isDead(){
        return (hp<1);
    }
    public void untap(){
        canAtk = true;
    }
}
