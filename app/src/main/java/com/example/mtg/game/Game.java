package com.example.mtg.game;

import com.example.mtg.game.Card;
import com.example.mtg.game.Deck;

public class Game {
    //code placed in here may be moved to main, but working on it here to avoid GitHub's problems
    Deck p1Library, p2Library, p1Hand, p2Hand, p1Graveyard, p2Graveyard;
    int mana;
    boolean landPlayed = false;
    State state;
    public enum State {

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

    public boolean canPlayCard(Card c){
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
}
