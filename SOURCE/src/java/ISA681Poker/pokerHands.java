/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681Poker;

import ISA681.CardDeck.Card;
import ISA681.CardDeck.DeckOfCards;
import java.util.List;

/**
 *
 * @author b1wolt
 */
public class pokerHands {
   private DeckOfCards Deck;
    
   private List<Card> Player1Hand;
   private List<Card> Player2Hand;
   
   public void setDeckOfCards(DeckOfCards Deck)
   {
       this.Deck = Deck;
   }
   
   public void setPlayer1Hand1(List<Card> Player1Hand)
   {
       this.Player1Hand = Player1Hand;
   }
   
   public void setPlayer2Hand2(List<Card> Player2Hand)
   {
       this.Player2Hand = Player2Hand;
   }
    
   public DeckOfCards getDeckOfCards()
   {
       return Deck;
   }
   public List<Card> GetPlayer1Hand()
   {
       return Player1Hand;
   }
    
   public List<Card> GetPlayer2Hand()
   {
       return Player2Hand;
   }
   
}
