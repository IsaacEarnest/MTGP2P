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





    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choose_deck2);
            setUpButtons();
            setUpListViews();




    }

    private void setUpListViews(){
        listViewBlue = findViewById(R.id.ListView_blueDeck);
        listViewRed = findViewById(R.id.ListView_redDeck);



//        ArrayList<String> a = new ArrayList<>();
//        for(int i = 0; i < 20;i++){
//            a.add("a");
//        }

        CustomListViewAdapter adapter_red = new CustomListViewAdapter(this,
                R.layout.listview_list_decks, buildRedDeck());

        CustomListViewAdapter adapter_blue = new CustomListViewAdapter(this,
                R.layout.listview_list_decks, buildBlueDeck());

        listViewBlue.setAdapter(adapter_blue);
        listViewRed.setAdapter(adapter_red);
    }

    private ArrayList<Drawable> buildRedDeck(){
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
        return k;
    }

    private ArrayList<Drawable> buildBlueDeck(){
        ArrayList<Drawable> k = new ArrayList<>();
        k.add(getImage(this, "blue_air_elemental"));
        k.add(getImage(this, "blue_ancient_crab"));
        k.add(getImage(this, "blue_angler_drake"));
        k.add(getImage(this, "blue_coral_merfolk"));
        k.add(getImage(this, "blue_drag_under"));
        k.add(getImage(this, "blue_inspiration"));
        k.add(getImage(this, "blue_island"));
        k.add(getImage(this, "blue_nimble_innovator"));
        k.add(getImage(this, "blue_sleep_paralysis"));
        k.add(getImage(this, "blue_sphinx_of_magosi"));
        k.add(getImage(this, "blue_stealer_of_secrets"));
        k.add(getImage(this, "blue_trick_of_the_trade"));
        k.add(getImage(this, "blue_wind_drake"));
        return k;
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
