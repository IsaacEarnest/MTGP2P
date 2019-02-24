package com.example.mtg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.Communication;
import com.example.mtg.networking.Server;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;
import com.example.mtg.networking.Utilities;

import java.io.IOException;
import java.net.Socket;

public class ActivityCreateLobby extends AppCompatActivity implements ServerListener {
    private static final String TAG = "CREATELOBBY";

    public TextView connectionStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);
        //call the recieve data method and update the user
        setUpGUI();
        implementListener();
    }

    private void setUpGUI(){
        connectionStatus = findViewById(R.id.connectionStatus);
    }

    private void implementListener(){

        Singleton.getInstance().addlistener(this);

    }

    private void showIncoming(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                connectionStatus.setText(msg);
            }
        });

    }

    public void acceptRequest(View view) {
        if (connectionStatus.getText().equals("Connection Pending")){
            Utilities.notifyMessage(this, "No one has connected with you yet");
        }

    }

    public void playGameP1(View view) {
        //TODO: implement the PlayGame Button P1
        //this will check the text of connectionStatus
        // and if connectionStatus is the correct message it will go to the next screen

        //need to pass IP address to the next activity
    }

    @Override
    public void notifyMessage(String msg) {
        //TODO: actually parse this!
        Log.d(TAG, msg);
        showIncoming(msg);
    }
}
