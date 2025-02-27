package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){ //hint.. use the utility class
        for (int s = 0; s < 4; s++) {
            for (int r = 0; r < 13; r++) {
                Card newCard = new Card(Utility.getRanks()[r], Utility.getSuits()[s]); 
                cards.add(newCard);
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public  Card drawCard(){
        for (int s = 0; s < 4; s++) {
            
        } 
       return new Card("","");
    }

    public  boolean isEmpty(){
        return cards.isEmpty();
    }

   


}