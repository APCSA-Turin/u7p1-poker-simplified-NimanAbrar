package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){ //initialize hand and all cards lists
        hand = new ArrayList<Card>();
        allCards = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c); //add card
    }

    public String playHand(ArrayList<Card> communityCards){
        allCards.clear(); //clear list to make it smoother
        allCards.addAll(hand); //add hand
        allCards.addAll(communityCards); //add community cards
        sortAllCards(); //sort by descending order
            if (royalFlush()) { //check each hand by descending order if it matches
                return "Royal Flush";
            } else if (straightFlush()) {
                return "Straight Flush";
            } else if (fourOfAKind()) {
                return "Four of a Kind";
            } else if (fullHouse()) {
                return "Full House";
            } else if (Flush()) {
                return "Flush";
            } else if (Straight()) {
                return "Straight";
            } else if (threeOfAKind()) {
                return "Three of a Kind";
            } else if (twoPair()) {
                return "Two Pair";
            } else if (onePair()) {
                return "A Pair";
            } else {
                if (highCard()) {
                    return "High Card";
                }
                return "Nothing"; //if hand doesn't match the above outcomes then it is nothing
            }
        }
    public void sortAllCards(){
        for (int i = 0; i < allCards.size(); i++) { //selection sort 
            int idx = i;
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(idx).getRank())) { //find minimum rank
                    idx = j;
                }
            }
            Card min = allCards.get(idx); //swap
            allCards.set(idx, allCards.get(i));
            allCards.set(i, min);
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < ranks.length; i++) {
            frequency.add(0); //initalize all the values of frequency to 0
        }
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j < ranks.length; j++) {
                if (allCards.get(i).getRank().equals(ranks[j])) {
                    frequency.set(j, frequency.get(j) + 1); //increment count by 1
                    break; //terminate
                }
            }
        }
        return frequency; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        for (int i = 0; i < suits.length; i++) {
            frequency.add(0);//initialize all values of frequency to 0 
        }
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j < suits.length; j++) {
                if (allCards.get(i).getSuit().equals(suits[j])) {
                    frequency.set(j, frequency.get(j) + 1); //increment count by 1
                    break; //terminate
                }
            }
        }
        return frequency;
    }
    //boolean methods
    public boolean onePair() {
       for (int i = 0; i < findRankingFrequency().size(); i++) {
        if (findRankingFrequency().get(i) == 2) { //if get i of frequency = 2 then there is a pair
            return true;
        }
       }
       return false; 
    }
    public boolean twoPair() {
        int count = 0;
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 2) { //if get i of frequency = 2 then there is a pair
                count++;
            }
        }
        if (count == 2) { //if occurences of pairs is 2 then it is true
            return true;
        }
        return false;
    }
    public boolean threeOfAKind() {
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 3) { //if get i of frequency = 2 then it is 3 of a kind
                return true;
            }
           }
           return false; 
    }
    public boolean Straight() {
        int count = 0;
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 1) { //check for instances
                count++;
                if (count == 5) { //if there are 5 counts of instances in a row then it is true
                    return true;
                }
            } else { //if there is no instance then count resets or returns false
                count = 0;
            }
        }
        return false;
    }
    public boolean Flush() {
        for (int i = 0; i < findSuitFrequency().size(); i++) {
            if (findSuitFrequency().get(i) == 5) { //if get i of suit frequency = 5 then returns true
                return true;
            }
        }
        return false;
    }
    public boolean fullHouse() {
        boolean two = false;
        boolean three = false;
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 2) { //checks if there is pair
                two = true;
            } else if (findRankingFrequency().get(i) == 3) { //checks if there is three of a kind
                three = true;
            }
        }
        if (two && three) { //returns true if both are true
            return true;
        }
        return false;
    }
    public boolean fourOfAKind() {
        for (int i = 0; i < findRankingFrequency().size(); i++) {
            if (findRankingFrequency().get(i) == 4) { //if get i of frequency = 4 then it is true
                return true;
            }
        }
        return false;
    }
    public boolean straightFlush() {
        if (Straight() && Flush()) { //if straight and flush returns true
            return true;
        }
        return false; //false otherwise
    }
    public boolean royalFlush() {
        boolean royal = false;
        for (int i = 8; i < findRankingFrequency().size(); i++) { //starts at 8 since that is the minimum value or 10
            if (findRankingFrequency().get(i) != 1) { //if there are no instances then it must be false since it has to run from king to ace
                return false;
            }
        }
        royal = true;
        if (royal && Flush()) { //if royal and flush then it is true
            return true;
        }
        return false;
    }
    public boolean highCard() {
        int maxRankValue = -1;
        for (Card card : allCards) { //iterate through every card in all cards
            int cardValue = Utility.getRankValue(card.getRank());
            if (cardValue > maxRankValue) { //find max value
                maxRankValue = cardValue;
            }
        }
        for (Card card : hand) {
            if (Utility.getRankValue(card.getRank()) == maxRankValue) { //if max is in hand and not community cardsthen it's true
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString(){
        return hand.toString();
    }
    public static void main (String[] args) { //testing purposes
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("9", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("9", "♥"));
        communityCards.add(new Card("A", "♦"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        System.out.println(handResult);
    }
}
