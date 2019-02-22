package com.example.mtg.networking;



import org.junit.Test;


import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;

public class RecieveDataTest {
    //create THE ONE and ONLY server
    public static String TAG = "RECIEVEDATATEST";
    public final Singleton s = Singleton.getInstance();

    @Test
    public void test_simple_send_receive() throws Exception {

        final String testMsg = "This is a test.\nThis is only a test.\n";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    s.getServer().listenOnce().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //gets the socket to send information over
        //"local host" in actual implementation will be another device not the local
        Socket test = new Socket("localhost", Server.APP_PORT);
        //sends testMsg over the socket
        Communication.sendOver(test, testMsg);
        //since we are sending it to ourselves we will receive on the same socket
        String result = Communication.receive(test);
        //check the message to check if the reply header and the message are the same
        assertEquals(SocketEchoThread.REPLY_HEADER + testMsg, result);

    }


    @Test
    public void test_Singleton_listen() throws IOException {
        final String testMsg = "This is a test.\nThis is only a test.\n";


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    s.listen(new ServerListener() {
                        @Override
                        public void notifyMessage(String msg) {
                            assertEquals(testMsg, msg);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        //open the socket and send the message
        Socket test = new Socket("localhost", Server.APP_PORT);
        Communication.sendOver(test, testMsg);








    }




}