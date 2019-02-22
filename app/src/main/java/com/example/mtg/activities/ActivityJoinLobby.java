package com.example.mtg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.Communication;
import com.example.mtg.networking.SendData;
import com.example.mtg.networking.Server;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;

import java.io.IOException;
import java.net.Socket;

public class ActivityJoinLobby extends AppCompatActivity {
    private TextView joinStatus;
    private EditText IPnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_lobby);

        joinStatus = findViewById(R.id.joinStatus);
        IPnumber = findViewById(R.id.IPnumber);


        Singleton s = Singleton.getInstance();
        try {
            s.listen(new ServerListener() {
                @Override
                public void notifyMessage(String msg) {
                    showIncoming(msg);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showIncoming(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                joinStatus.setText(msg);
            }
        });
    }


    public void joinLobby(View view) {
        String number = IPnumber.getText().toString();
        String msg = number + ": I want to duel you";
        try {
            Socket test = new Socket(number, Server.APP_PORT);
            Communication.sendOver(test, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void playGameP2(View view) {
        //TODO: implement the PlayGame Button P2
        //this will check the text of joinStatus
        // and if joinStatus is the correct message it will go to the next screen

        //need to pass IP address to the next activity
    }
}
