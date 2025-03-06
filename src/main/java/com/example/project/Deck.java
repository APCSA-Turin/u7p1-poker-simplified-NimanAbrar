package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();
        initializeDeck();
        shuffleDeck(); //shuffle deck for randomness
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){ //hint.. use the utility class
        for (int s = 0; s < 4; s++) { //iterate through every Suit
            for (int r = 0; r < 13; r++) { //iterate through every rank
                Card newCard = new Card(Utility.getRanks()[r], Utility.getSuits()[s]); //generate new card with unique suit or rank
                cards.add(newCard);
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public  Card drawCard(){
        if (cards.size() ==0) { //return null if nothing is there
            return null;
        }
        return cards.remove(0); //returns the card being removed
    }

    public boolean isEmpty(){
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != null) { //if it is not null return true
                return true;
            }
        }
        return false;
    }

   


}