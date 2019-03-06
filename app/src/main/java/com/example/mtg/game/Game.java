package com.example.mtg.game;

import android.util.Log;

import java.util.ArrayList;

public class Game {
    private static Player player;
    ArrayList<Permanent> pBoard;
    ArrayList<Permanent> oBoard;
    private static int timesMulled;
    private static int oCards;
    private static int oMana, pMana, oHP, pHP;
    boolean landPlayed;
    Phase phase;
    public Game(ArrayList cards, String library){
        landPlayed = false;
        oHP = 20;
        pHP = 20;
        pMana = 0;
        oMana = 0;
        timesMulled = 0;
        oCards = 30;
        player = new Player(cards, library);
        phase = Phase.MULLIGAN;
    }
    public enum Phase {
        MULLIGAN{
          @Override
          Phase nextPhase(){
              if(!isGameOver()){
               mulligan();

              }
              return BEGINNING;
          }
        },
        BEGINNING {
            @Override
            Phase nextPhase() {
                if(!isGameOver()){

                }
                //untap();
                //upkeep();
                //player.draw(deck);
                return PRECOMBATMAIN;
            }
        },
        PRECOMBATMAIN {
            @Override
            Phase nextPhase() {
                if(!isGameOver()){

                }
                //wait for player to play card
                return COMBAT;
            }
        },
        COMBAT {
            @Override
            Phase nextPhase() {
                if(!isGameOver()){

                }
                return POSTCOMBATMAIN;
            }
        },
        POSTCOMBATMAIN {
            @Override
            Phase nextPhase() {
                if(!isGameOver()){

                }
                //identical to PRECOMBATMAIN
                return ENDING;
            }
        },
        ENDING {
            @Override
            Phase nextPhase() {
                if(!isGameOver()){

                }
                //players are technically allowed to perform
                return OPPONENT_TURN;
            }
        },
        RESPONDING {
            @Override
            Phase nextPhase() {
                if(!isGameOver()){

                }
                return OPPONENT_TURN;
            }
        },
        OPPONENT_TURN {
            //wait for opponent to send an action for a chance to respond, or wait until opponent ends their turn
            @Override
            Phase nextPhase() {
                if(!isGameOver()){

                }

                return BEGINNING;
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
        if ((phase == Phase.PRECOMBATMAIN || phase == Phase.POSTCOMBATMAIN) ) {
            if(c.getType() == Card.Type.LAND && landPlayed) {
                return false;
            }
            return true;
        }


        return false;
    }
    public boolean isCardPlayable(Card c){
        if(pMana >= c.getCost()) {
            if (phase == Phase.PRECOMBATMAIN || phase == Phase.POSTCOMBATMAIN) {
                return true;
            }
            else if (c.getType() == Card.Type.INSTANT && phase == Phase.RESPONDING) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Card c){
        if(isCardPlayable(c)){
            pMana -= c.getCost();
            if(c.getType()== Card.Type.CREATURE){
                pBoard.add(new Permanent(c));
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
                landPlayed=true;
                pMana++;
                player.remove(c);
            }
        }
    }
    public void oLandPlayed(){
        oMana++;
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
    public static boolean isGameOver(){
        return (pHP<1||oHP<1||player.getLibrary().getCardsLeft()<1||oCards<1);
    }
    public static void mulligan(){

        for (int i = 0; i < (7-timesMulled); i++) {
            player.drawCard();

        }
        //timesMulled++;

    }
    public static void returnCards(){
        int handSize = player.getHand().size();
        for (int i = 0; i< handSize; i++) {
            player.getLibrary().addCard((Card)player.getHand().remove(0));
        }
    }




}
