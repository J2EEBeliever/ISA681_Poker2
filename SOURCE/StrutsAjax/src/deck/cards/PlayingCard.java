package deck.cards;

public class PlayingCard {
	
	private int cardNumber = -1;
	private String cardString = "";
	private String cardSuit = "";

	
	public PlayingCard() {
		
		super();
		
		this.cardNumber = -1;
		this.cardString = "Card Instance Not Used";
		this.cardSuit = "Card Instance Not Used";
		
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
