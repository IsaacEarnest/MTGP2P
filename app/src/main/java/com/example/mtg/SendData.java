package com.example.mtg;

import java.net.Socket;

public class SendData {
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
}
