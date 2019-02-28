package com.example.mtg.game;

import java.util.ArrayList;

public class Game {
    Player player, opponent;
    static int timesMulled =0;
    private String deckColor;
    //ArrayList<Card> =
    int mana;
    boolean landPlayed = false;
    State state;
    public Game(String library){
        this.deckColor = library;
        //give deck name, call parseJSON(deckname) and it gets all cards in deck as string,
        initializeDeck(deckColor);

    }
    public enum State {
        MULLIGAN{
          @Override
          State doSomething(String aParameter){

              //drawFullHand();
              //listener for mulligan/keep
              //drawFullHand();
              return BEGINNING;
          }
        },
        BEGINNING {
            @Override
            State doSomething(String aParameter) {
               // if(isGameOver())


                //untap();
                //upkeep();
                //player.draw(deck);
                System.out.println("Doing Something in INITIAL state and jumping to NEXT_STEP, argument = " + aParameter);
                return PRECOMBATMAIN;
            }
        },
        PRECOMBATMAIN {
            @Override
            State doSomething(String aParameter) {
                //wait for player to play card
                System.out.println("Doing Something in NEXT_STEP and jumping into FINAL, argument = " + aParameter);
                return COMBAT;
            }
        },
        COMBAT {
            @Override
            State doSomething(String aParameter) {
                System.out.println("Doing Something in NEXT_STEP and jumping into FINAL, argument = " + aParameter);
                return POSTCOMBATMAIN;
            }
        },
        POSTCOMBATMAIN {
            @Override
            State doSomething(String aParameter) {
                //identical to PRECOMBATMAIN
                System.out.println("Doing Something in NEXT_STEP and jumping into FINAL, argument = " + aParameter);
                return ENDING;
            }
        },
        ENDING {
            @Override
            State doSomething(String aParameter) {
                //players are technically allowed to perform
                System.out.println("I am in FINAL state, argument = " + aParameter);
                return OPPONENT_TURN;
            }
        },
        RESPONDING {
            @Override
            State doSomething(String aParameter) {
                System.out.println("Doing Something in NEXT_STEP and jumping into FINAL, argument = " + aParameter);
                return OPPONENT_TURN;
            }
        },
        OPPONENT_TURN {
            //wait for opponent to send an action for a chance to respond, or
            @Override
            State doSomething(String aParameter) {

                return BEGINNING;
            }
        },
        GAME_OVER {
            @Override
            State doSomething(String aParameter) {
                return null;
            }
        };

        abstract State doSomething(String aParameter);
    }



    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return state;
    }

    public boolean isPlayable(Card c){
        if(mana >= c.getCost()) {
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
        Card.Type type =c.getType();
    }
    public void playLand(Card c){
        if(player.handContains(c)){
            if(c.getType()== Card.Type.LAND){
                //increase playerMana by 1
                player.remove(c);
            }
        }
    }
    public ArrayList getpHand(){
        return player.getHand();
    }
    public boolean isGameOver(){
        //check if either hp value is <1
        return false;
    }
    public void mulligan(){
        player.drawCard();
    }
    public void initializeDeck(String color){
        if(color.equals("Red")){

        }else if(color.equals("Blue")){

        }
    }


}
