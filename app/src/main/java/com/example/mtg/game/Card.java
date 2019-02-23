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
                if(str.equals("LAND"))
                    return LAND;
                else return CREATURE.toType(str);
            }
        },
        CREATURE{
            @Override
            void playCard(Card c) {
                //add to stack
            }
            @Override
            Type toType(String str){
                if(str.equals("CREATURE"))
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
                if(str.equals("ENCHANTMENT"))
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
                if(str.equals("INSTANT"))
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
                if(str.equals("SORCERY"))
                    return SORCERY;
                else return LAND.toType(str);
            }
        };
        abstract void playCard(Card c);
        abstract Type toType(String str);
    }

    String name;
    Type type;
    int cost;
    public Card(String name, Type type,int cost){
        this.name = name;
        this.type = type;
        this.cost = cost;
    }
    public int getCost(){
        return cost;
    }
    @Override
    public String toString(){
        return name+"_"+type+"_"+cost;
    }
    public Card parse(String str){
        String[] parsed = str.split("_");
        return new Card(parsed[0],Type.CREATURE.toType(parsed[1]),Integer.parseInt(parsed[2]));
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




}
