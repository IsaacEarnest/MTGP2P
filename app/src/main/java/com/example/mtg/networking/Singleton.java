package com.example.mtg.networking;



import java.io.IOException;
import java.net.Socket;

public class Singleton {
    private static final String TAG = "SINGLETON";
    private static final Singleton ourInstance = new Singleton();
    private Server server;



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

    public Server getServer(){
        return server;
    }

    public void addlistener(ServerListener serverListener) {
        server.addListener(serverListener);
        //server.addlistener();
    }







}
