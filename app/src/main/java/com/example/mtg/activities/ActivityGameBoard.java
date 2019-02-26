package com.example.mtg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.game.Card;
import com.example.mtg.game.Deck;
import com.example.mtg.game.Game;

public class ActivityGameBoard extends AppCompatActivity {
    TextView opponentMana;
    Button playLand,playCard,nextPhase,confirm,nextCard,prevCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        opponentMana.findViewById(R.id.opponentMana);
        playLand.findViewById(R.id.playLand);
        playCard.findViewById(R.id.playCard);
        nextPhase.findViewById(R.id.nextPhase);
        confirm.findViewById(R.id.confirm);
        nextCard.findViewById(R.id.nextCard);
        prevCard.findViewById(R.id.prevCard);


    }


}

