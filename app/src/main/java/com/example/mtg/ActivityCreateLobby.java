package com.example.mtg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityCreateLobby extends AppCompatActivity {

    public TextView connectionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);
        //call the recieve data method and update the user
        connectionStatus = findViewById(R.id.connectionStatus);


        RecieveData recieveData = new RecieveData(this);
        recieveData.openConnection();




    }
}
