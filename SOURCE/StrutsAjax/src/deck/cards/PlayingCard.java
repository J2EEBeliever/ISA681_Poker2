package deck.cards;

import java.io.Serializable;
import java.util.Comparator;

/*
 * Author: Linus Freeman
 * 
 */

public class PlayingCard implements Serializable, Comparable<PlayingCard> {
	
	

	private int cardNumber = -1;
	private String cardString = "";
	private String cardSuit = "";

	public static Comparator<PlayingCard> playingCardBySuitNameComparator =

	new Comparator<PlayingCard>() {

		public int compare(PlayingCard playingCard1_, PlayingCard playingCard2_) {

			String suit1_ = playingCard1_.getCardSuit();
			String suit2_ = playingCard2_.getCardSuit();

			// ascending order
			return suit1_.compareTo(suit2_);

			// descending order
			// return fruitName2.compareTo(fruitName1);
		}

	};
	
	public static Comparator<PlayingCard> playingCardByCardNumberNameComparator =

	new Comparator<PlayingCard>() {

		public int compare(PlayingCard playingCard1_, PlayingCard playingCard2_) {

			int cardNumber1_ = playingCard1_.getCardNumber();
			int cardNumber2_= playingCard2_.getCardNumber();

			// ascending order
			return cardNumber1_ - cardNumber2_; 

			// descending order
			// return fruitName2.compareTo(fruitName1);
		}

	};


	public int compareTo(PlayingCard playingCard_) {

		int cardNumber_ = playingCard_.getCardNumber();

		// ascending order
		return this.cardNumber - cardNumber_;

		// descending order
		// return compareQuantity - this.quantity;

	}
	
	

	public PlayingCard() {

		super();

		this.cardNumber = -1;
		this.cardString = "Card Instance Not Used";
		this.cardSuit = "Card Instance Not Used";

	}

	public String toString() {

		StringBuffer stringBuffer = new StringBuffer("");

		stringBuffer.append(getCardString() + " " + getCardSuit() + "");

		return stringBuffer.toString();
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardString() {
		return cardString;
	}

	public void setCardString(String cardString) {
		this.cardString = cardString;
	}

	public String getCardSuit() {
		return cardSuit;
	}

	public void setCardSuit(String cardsuit) {
		this.cardSuit = cardsuit;
	}


}
