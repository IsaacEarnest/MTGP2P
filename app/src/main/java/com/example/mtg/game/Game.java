package com.example.mtg.game;

import java.util.ArrayList;

public class Game {
    private static Player player;
    private static ArrayList<Permanent> pBoard;
    private static ArrayList<Permanent> oBoard;
    private static int timesMulled;
    private static int oCards;
    private static int oMana, oMaxMana, pMana, pMaxMana, oHP, pHP;
    private static boolean landPlayable;
    private Phase phase;
    public Game(ArrayList cards, String library){
        landPlayable = true;
        oHP = 20;
        pHP = 20;
        pMana = 5;
        pMaxMana = pMana;
        oMana = 0;
        oMaxMana = oMana;
        timesMulled = 0;
        oCards = 30;
        player = new Player(cards, library);
        phase = Phase.BEGINNING;
    }
    public enum Phase {
        BEGINNING {
            @Override
            Phase nextPhase() {


                    landPlayable=true;
                    player.drawCard();
                    pMana = pMaxMana;

                return COMBAT;
            }
        },
        COMBAT {
            @Override
            Phase nextPhase() {

                return ENDING;
            }
        },
        ENDING {
            @Override
            Phase nextPhase() {

                return GAME_OVER;
            }
        },
        GAME_OVER {
            @Override
            Phase nextPhase() {
                return GAME_OVER;
            }
        };

        abstract Phase nextPhase();
    }
    public void toNextPhase(){
        phase = phase.nextPhase();


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
                if (landPlayable) {
                    return true;
                }

                return false;

            }

        }
        return false;
    }
    public boolean isCardPlayable(Card c){
        if(pMana >= c.getCost() && c.getType()!=Card.Type.LAND) {
            if (phase == Phase.BEGINNING) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Card c){
        if(isCardPlayable(c)){
            pMana -= c.getCost();
            if(c.getType()== Card.Type.CREATURE){

           //     pBoard.add(new Permanent(c));

            }
        }
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
    public ArrayList getpPermanents(){
        return pBoard;
    }
    public ArrayList getoPermanents(){
        return oBoard;
    }

    public void playLand(Card c){
        if(isLandPlayable(c)){
            if(c.getType()== Card.Type.LAND){
                landPlayable=false;
                pMana++;
                pMaxMana++;
                player.remove(c);
            }
        }
    }
    public void oLandPlayed(){
        oMana++;
        oMaxMana++;
    }
    public void oCardPlayed(Card c){
        oMana -= c.getCost();
        if(c.getType()== Card.Type.CREATURE){
            oBoard.add(new Permanent(c));
        }
    }
    public ArrayList<Card> getpHand(){
        return player.getHand();
    }
    public static String isGameOver(Permanent other){
        return (player.calculate(other));
    }




}
