package com.example.mtg;

import android.app.Activity;
import android.content.Context;

import java.net.Socket;

public class SendData {
    private Activity activity;

    public SendData(Activity activity){
        this.activity = activity;
    }

    public void send(final String message, final String host, final int port) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Socket target = new Socket(host, port);
                    Communication.sendOver(target, message);
                    showIncoming(Communication.receive(target));
                    target.close();
                } catch (final Exception e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilities.notifyException(activity, e);
                        }
                    });
                }

            }
        }.start();
    }
<<<<<<< HEAD



=======
    /*
>>>>>>> a70d1be93b01621c7e1df445ab85168ffe91230e
    private void showIncoming(final String msg) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
*/

}
