package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<Card>();
        allCards = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards.add(hand.get(0));
        allCards.add(hand.get(1));
        allCards.add(communityCards.get(0));
        allCards.add(communityCards.get(1));
        allCards.add(communityCards.get(2));

        return "Nothing";
    }

    public void sortAllCards(){
        for (int i = 0; i < allCards.size(); i++) {
            int idx = i;
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(idx).getRank())) {
                    idx = j;
                }
            }
            Card min = allCards.get(idx);
            allCards.set(idx, allCards.get(i));
            allCards.set(i, min);
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        int[] frequency = new int[ranks.length - 1];
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j <= ranks.length; j++) {
                if (allCards.get(i).getRank().equals(ranks[j])) {
                    frequency[j]++;
                    break;
                }
            }
        }
        ArrayList<Integer> frequencyList = new ArrayList<Integer>();
        for (int i = 0; i < frequency.length; i++) {
            frequencyList.add(frequency[i]);
        }
        return frequencyList; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        int[] frequency = new int[suits.length - 1];
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j < suits.length; j++) {
                if (allCards.get(i).getSuit().equals(suits[j])) {
                    frequency[j]++;
                    break;
                }
            }
        }
        ArrayList<Integer> frequencyList = new ArrayList<Integer>();
        for (int i = 0; i < frequency.length; i++) {
            frequencyList.add(frequency[i]);
        }
        return frequencyList;
    }
    public boolean onePair() {
       for (int i = 0; i < findRankingFrequency().size(); i++) {
        if (findRankingFrequency().get(i) == 2) {
            return true;
        }
       }
       return false; 
    }
    public boolean twoPair() {
        int count = 0;
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i))
        }
    }
    @Override
    public String toString(){
        return hand.toString();
    }




}
