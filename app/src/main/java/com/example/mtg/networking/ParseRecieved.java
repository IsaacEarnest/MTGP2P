package com.example.mtg.networking;

public class ParseRecieved {
    enum Protocol {
        IP,
        ATTACKINGCARDS,
        CARDPLAYED,
        RESPONDING,
        NONE;
    }
    public static IncomingMsg getProtocol(String msg){
        if(msg.startsWith("IP:")){
            return IncomingMsg.IP;
        }else if(msg.startsWith("ATTACKINGCARDS:")){
            return IncomingMsg.ATTACKINGCARDS;
        }else if(msg.startsWith("CARDPLAYED:")){
            return IncomingMsg.CARDPLAYED;
        }else if(msg.startsWith("RESPONDING:")){
            return IncomingMsg.RESPONDING;
        }else return IncomingMsg.NONE;
    }
    public static String cutMsg(String msg){
        String[] split = msg.split(":");
        return split[1];
    }


}
