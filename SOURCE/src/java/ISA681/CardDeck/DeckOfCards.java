/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681.CardDeck;

import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author b1wolt
 */
public class DeckOfCards {
    private final Stack<Card> deck = new Stack<>();
    
    public DeckOfCards()
    {
       
        for (int i = 1 ; i<=52; i++)
        {
            deck.push(new Card(i));
        }
        
    }
    
    public void shuffle()
    {
       
       Collections.shuffle(deck);
    }
    
    public Card draw()
    {
        
        return deck.pop();
    }
    
    
}
