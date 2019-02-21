package com.example.mtg;

import android.content.Context;

import java.net.Socket;

public class SendData {
    Context activity;
    String className;
    public SendData(Context activity, String name){
        this.activity = activity;
        className = name;
    }
    
    public void send(final String message, final String host, final int port) {
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Socket target = new Socket(host, port);
//                    Communication.sendOver(target, message);
//                    showIncoming(Communication.receive(target));
//                    target.close();
//                } catch (final Exception e) {
//                    SendData.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Utilities.notifyException(SendData.this, e);
//                        }
//                    });
//                }
//
//            }
//        }.start();
    }
    /*
    private void showIncoming(final String msg) {
        .this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                incoming.setText(msg);
            }
        });
    }
*/

}
