package com.example.mtg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.Communication;
import com.example.mtg.networking.Server;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;
import com.example.mtg.networking.Utilities;

import java.io.IOException;
import java.net.Socket;


public class ActivityJoinLobby extends AppCompatActivity implements ServerListener {
    private TextView joinStatus;
    private EditText IPnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_lobby);


        setUpGUI();
        implementListener();


    }

    private void setUpGUI(){
        joinStatus = findViewById(R.id.joinStatus);
        IPnumber = findViewById(R.id.IPnumber);
    }

    private void implementListener(){
            Singleton.getInstance().addlistener(this);

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
        String ip = IPnumber.getText().toString();
        try {
            Socket socket = new Socket(ip, Server.APP_PORT);
            Communication.sendOver(socket, "MY IP IS: "+ ip);
        } catch (IOException e) {
            Utilities.notifyProblem(this, "WAS UNABLE TO CONNECT TO PORT");
        }

    }

    public void playGameP2(View view) {
        //TODO: implement the PlayGame Button P2
        //this will check the text of joinStatus
        // and if joinStatus is the correct message it will go to the next screen

        //need to pass IP address to the next activity
    }

    @Override
    public void notifyMessage(String msg) {

    }
}
