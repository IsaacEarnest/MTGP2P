package com.example.mtg.networking;

public class ParseRecieved {
    enum Protocol {
        IP,
        ATTACKINGCARDS,
        CARDPLAYED,
        RESPONDING,
        NONE;
    }

    public static Protocol getProtocol(String msg){
        if(msg.startsWith("IP:")){
            return Protocol.IP;
        }else if(msg.startsWith("ATTACKINGCARDS:")){
            return Protocol.ATTACKINGCARDS;
        }else if(msg.startsWith("CARDPLAYED:")){
            return Protocol.CARDPLAYED;
        }else if(msg.startsWith("RESPONDING:")){
            return Protocol.RESPONDING;
        }else return Protocol.NONE;
    }
    public String cutMsg(String msg){
        String[] split = msg.split(":");
        return split[1];
    }

}
