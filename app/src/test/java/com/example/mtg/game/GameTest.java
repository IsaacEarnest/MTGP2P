package com.example.mtg.game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void isDrawableName(){
            //created by David Pojunas
            ArrayList cards = MasterCardClass.getInstance().getRedCards();

            Game game = new Game(cards, "red");
            // Brazen Scourge:Creature:3.0:1:3:3/red
            // Mountain:Land:0.0:13/red
            // Fling:Instant:2.0:1/red
            // Renegade Tactics:Sorcery:1.0:1/red
            game.toNextPhase();
            Card scourge = new Card("Brazen Scourge:Creature:3.0:1:3:3");
            assertEquals("brazen_scourge", scourge.getDrawableName());

    }
}