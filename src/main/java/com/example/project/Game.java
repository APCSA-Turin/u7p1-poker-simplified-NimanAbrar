package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1Rank = Utility.getHandRanking(p1Hand); //find hands of each player to compare
        int p2Rank = Utility.getHandRanking(p2Hand); 
        if (p1Rank > p2Rank) { //greater hand from player 1= player 1 win
            return "Player 1 wins!";
        } else if (p2Rank > p1Rank) { //greater hand from player 2 = player 2 win
            return "Player 2 wins!";
        } else if (p1Rank == p2Rank) { //if equal you decide who has the greater rank
            for (int i = 0; i < p1.getHand().size(); i++) { //only look at hand not community cards
                int p1CardValue = Utility.getRankValue(p1.getHand().get(i).getRank()); 
                int p2CardValue = Utility.getRankValue(p2.getHand().get(i).getRank());
                if (p1CardValue > p2CardValue) { //compare highest card
                    return "Player 1 wins!";
                } else if (p2CardValue > p1CardValue) {
                    return "Player 2 wins!";
                }
            }
        }
        return "Tie!"; //if result doesn't match any outcomes above then it is a tie
}

    public static void play(){ //simulate card playing
    
    }
}
