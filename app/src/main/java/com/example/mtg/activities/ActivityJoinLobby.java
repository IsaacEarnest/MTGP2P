package com.example.mtg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mtg.R;
import com.example.mtg.networking.SendData;

public class ActivityJoinLobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_lobby);
        SendData sendData = new SendData(this);
        sendData.send("Testing", "10.23.234.12", 8888);
    }
}
