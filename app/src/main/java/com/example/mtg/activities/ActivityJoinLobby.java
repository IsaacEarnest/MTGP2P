package com.example.mtg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.IncomingMsg;
import com.example.mtg.networking.ParseRecieved;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;
import com.example.mtg.networking.Utilities;

import java.net.SocketException;


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
            Intent intent = new Intent(this, ActivityChooseDeck.class);
            startActivity(intent);
            //Utilities.notifyMessage(this, "READY TO PLAY");
        }
    }

    @Override
    public void notifyMessage(String msg) {
        //TODO: fix this bug
        Log.d(TAG, "RECIEVING" + msg);
        IncomingMsg incomingMsg = ParseRecieved.getProtocol(msg);
        if(incomingMsg == IncomingMsg.IP){
            showIncoming("OPPONENT AS ACCEPTED YOUR REQUEST");
        }

    }

    public void requestConnection(View view) {

        final String ip = IPnumber.getText().toString();

        if(IPnumber.getText().length() == 0){
            Utilities.notifyProblem(this, "PLEASE TYPE IN A VALID IP ADDRESS!");
        }else {
            final String brokenstuff = getIP();
            final String IPprotocol = createIPprotocol(brokenstuff);

            Singleton.getInstance().setOpponentIP(ip);
            Log.d(TAG, "SENDING" + IPprotocol);
            Singleton.getInstance().sendOverSocket(IPprotocol, this);
        }
    }

    private String createIPprotocol(String ip){
        String protocol = "IP:\n" + ip;
        return protocol;
    }

    private String getIP(){
        try {
            return Utilities.getLocalIpAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "none";

    }



}
