package com.example.mtg.game;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {
    private RequestQueue mRequestQueue;

    public static String tag = "JSON";

    public JSON (Context con){
        mRequestQueue = Volley.newRequestQueue(con);
    }

    public void parseJSON() {



        String url = "https://mtgjson.com/json/GK2.json";
        Log.d(tag,url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("cards");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String id = hit.getString("type");
                                Log.d(tag,id);

                            }

                        } catch (JSONException e) {
                            Log.e(tag,"Oops");
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

    }
}
//TODO http://www.magicspoiler.com/mtg-news/amonkhet-welcome-decks/
