package com.example.mtg.game;

import android.util.Log;

public class Card {
    enum Type{
        LAND{
            @Override
            Type getType(String str){
                if(str.contains("Land")||str.contains("LAND"))
                    return LAND;
                else return CREATURE.getType(str);
            }
        },
        CREATURE{
            @Override
            Type getType(String str){
                if(str.contains("Creature")||str.contains("CREATURE"))
                    return CREATURE;
                else return ENCHANTMENT.getType(str);
            }
        },
        ENCHANTMENT{
            @Override
            Type getType(String str){
                if(str.contains("Enchantment")||str.contains("ENCHANTMENT"))
                    return ENCHANTMENT;
                else return INSTANT.getType(str);
            }
        },
        INSTANT{
            @Override
            Type getType(String str){
                if(str.contains("Instant")||str.contains("INSTANT"))
                    return INSTANT;
                else return SORCERY.getType(str);
            }
        },
        SORCERY{
            @Override
            Type getType(String str){
                if(str.contains("Sorcery")||str.contains("SORCERY"))
                    return SORCERY;
                else return LAND.getType(str);
            }
        },
        ERROR{
            @Override
            Type getType(String str){
                return ERROR;
            }
        };
        abstract Type getType(String str);
    }
    private int health, power;
    private String name;
    private Type type;
    private int cost;
    public Card(String name, Type type,int cost){
        this.name = name;
        this.type = type;
        this.cost = cost;
    }
    public Card(String name, Type type,int cost, int atk, int hp){
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.power = atk;
        this.health = hp;
    }
    public Card(String data){
        Card c = parse(data);
        name = c.getName();
        type = c.getType();
        cost = c.getCost();
        if(type == Type.CREATURE) {
            power = c.getPermanentPower();
            health = c.getPermanentHealth();
        }
    }

    public int getCost(){
        return cost;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        if(type==Type.CREATURE){
            return name+":"+type+":"+cost+":"+power+":"+health;
        }
        return name+":"+type+":"+cost;
    }

    public Card parse(String str){
        String[] parsed = str.split(":");
        try {
            if (Type.LAND.getType(parsed[1]) == Type.CREATURE) {
                Log.d("CREATION", parsed[0] + Type.LAND.getType(parsed[1]) + (int) Double.parseDouble(parsed[2]) + Integer.parseInt(parsed[3]) + Integer.parseInt(parsed[4]));
                return new Card(parsed[0], Type.LAND.getType(parsed[1]), (int) Double.parseDouble(parsed[2]), Integer.parseInt(parsed[3]), Integer.parseInt(parsed[4]));
                //Shivan Dragon:Creature:6.0:1:5:5
            } else {
                return new Card(parsed[0], Type.LAND.getType(parsed[1]), (int) Double.parseDouble(parsed[2]));
            }
        }catch(NumberFormatException e){
            return new Card("island", Type.LAND.getType("LAND"), (int) Double.parseDouble("0.0"));
        }

    }
    public int parseCount(String str){
        String[] parsed = str.split(":");
        return Integer.parseInt(parsed[5]);
    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Card){
            Card that = (Card) other;
            return this.name.equals(that.name) && this.type == that.getType() && this.cost == that.cost;
        }else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    public Type getType() {
        return type;
    }
    public int getPermanentPower(){
        return power;
    }
    public int getPermanentHealth(){
        return health;
    }
    public String getDrawableName(){
        return name.toLowerCase().replaceAll(" ","_");
    }








}
