package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();
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
        if (cards.size() ==0) {
            return null;
        }
        return cards.remove(0);
    }

    public boolean isEmpty(){
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != null) {
                return true;
            }
        }
        return false;
    }

   


}