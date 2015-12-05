/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681.CardDeck;

import org.apache.commons.math3.exception.OutOfRangeException;

/**
 *
 * @author b1wolt
 */
public class Card {
    //private int cardNum;
    private String Rank;
    private String Suit;

    
    public Card(int cardNum)
    {
       // this.cardNum = cardNum;
        switch (cardNum)
                {
            case 0:  Suit = "3";
                     Rank = "N";
                break;
            case 1:  Suit = "S";
                     Rank = "2";    
                break;
            case 2:  Suit = "S";
                     Rank = "3";    
                break;
            case 3:  Suit = "S";
                     Rank = "4";    
                break;
            case 4:  Suit = "S";
                     Rank = "5";    
                break;
            case 5:  Suit = "S";
                     Rank = "6";    
                break;
            case 6:  Suit = "S";
                     Rank = "7";    
                break;
            case 7:  Suit = "S";
                     Rank = "8";    
                break;
            case 8:  Suit = "S";
                     Rank = "9";
                break;
            case 9:  Suit = "S";
                     Rank = "10";    
                break;
            case 10: Suit = "S";
                     Rank = "J";
                break;
            case 11: Suit = "S";
                     Rank = "Q";    
                break;
            case 12: Suit = "S";
                     Rank = "K";
                break;
            case 13: Suit = "S";
                     Rank = "A";    
                break;
            case 14: Suit = "D";
                     Rank = "2";    
                break;
            case 15: Suit = "D";
                     Rank = "3";    
                break;
            case 16: Suit = "D";
                     Rank = "4";    
                break;
            case 17: Suit = "D";
                     Rank = "5";    
                break;
            case 18: Suit = "D";
                     Rank = "6";    
                break;
            case 19: Suit = "D";
                     Rank = "7";    
                break;
            case 20: Suit = "D";
                     Rank = "8";    
                break;
            case 21: Suit = "D";
                     Rank = "9";
                break;
            case 22: Suit = "D";
                     Rank = "10";    
                break;
            case 23: Suit = "D";
                     Rank = "J";
                break;
            case 24: Suit = "D";
                     Rank = "Q";    
                break;
            case 25: Suit = "D";
                     Rank = "K";
                break;
            case 26: Suit = "D";
                     Rank = "A";    
                break;
            case 27: Suit = "C";
                     Rank = "2";    
                break;
            case 28: Suit = "C";
                     Rank = "3";    
                break;
            case 29: Suit = "C";
                     Rank = "4";    
                break;
            case 30: Suit = "C";
                     Rank = "5";    
                break;
            case 31: Suit = "C";
                     Rank = "6";    
                break;
            case 32: Suit = "C";
                     Rank = "7";    
                break;
            case 33: Suit = "C";
                     Rank = "8";    
                break;
            case 34: Suit = "C";
                     Rank = "9";
                break;
            case 35: Suit = "C";
                     Rank = "10";    
                break;
            case 36: Suit = "C";
                     Rank = "J";
                break;
            case 37: Suit = "C";
                     Rank = "Q";    
                break;
            case 38: Suit = "C";
                     Rank = "K";
                break;
            case 39: Suit = "C";
                     Rank = "A";    
                break;
            case 40: Suit = "H";
                     Rank = "2";    
                break;
            case 41: Suit = "H";
                     Rank = "3";    
                break;
            case 42: Suit = "H";
                     Rank = "4";    
                break;
            case 43: Suit = "H";
                     Rank = "5";    
                break;
            case 44: Suit = "H";
                     Rank = "6";    
                break;
            case 45: Suit = "H";
                     Rank = "7";    
                break;
            case 46: Suit = "H";
                     Rank = "8";    
                break;
            case 47: Suit = "H";
                     Rank = "9";
                break;
            case 48: Suit = "H";
                     Rank = "10";    
                break;
            case 49: Suit = "H";
                     Rank = "J";
                break;
            case 50: Suit = "H";
                     Rank = "Q";    
                break;
            case 51: Suit = "H";
                     Rank = "K";
                break;
            case 52: Suit = "H";
                     Rank = "A";    
                break;
            case 53: Suit = "1";
                     Rank = "N";
                break;
            case 54: Suit = "2";
                     Rank = "N";    
                break;    
            default: throw new OutOfRangeException(cardNum,1,52);
                
        }
    }
    
  //  public int getCardNum()
  //  {
  //      return cardNum;
  //  }
    
    public String getSuit()
    {
        return Suit;
    }
    
    public String getRank()
    {
        return Rank;
    }
    
    public String getSuitString()
    {
        String returnVal = "";
        switch(Suit)
        {
            case "S": returnVal = "Spades";
                break;
            case "D": returnVal = "Diamonds";
                break;
            case "C": returnVal = "Clubs";
                break;
            case "H": returnVal = "Hearts";
                break;
            case "N": returnVal = "Non";
                break;
        }
        return returnVal;
    }
    
     public String getRankString()
    {
        String returnVal = "";
        switch(Rank)
        {
            case "2": returnVal = "Two";
                break;
            case "3": returnVal = "Three";
                break;
            case "4": returnVal = "Four";
                break;
            case "5": returnVal = "Five";
                break;
            case "6": returnVal = "Six";
                break;
            case "7": returnVal = "Seven";
                break;
            case "8": returnVal = "Eight";
                break;
            case "9": returnVal = "Nine";
                break;
            case "10": returnVal = "Ten";
                break;
            case "J": returnVal = "Jack";
                break;
            case "Q": returnVal = "Queen";
                break;
            case "K": returnVal = "King";
                break;
            case "A": returnVal = "Ace";
                break;
        }
        return returnVal;
    }
    
}
