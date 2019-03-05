package com.example.mtg.game;

import com.example.mtg.activities.ActivityGameBoard;

import java.util.ArrayList;

public class Game {
    private static Player player;
    ArrayList<Permanent> pBoard;
    ArrayList<Permanent> oBoard;
    private static int timesMulled;
    private static int oCards;
    private String deckColor;
    private static int oMana, pMana, oHP, pHP;
    boolean landPlayed;
    State state;
    public Game(ArrayList cards, String library){
        this.deckColor = library;
        landPlayed = false;
        oHP = 20;
        pHP = 20;
        pMana = 0;
        oMana = 0;
        timesMulled = 0;
        oCards = 60;
        player = new Player(cards, library);
        state = State.MULLIGAN;

        //give deck name, call parseJSON(deckname) and it gets all cards in deck as string,
        //initializeDeck(deckColor);

    }
    public enum State {
        MULLIGAN{
          @Override
          State nextPhase(){
              if(!isGameOver()){
               mulligan();

              }
              //drawFullHand();
              //listener for mulligan/keep
              //drawFullHand();
              return BEGINNING;
          }
        },
        BEGINNING {
            @Override
            State nextPhase() {
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
            State nextPhase() {
                if(!isGameOver()){

                }
                //wait for player to play card
                return COMBAT;
            }
        },
        COMBAT {
            @Override
            State nextPhase() {
                if(!isGameOver()){

                }
                return POSTCOMBATMAIN;
            }
        },
        POSTCOMBATMAIN {
            @Override
            State nextPhase() {
                if(!isGameOver()){

                }
                //identical to PRECOMBATMAIN
                return ENDING;
            }
        },
        ENDING {
            @Override
            State nextPhase() {
                if(!isGameOver()){

                }
                //players are technically allowed to perform
                return OPPONENT_TURN;
            }
        },
        RESPONDING {
            @Override
            State nextPhase() {
                if(!isGameOver()){

                }
                return OPPONENT_TURN;
            }
        },
        OPPONENT_TURN {
            //wait for opponent to send an action for a chance to respond, or wait until opponent ends their turn
            @Override
            State nextPhase() {
                if(!isGameOver()){

                }

                return BEGINNING;
            }
        },
        GAME_OVER {
            @Override
            State nextPhase() {
                return GAME_OVER;
            }
        };

        abstract State nextPhase();
    }



    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return state;
    }
    public Player getPlayer(){
        return player;
    }

    public boolean isPlayable(Card c){
        if(pMana >= c.getCost()) {
            if (state == State.PRECOMBATMAIN || state == State.POSTCOMBATMAIN) {
                if(c.getType() == Card.Type.LAND && landPlayed == true) {
                    return false;
                }
                return true;
            }
            else if (c.getType() == Card.Type.INSTANT && state == State.RESPONDING) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Card c){
        if(isPlayable(c)){
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
        if(isPlayable(c)){
            if(c.getType()== Card.Type.LAND){
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
            timesMulled++;
        }

    }
    public static void returnCards(){
        int handSize = player.getHand().size();
        for (int i = 0; i< handSize; i++) {
            player.getLibrary().addCard((Card)player.getHand().remove(0));
        }
    }



}
