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
    private Stack<Card> deck = new Stack<>();

    
    public DeckOfCards()
    {
       
       createDeck();
    }
    
    private DeckOfCards(Stack<Card> deck)
    {
        this.deck = deck;
    }
    
    private void createDeck()
    {
          for (int i = 1 ; i<=52; i++)
        {
            deck.push(new Card(i));
        }
    }
    
    public DeckOfCards(boolean continuous)
    {
        if(continuous)
        {
        DeckOfCards ContinDeck = (DeckOfCards)RandomContainerEnum.INSTANCE.continuousDeck.getDeck();
        this.deck = ContinDeck.deck;
        continuousShuffle();
        }
        else
        {
            createDeck();
        }
        }
        
 
    
    public void shuffle()
    {

       Collections.shuffle(deck,RandomContainerEnum.INSTANCE.RandomContainer.getRandom());
    }
    
    private void continuousShuffle()
    {
       
        Collections.shuffle(deck,RandomContainerEnum.INSTANCE.RandomContainer.getRandom());
        RandomContainerEnum.INSTANCE.continuousDeck.setDeck(this);
    }
    
    public Card draw()
    {
       
        
        return deck.pop();
    }
   
    public DeckOfCards Copy()
    {
        return new DeckOfCards((Stack)deck.clone());
    }
    
}
