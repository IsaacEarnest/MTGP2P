package com.example.mtg.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mtg.R;
import com.example.mtg.networking.Communication;
import com.example.mtg.networking.Server;
import com.example.mtg.networking.ServerListener;
import com.example.mtg.networking.Singleton;

import java.io.IOException;
import java.net.Socket;

public class ActivityCreateLobby extends AppCompatActivity {

    public TextView connectionStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);
        //call the recieve data method and update the user
        connectionStatus = findViewById(R.id.connectionStatus);

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
                connectionStatus.setText(msg);
            }
        });

    }

    public void acceptRequest(View view) {

    }

    public void playGameP1(View view) {
        //TODO: implement the PlayGame Button P1
        //this will check the text of connectionStatus
        // and if connectionStatus is the correct message it will go to the next screen

        //need to pass IP address to the next activity
    }
}
