package com.example.mtg.game;

import com.example.mtg.activities.ActivityGameBoard;

public class Game {
    Deck pLibrary, oLibrary, pHand, oHand, pGraveyard, oGraveyard;
    int mana;
    boolean landPlayed = false;
    State state;
    public Game(String library){
        //give deck name, call parseJSON(deckname) and it gets all cards in deck as string,
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
        },;

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
        if(pHand.contains(c)){
            if(c.getType()== Card.Type.LAND){
                //increase playerMana by 1
                pHand.remove(c);
            }
        }
    }
    public Deck getpHand(){
        return pHand;
    }

}
