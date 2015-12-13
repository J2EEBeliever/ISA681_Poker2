/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import deck.cards.DeckOfCards;
import java.io.Serializable;

/**
 *
 * @author b1wolt
 */
public class DeckContainer implements Serializable{
    private DeckOfCards deck = new DeckOfCards();
   
    public synchronized DeckOfCards getDeck()
    {
        return deck.Copy();
    }
    
        public synchronized void setDeck(DeckOfCards deck)
    {
        this.deck = deck.Copy();
    }
    
}
