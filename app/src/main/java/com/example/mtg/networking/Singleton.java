package com.example.mtg.networking;



import android.app.Activity;
import android.content.Context;

import com.example.mtg.activities.ActivityJoinLobby;

import java.io.IOException;
import java.net.Socket;

public class Singleton {
    private static final String TAG = "SINGLETON";
    private static final Singleton ourInstance = new Singleton();
    private Server server;
    private String opponentIP = "none";



    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createServer();
    }

    private void createServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    server.addListener(new ServerListener() {
                        @Override
                        public void notifyMessage(String msg) {
                            //TODO: handle IP address
                        }
                    });
                    server.listen();
                } catch (IOException e) {
                    //Log.e(TAG, "Could not start server");
                    System.out.println("bad things");
                    e.printStackTrace();

                }
            }
        }).start();

    }

    public void sendOverSocket(final String message, final Activity activity) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(opponentIP, Server.APP_PORT);

                    Communication.sendOver(socket, message);

                    socket.close();

                } catch (final IOException e) {
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

    public Server getServer(){
        return server;
    }

    public void addlistener(ServerListener serverListener) {
        server.addListener(serverListener);
        //server.addlistener();
    }

    public void setOpponentIP(String ip){
        this.opponentIP = ip;
    }







}
