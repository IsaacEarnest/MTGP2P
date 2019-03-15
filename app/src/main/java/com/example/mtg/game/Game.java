package com.example.mtg.game;

import android.util.Log;

import java.util.ArrayList;

public class Game {
    private static Player player;
    private static Permanent pBoard;
    private static Permanent oBoard;
    private static int oMana, pMana, oAtk, oHP, pAtk,pHP;
    private Phase phase;
    public Game(ArrayList cards, String library){
        pMana = 5;
        oMana = 5;
        player = new Player(cards, library);
        phase = Phase.BEGINNING;
        oAtk = 0;
        oHP = 0;
        pAtk = 0;
        pHP = 0;
        oBoard = new Permanent();
        pBoard = new Permanent();
    }
    public enum Phase {
        BEGINNING {
            @Override
            void playPhase(){

            }
            @Override
            Phase nextPhase() {
                return COMBAT;
            }
        },
        COMBAT {
            void playPhase(Permanent other){

            }
            void playPhase(){

            }
            @Override
            Phase nextPhase() {
                return ENDING;
            }
        },
        ENDING {
            @Override
            void playPhase(){

            }
            @Override
            Phase nextPhase() {
                return GAME_OVER;
            }
        },
        GAME_OVER {
            @Override
            void playPhase(){

            }
            @Override
            Phase nextPhase() {
                return GAME_OVER;
            }
        };

        abstract Phase nextPhase();
        abstract void playPhase();
    }
    public int getpAtk(){
        return pAtk;
    }
    public int getoAtk(){
        return oAtk;
    }

    public void toNextPhase(){
        phase = phase.nextPhase();


    }
    public void initialDraw(){
        for (int i = 0; i <7 ; i++) {
            player.drawCard();
        }

    }



    public void setState(Phase state) {
        this.phase = state;
    }
    public Phase getState() {
        return phase;
    }
    public Player getPlayer(){
        return player;
    }
    public boolean isLandPlayable(Card c){
        if (phase == Phase.BEGINNING ) {
            if(c.getType() == Card.Type.LAND) {
                    return true;
                }

                return false;

            }


        return false;
    }
    public boolean isCardPlayable(Card c){
        if(pMana >= c.getCost() && c.getType()!=Card.Type.LAND) {
            if (phase == Phase.BEGINNING&&c.getType()== Card.Type.CREATURE) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Card c){
            pMana -= c.getCost();
            pAtk+= c.getPermanentPower();
            pHP += c.getPermanentHealth();
        Log.d("PERMANENT","ATK ADDED: "+ c.getPermanentPower()+", HP ADDED: "+ c.getPermanentHealth());
            pBoard.addStats(c.getPermanentPower(),c.getPermanentHealth());
            player.remove(c);

    }
    public int getpMana(){
        return pMana;
    }
    public int getoMana(){
        return oMana;
    }
    public int getpHP(){
        return pHP;
    }
    public int getoHP(){
        return oHP;
    }
    public Permanent getpPermanent(){
        return pBoard;
    }
    public Permanent getoPermanent(){
        return oBoard;
    }

    public void playLand(Card c){
        pMana++;
        player.remove(c);
    }


    public void oLandPlayed(){
        oMana++;
    }
    public void oCardPlayed(Card c){
        oMana -= c.getCost();
        oBoard.addStats(c.getPermanentPower(),c.getPermanentHealth());
        oAtk += c.getPermanentPower();
        oHP += c.getPermanentHealth();

    }
    public ArrayList<Card> getpHand(){
        return player.getHand();
    }
    public static String isGameOver(){
        return (pBoard.calculate(oBoard));
    }




}
