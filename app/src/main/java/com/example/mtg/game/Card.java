package com.example.mtg.game;

public class Card {
    enum Type{
        LAND{
            @Override
            void playCard(Card c) {
                //increase mana by 1, remove from hand

            }
            @Override
            Type toType(String str){
                if(str.contains("Land"))
                    return LAND;
                else return CREATURE.toType(str);
            }
        },
        CREATURE{
            int health, power;
            @Override
            void playCard(Card c) {
                //add to stack
            }
            public int getPermanentPower(){
                return power;
            }
            public int getPermanentHealth(){
                return health;
            }
            @Override
            Type toType(String str){
                if(str.contains("Creature"))
                    return CREATURE;
                else return ENCHANTMENT.toType(str);
            }
        },
        ENCHANTMENT{
            @Override
            void playCard(Card c) {
                //add to stack
            }
            @Override
            Type toType(String str){
                if(str.contains("Enchantment"))
                    return ENCHANTMENT;
                else return INSTANT.toType(str);
            }
        },
        INSTANT{
            @Override
            void playCard(Card c) {
                //add to stack
            }
            @Override
            Type toType(String str){
                if(str.contains("Instant"))
                    return INSTANT;
                else return SORCERY.toType(str);
            }
        },
        SORCERY{
            @Override
            void playCard(Card c) {
                //add to stack
            }
            @Override
            Type toType(String str){
                if(str.contains("Sorcery"))
                    return SORCERY;
                else return LAND.toType(str);
            }
        },
        ERROR{
            @Override
            void playCard(Card c) {}
            @Override
            Type toType(String str){
                return ERROR;
            }
        };
        abstract void playCard(Card c);
        abstract Type toType(String str);
    }

    private String name;
    private Type type;
    private int cost;
    public Card(String name, Type type,int cost){
        this.name = name;
        this.type = type;
        this.cost = cost;
    }
    public Card(String data){
        parse(data);
    }

    public int getCost(){
        return cost;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return name+"_"+type+"_"+cost;
    }

    public Card parse(String str){
        String[] parsed = str.split("_");
        return new Card(parsed[0],Type.LAND.toType(parsed[1]),Integer.parseInt(parsed[2]));

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

    public void onCreaturePlayed(Card c){
        String s = c.getName();
        if(s.equals("Nimble Innovator")){
            //draw 1
        }else if(s.equals("Angler Drake")){
            //return creature to owner's hand
        }
    }
    public void onInstantPlayed(Card c){
        String s = c.getName();
        if(s.equals("Inspiration")){
            //draw 2
        }
    }
    public void onEnchantmentPlayed(Card c){
        String s = c.getName();
        if(s.equals("Sleep Paralysis")){
            //tap opposing creature, doesn't untap
        }else if(s.equals("Tricks of the Trade")){
            //+2 power, attacks can't be blocked
        }
    }
    public void onSorceryPlayed(Card c){
        String s = c.getName();
        if(s.equals("Drag Under")){
            //return target creature to owner's hand, draw 1
        }
    }








}
