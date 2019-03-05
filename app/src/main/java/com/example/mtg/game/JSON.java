package com.example.mtg.game;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mtg.activities.ActivityGameBoard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSON {

    private RequestQueue mRequestQueue;
    private final String blueDeck = "https://mtgjson.com/json/decks/AmonkhetWelcomeDeckBlue.json";
    private final String redDeck = "https://mtgjson.com/json/decks/AmonkhetWelcomeDeckRed.json";
    private final ArrayList<String> cards = new ArrayList<>();
    public static String tag = "JSON";

    public JSON (Context con){
        mRequestQueue = Volley.newRequestQueue(con);
    }

    public ArrayList<String> parseJSON(String color) {

        final String url;
        final String name;
        switch (color) {
            case"blueDeck":{
                url = blueDeck;
                name = "mainBoard";
                break;
            }
            case "redDeck": {
                url = redDeck;
                name = "mainBoard";
                break;
            }
            default:{
                url = "https://mtgjson.com/json/AKH.json";
                name = "cards";
                break;
            }
        }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray(name);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);
                                    String s = hit.getString("type");
                                    s = s.split(" — ")[0];
                                    if(s.equals("Basic Land")){
                                        s = "Land";
                                    }
                                    String id =hit.getString("name") + ":" + s + ":" + hit.getString("convertedManaCost")+":"+hit.getString("count");
                                    if(id.contains("Creature")){
                                       id = id + ":" + hit.getString("power") + ":" + hit.getString("toughness");
                                    }

                                    cards.add(id);
                                    Log.d(tag,cards.toString());

                                }

                            } catch (JSONException e) {
                                Log.e(tag, "Oops");
                                e.printStackTrace();

                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }

            });
            mRequestQueue.add(request);

    return cards;
    }
public interface updateList{
        void update(String id);
    }
}
//TODO http://www.magicspoiler.com/mtg-news/amonkhet-welcome-decks/ red and blue
