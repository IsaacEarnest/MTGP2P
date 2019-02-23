package com.example.mtg.networking;

import android.util.Log;

import java.io.IOException;

public class Singleton {
    private static final String TAG = "SINGLETON";
    private static final Singleton ourInstance = new Singleton();
    private Server server;



    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        createServer();
    }

    private void createServer(){
            try {
                server = new Server();
            } catch (IOException e) {
                //Log.e(TAG, "Could not start server");
                System.out.println("bad things");
                e.printStackTrace();

            }


    }

    public Server getServer(){
        return server;
    }

    public void listen(ServerListener serverListener) throws IOException {
        server.addListener(serverListener);
        server.listen();
    }





}
