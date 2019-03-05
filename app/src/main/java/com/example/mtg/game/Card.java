package com.example.mtg.game;

import android.graphics.drawable.Drawable;

public class Card {
    enum Type{
        LAND{
            @Override
            Type toType(String str){
                if(str.contains("Land"))
                    return LAND;
                else return CREATURE.toType(str);
            }
        },
        CREATURE{
            @Override
            Type toType(String str){
                if(str.contains("Creature"))
                    return CREATURE;
                else return ENCHANTMENT.toType(str);
            }
        },
        ENCHANTMENT{
            @Override
            Type toType(String str){
                if(str.contains("Enchantment"))
                    return ENCHANTMENT;
                else return INSTANT.toType(str);
            }
        },
        INSTANT{
            @Override
            Type toType(String str){
                if(str.contains("Instant"))
                    return INSTANT;
                else return SORCERY.toType(str);
            }
        },
        SORCERY{
            @Override
            Type toType(String str){
                if(str.contains("Sorcery"))
                    return SORCERY;
                else return LAND.toType(str);
            }
        },
        ERROR{
            @Override
            Type toType(String str){
                return ERROR;
            }
        };
        abstract Type toType(String str);
    }
    private int health, power;
    private String name;
    private Type type;
    private int cost;
    private Drawable pic;
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
        this.pic = convertToDrawable();

    }
    public Card(String data){
        Card c = parse(data);
        name = c.getName();
        type = c.getType();
        cost = c.getCost();
        power = c.getPermanentPower();
        health = c.getPermanentHealth();
    }

    public int getCost(){
        return cost;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return name+":"+type+":"+cost;
    }

    public Card parse(String str){
        String[] parsed = str.split(":");
        if(Type.LAND.toType(parsed[1])== Type.CREATURE)
            return new Card(parsed[0],Type.LAND.toType(parsed[1]),(int)Double.parseDouble(parsed[2]), Integer.parseInt(parsed[3]), Integer.parseInt(parsed[4]));
        //Shivan Dragon:Creature:6.0:1:5:5
        else {
            return new Card(parsed[0], Type.LAND.toType(parsed[1]),(int)Double.parseDouble(parsed[2]));
        }

    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Card){
            Card that = (Card) other;
            return this.name.equals(that.name) && this.type == that.type && this.cost == that.cost;
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

    public void onCreaturePlayed(){
        String s = c.getName();
        if(s.equals("Nimble Innovator")){
            //draw 1
        }else if(s.equals("Angler Drake")){
            //return creature to owner's hand
        }
    }
    public void onInstantPlayed(){
        String s = c.getName();
        if(s.equals("Inspiration")){
            //draw 2
        }
    }
    public void onEnchantmentPlayed(){
        String s = c.getName();
        if(s.equals("Sleep Paralysis")){
            //tap opposing creature, doesn't untap
        }else if(s.equals("Tricks of the Trade")){
            //+2 power, attacks can't be blocked
        }
    }
    public void onSorceryPlayed(){
        String s = c.getName();
        if(s.equals("Drag Under")){
            //return target creature to owner's hand, draw 1
        }
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
