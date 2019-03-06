package com.example.mtg.networking;



import org.junit.Test;


import java.net.Socket;

import static org.junit.Assert.*;

public class RecieveDataTest {
    //created by David Pojunas
    //create THE ONE and ONLY server
    public static String TAG = "RECIEVEDATATEST";


    @Test
    public void test_simple_send_receive() throws Exception {

        final String testMsg = "hello\n";
       Singleton.getInstance().addlistener(new ServerListener() {
            @Override
            public void notifyMessage(String msg) {
                assertEquals(testMsg, msg);
            }
        });

        //gets the socket to send information over
        //"local host" in actual implementation will be another device not the local
        Socket test = new Socket("localhost", Server.APP_PORT);
        //sends testMsg over the socket
        Communication.sendOver(test, testMsg);
    }
}