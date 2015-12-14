package deck.cards;

import entities.RandomContainerEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import org.apache.log4j.Logger;

/*
 * Author: Linus Freeman
 * 
 */


public class DeckOfCards implements Serializable{
	
	final static Logger log = Logger.getLogger(DeckOfCards.class);


	final static int NUMBER_OF_CARDS = 52;
	
	final static int NUMBER_OF_CARDS_PER_SUIT = 13;

	
//	public static int NUMBER_OF_SUITS = 52;
	
	private ArrayList<PlayingCard>  deckOfCards = new ArrayList<PlayingCard>();
	
	private int nextCardToDealIndex =0;

	public DeckOfCards() {
		
		super();
		
		//this.deckOfCards = deckOfCards;
			
		processCardSuite(deckOfCards, "Spades" );
		processCardSuite(deckOfCards, "Clubs" );
		processCardSuite(deckOfCards, "Diamonds" );
		processCardSuite(deckOfCards, "Hearts" );
		
	}

        private DeckOfCards(ArrayList<PlayingCard> deck) 
        {
		
		super();
		
		//this.deckOfCards = deckOfCards;
			
		processCardSuite(deckOfCards, "Spades" );
		processCardSuite(deckOfCards, "Clubs" );
		processCardSuite(deckOfCards, "Diamonds" );
		processCardSuite(deckOfCards, "Hearts" );
                this.deckOfCards = deck;
        }
		
	public static void main(String[] args) {
		
		DeckOfCards deckOfCards = new DeckOfCards();
		
		log.debug("\nDebug: display deck of cards un-shuffled\n");
		
		log.debug(deckOfCards.displayDeckCards());
		
		deckOfCards.shuffleDeckCards();

		log.debug("\nDebug: display deck of cards shuffled\n");
	
		log.debug(deckOfCards.displayDeckCards());
		

	}
	
	
	
	public PlayingCard dealCard() {

		
		
		PlayingCard playingCard = deckOfCards.get(this.nextCardToDealIndex);
		
		++(this.nextCardToDealIndex);
		
		return playingCard;
		
	}
	
	public String toString() {
		
		StringBuffer stringBuffer = new StringBuffer("\n\n");
		
		for(int i = 0; i < NUMBER_OF_CARDS; ++i) {
			
			PlayingCard playingCard = deckOfCards.get(i);
			
			stringBuffer.append(playingCard.getCardNumber() + " " + playingCard.getCardString() + " " + playingCard.getCardSuit() + "\n");
			
		}
		
		return stringBuffer.toString();
	}
	
	public String displayDeckCards() {
		
		return this.toString();
	
		
	}

	
	public void shuffleDeckCards() {
		
		//Collections.shuffle(Arrays.asList(deckOfCards));
		
		Collections.shuffle(deckOfCards,RandomContainerEnum.INSTANCE.randomContainer.getRandom());
		
	}
	
	private void processCardSuite(ArrayList<PlayingCard> deckOfCards, String suit ) {

		for(int i = 0; i < NUMBER_OF_CARDS_PER_SUIT; ++i) {
			
			
			PlayingCard playingCard = new PlayingCard();
			
			deckOfCards.add(playingCard);

			
			//playingCard = new PlayingCard();
			
			
			playingCard.setCardNumber(i+1);
			
			playingCard.setCardSuit(suit);
			
			
//			if(i == 0) {
//				playingCard.setCardString("Zero Card Not Used");
//				playingCard.setCardSuit("Zero Card Not Used");
//				playingCard.setCardNumber(-1);
//			}
			if(i == 0) {
				playingCard.setCardString("Ace");
			}
			else if(i < 10) {
				playingCard.setCardString((i+1)+ "");
			}
			else if(i == 10) {
				playingCard.setCardString("Jack");
			}
			else if(i == 11) {
				playingCard.setCardString("Queen");
			}
			else {
				playingCard.setCardString("King");
			}
		}
		
		
	}
        
        @SuppressWarnings("unchecked")
        public DeckOfCards copy()
    {
      
        return new DeckOfCards((ArrayList<PlayingCard>)deckOfCards.clone());
    }
				
				
		
		
	
	

}
