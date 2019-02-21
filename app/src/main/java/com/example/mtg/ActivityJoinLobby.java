package com.example.mtg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityJoinLobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_lobby);
        SendData sendData = new SendData(this);
        sendData.send("Testing", "10.23.234.12", 8888);
    }
}
