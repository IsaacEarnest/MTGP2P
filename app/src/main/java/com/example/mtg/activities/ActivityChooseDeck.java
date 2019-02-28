package com.example.mtg.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.mtg.R;

import java.util.ArrayList;

public class ActivityChooseDeck extends AppCompatActivity {

    private Button clickRedDeck;
    private Button clickBlueDeck;
    private Button clickPlayGame;
    private ListView listViewRed;
    private ListView listViewBlue;
    private CustomListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choose_deck2);
            setUpButtons();
            setUpListViews();




    }

    private void setUpListViews(){
        listViewBlue = findViewById(R.id.ListView_blueDeck);
        listViewRed = findViewById(R.id.ListView_redDeck);

        ArrayList<Drawable> k = new ArrayList<>();
        k.add(getImage(this, "red_wrangle"));
        k.add(getImage(this, "red_brazen_scourge"));
        k.add(getImage(this, "red_falkenrath_reaver"));
        k.add(getImage(this, "red_flame_lash"));
        k.add(getImage(this, "red_fling"));
        k.add(getImage(this, "red_frontline_rebel"));
        k.add(getImage(this, "red_hungry_flames"));
        k.add(getImage(this, "red_hyena_pack"));
        k.add(getImage(this, "red_mountain"));
        k.add(getImage(this, "red_renegade_tactics"));
        k.add(getImage(this, "red_shivan_dragon"));
        k.add(getImage(this, "red_shock"));
        k.add(getImage(this, "red_terror_of_the_fairground"));
        k.add(getImage(this, "red_thundering_giant"));

//        ArrayList<String> a = new ArrayList<>();
//        for(int i = 0; i < 20;i++){
//            a.add("a");
//        }

        adapter = new CustomListViewAdapter(this,
                R.layout.listview_list_decks, k);

        listViewBlue.setAdapter(adapter);
        listViewRed.setAdapter(adapter);
    }

    public static Drawable getImage(Context context, String name) {
        return context.getResources().getDrawable(context.getResources().getIdentifier(name, "drawable", context.getPackageName()));
    }



    private void setUpButtons(){
        clickRedDeck = findViewById(R.id.choose_redDeck);
        clickBlueDeck = findViewById(R.id.choose_blueDeck);
        clickPlayGame = findViewById(R.id.chooseDeck_PlayGame);
        clickPlayGame.setVisibility(View.INVISIBLE);
    }

    private void openActivityGameBoard() {
        Intent intent = new Intent(this, ActivityGameBoard.class);
        startActivity(intent);
    }

    public void clickRedDeck(View view) {
        clickPlayGame.setVisibility(View.VISIBLE);


    }

    public void clickBlueDeck(View view) {
        clickPlayGame.setVisibility(View.VISIBLE);
    }

    public void clickPlayGame(View view) {
        openActivityGameBoard();
    }
}
