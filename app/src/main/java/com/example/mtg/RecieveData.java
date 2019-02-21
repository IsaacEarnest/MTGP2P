package com.example.mtg;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

public class RecieveData {

    public static String TAG = "RECIEVEDATA";

    private Activity activity;
    private String message;


    public RecieveData(Activity activity){
        this.activity = activity;
    }

    public void openConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server s = new Server();
                    s.addListener(new ServerListener() {
                        @Override
                        public void notifyMessage(String msg) {
                            //showIncoming(msg);
                        }
                    });
                    s.listen();

                } catch (IOException e) {
                    Log.e(TAG, "Could not start server");
                }
            }
        }).start();
    }


    private void showIncoming(final String msg) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //TODO: do something with msg
            }
        });
    }



}
