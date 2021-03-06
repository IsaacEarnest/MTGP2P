package com.example.mtg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.IncomingMsg;
import com.example.mtg.networking.ParseRecieved;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;
import com.example.mtg.networking.Utilities;

import java.net.SocketException;

public class ActivityCreateLobby extends AppCompatActivity implements ServerListener {
    private static final String TAG = "CREATELOBBY";

    public TextView connectionStatus;
    public Button playGame;
    public IncomingMsg incomingMsg = IncomingMsg.NONE;
    public TextView IPaddress;


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
        playGame = findViewById(R.id.create_PlayGame);
        IPaddress = findViewById(R.id.IPaddress);
        try {
           String ip =  Utilities.getLocalIpAddress();
           IPaddress.setText("Your IP: " + ip);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        playGame.setEnabled(false);

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
            playGame.setEnabled(true);

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
        //TODO: parse this
        IncomingMsg incomingMsg = ParseRecieved.getProtocol(msg);
        if(incomingMsg == IncomingMsg.IP) {
            this.incomingMsg = incomingMsg;
            showIncoming("YOU HAVE BEEN SUCCESSFULLY CONNECTED!");
            String rawIP = ParseRecieved.cutMsg(msg);
            Singleton.getInstance().setOpponentIP(rawIP);
        }



    }
}
