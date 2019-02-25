package com.example.mtg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.Communication;
import com.example.mtg.networking.IncomingMsg;
import com.example.mtg.networking.ParseRecieved;
import com.example.mtg.networking.Server;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;
import com.example.mtg.networking.Utilities;

import java.io.IOException;
import java.net.Socket;


public class ActivityJoinLobby extends AppCompatActivity implements ServerListener {
    private static final String TAG = "JOINLOBBY";
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

    private void send(final String ip) {
        Socket test = null;
        try {
            test = new Socket(ip, Server.APP_PORT);
            Communication.sendOver(test, "I RECEIVED YOUR REQUEST");
        } catch (IOException e) {
            e.printStackTrace();
        }

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


    public void playGameP2(View view) {
        if(joinStatus.getText().toString().equals("OPPONENT AS ACCEPTED YOUR REQUEST")){
            Utilities.notifyMessage(this, "READY TO PLAY");
        }
        //TODO: implement the PlayGame Button P2
        //this will check the text of joinStatus
        // and if joinStatus is the correct message it will go to the next screen

        //need to pass IP address to the next activity
    }

    @Override
    public void notifyMessage(String msg) {
        IncomingMsg incomingMsg = ParseRecieved.getProtocol(msg);
        if(incomingMsg == IncomingMsg.IP){
            showIncoming("OPPONENT AS ACCEPTED YOUR REQUEST");
        }

    }

    public void requestConnection(View view) {

        final String ip = IPnumber.getText().toString();
        final String IPprotocol = createIPprotocol(ip);
        Singleton.getInstance().setOpponentIP(ip);
        Log.d(TAG, IPprotocol);
        Singleton.getInstance().sendOverSocket(IPprotocol, this);
    }

    private String createIPprotocol(String ip){
        String protocol = "IP:\n" + ip;
        return protocol;
    }



}
