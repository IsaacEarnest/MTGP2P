package com.example.mtg.gui;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ImageHandler {

    private Context context;

    public ImageHandler(Context context){
        this.context = context;
    }

    public ArrayList<Drawable> buildRedDeck(){
        ArrayList<Drawable> k = new ArrayList<>();
        k.add(getImage(context, "red_wrangle"));
        k.add(getImage(context, "red_brazen_scourge"));
        k.add(getImage(context, "red_falkenrath_reaver"));
        k.add(getImage(context, "red_flame_lash"));
        k.add(getImage(context, "red_fling"));
        k.add(getImage(context, "red_frontline_rebel"));
        k.add(getImage(context, "red_hungry_flames"));
        k.add(getImage(context, "red_hyena_pack"));
        k.add(getImage(context, "red_mountain"));
        k.add(getImage(context, "red_renegade_tactics"));
        k.add(getImage(context, "red_shivan_dragon"));
        k.add(getImage(context, "red_shock"));
        k.add(getImage(context, "red_terror_of_the_fairgrounds"));
        k.add(getImage(context, "red_thundering_giant"));
        return k;
    }

    public ArrayList<Drawable> buildBlueDeck(){
        ArrayList<Drawable> k = new ArrayList<>();
        k.add(getImage(context, "blue_air_elemental"));
        k.add(getImage(context, "blue_ancient_crab"));
        k.add(getImage(context, "blue_angler_drake"));
        k.add(getImage(context, "blue_coral_merfolk"));
        k.add(getImage(context, "blue_drag_under"));
        k.add(getImage(context, "blue_inspiration"));
        k.add(getImage(context, "blue_island"));
        k.add(getImage(context, "blue_nimble_innovator"));
        k.add(getImage(context, "blue_sleep_paralysis"));
        k.add(getImage(context, "blue_sphinx_of_magosi"));
        k.add(getImage(context, "blue_stealer_of_secrets"));
        k.add(getImage(context, "blue_tricks_of_the_trade"));
        k.add(getImage(context, "blue_wind_drake"));
        return k;
    }

    public static Drawable getImage(Context context, String name) {
        return context.getResources().getDrawable(context.getResources().getIdentifier(name, "drawable", context.getPackageName()));
    }
}
