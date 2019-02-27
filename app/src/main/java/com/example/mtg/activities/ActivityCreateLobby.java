package com.example.mtg.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.IncomingMsg;
import com.example.mtg.networking.ParseRecieved;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;
import com.example.mtg.networking.Utilities;

public class ActivityCreateLobby extends AppCompatActivity implements ServerListener {
    private static final String TAG = "CREATELOBBY";

    public TextView connectionStatus;
    public IncomingMsg incomingMsg = IncomingMsg.NONE;


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
        }else{
            if(incomingMsg == IncomingMsg.IP){
                Singleton.getInstance().sendOverSocket("IP:\n", this);

            }
        }

    }

    public void playGameP1(View view) {
        if(connectionStatus.getText().equals("YOU HAVE BEEN SUCCESSFULLY CONNECTED!")){
            Intent intent = new Intent(this, ActivityChooseDeck.class);
            startActivity(intent);
        }
    }

    @Override
    public void notifyMessage(String msg) {

        IncomingMsg incomingMsg = ParseRecieved.getProtocol(msg);
        this.incomingMsg = incomingMsg;
        showIncoming("YOU HAVE BEEN SUCCESSFULLY CONNECTED!");
        String rawIP = ParseRecieved.cutMsg(msg);
        Singleton.getInstance().setOpponentIP(rawIP);


    }
}
