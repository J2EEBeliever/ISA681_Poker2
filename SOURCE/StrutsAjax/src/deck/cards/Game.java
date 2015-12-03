package deck.cards;

import entities.User;

public class Game {
	
	private User player1 = null;
	private User player2 = null;
	
	private DeckOfCards deckOfCards = null;
	
	private static int uniqueSerialNumber = 0;
	
	private int gameNumber = -1;
	

	  private PlayingCard player1card1 = null;
	  

	  private PlayingCard player1card2 = null;
	  private double player1betCard2 = 0.00;

	  private PlayingCard player1card3 = null;
	  private double player1betCard3 = 0.00;

	  private PlayingCard player1card4 = null;
	  private double player1betCard4 = 0.00;

	  public double getPlayer1betCard4() {
		return player1betCard4;
	}


	public void setPlayer1betCard4(double player1betCard4) {
		this.player1betCard4 = player1betCard4;
	}


	public double getPlayerbetCard5() {
		return playerbetCard5;
	}


	public void setPlayerbetCard5(double playerbetCard5) {
		this.playerbetCard5 = playerbetCard5;
	}
	private PlayingCard player1card5 = null;
	  private double player1betCard5 = 0.00;


	  
	  
	  private PlayingCard player2card1 = null;
	  

	  private PlayingCard player2card2 = null;
	  private double player2betCard2 = 0.00;

	  private PlayingCard player2card3 = null;
	  private double player2betCard3 = 0.00;

	  private PlayingCard player2card4 = null;
	  private double player2betCard4 = 0.00;

	  private PlayingCard player2card5 = null;
	  private double playerbetCard5 = 0.00;
	  

	  private double player2betCard5 = 0.00;
		  

	  
	  
	  	  
	  public PlayingCard getPlayer2card3() {
		return player2card3;
	}


	public void setPlayer2card3(PlayingCard player2card3) {
		this.player2card3 = player2card3;
	}
	
	public String getPlayers1HandsForDisplay(User user) {
		
		StringBuffer stringBuffer = new StringBuffer();

		if (this.getPlayer1card1() == null) {
			
			return null;
			
		}
		if (this.getPlayer1card1() != null && user.getUserName().equals(player1.getUserName())) {

			stringBuffer.append(this.getPlayer1card1().toString());
			
			
			
		}
		else if (this.getPlayer1card1() != null && !user.getUserName().equals(player1.getUserName())) {

			stringBuffer.append("Player Card Face Down");
			
			
			
		}
		
		
		
		if (this.getPlayer1card2() != null) {

			stringBuffer.append(" | " + this.getPlayer1card2().toString());
			
			
			
		}
		if (this.getPlayer1card3() != null) {

			stringBuffer.append(" | " + this.getPlayer1card3().toString());
			
			
			
		}
		if (this.getPlayer1card4() != null) {

			stringBuffer.append(" | " + this.getPlayer1card4().toString());
			
			
			
		}
		
		
		if (this.getPlayer1card5() != null && user.getUserName().equals(player1.getUserName())) {

			stringBuffer.append(this.getPlayer1card5().toString());
			
			
			
		}
		else if (this.getPlayer1card5() != null && !user.getUserName().equals(player1.getUserName())) {

			stringBuffer.append("Player Card Face Down");
			
			
			
		}

		return stringBuffer.toString();
		
		
	}
	  

	
	public String getPlayers2HandsForDisplay(User user) {
		
		StringBuffer stringBuffer = new StringBuffer();

		if (this.getPlayer2card1() == null) {
			
			return "";
			
		}
		if (this.getPlayer2card1() != null && user.getUserName().equals(player2.getUserName())) {

			stringBuffer.append(this.getPlayer1card1().toString());
			
			
			
		}
		else if (this.getPlayer2card1() != null && !user.getUserName().equals(player2.getUserName())) {

			stringBuffer.append("Player Card Face Down");
			
			
			
		}
		
		
		
		if (this.getPlayer2card2() != null) {

			stringBuffer.append(" | " + this.getPlayer2card2().toString());
			
			
			
		}
		if (this.getPlayer2card3() != null) {

			stringBuffer.append(" | " + this.getPlayer2card3().toString());
			
			
			
		}
		if (this.getPlayer2card4() != null) {

			stringBuffer.append(" | " + this.getPlayer2card4().toString());
			
			
			
		}
		
		
		if (this.getPlayer2card5() != null && user.getUserName().equals(player2.getUserName())) {

			stringBuffer.append(this.getPlayer2card5().toString());
			
			
			
		}
		else if (this.getPlayer2card5() != null && !user.getUserName().equals(player2.getUserName())) {

			stringBuffer.append("Player Card Face Down");
			
			
			
		}

		return stringBuffer.toString();
		
		
	}
	  
	
	
	public void dealNextTwoCards(PlayingCard playingCard1, PlayingCard playingCard2) {
		
		if (this.getPlayer1card1() == null) {
			
			this.setPlayer1card1(playingCard1);
			
			this.setPlayer2card1( playingCard2);
			
			
		}
		else if (this.getPlayer1card2() == null) {

			this.setPlayer1card2(playingCard1);
			
			this.setPlayer2card2( playingCard2);
			
			
		}
		else if (this.getPlayer1card3() == null) {

			this.setPlayer1card3(playingCard1);
			
			this.setPlayer2card3(playingCard2);
			
			
		}
		else if (this.getPlayer1card4() == null) {

			this.setPlayer1card4(playingCard1);
			
			this.setPlayer2card4(playingCard2);
			
			
		}
		else if (this.getPlayer1card5() == null) {

			this.setPlayer1card5(playingCard1);
			
			this.setPlayer2card5(playingCard2);
			
			
		}
		
		
		
		
	}

	public void  makeBet (double bet1, double bet2) {
		
		if (this.getPlayer1card5() != null) {
			
			this.setPlayer1betCard5(bet1);
			
			this.setPlayer2betCard5(bet2);
			
			
		}

		else if (this.getPlayer1card4() != null) {
			
			this.setPlayer1betCard4(bet1);
			
			this.setPlayer2betCard4(bet2);
			
			
		}
		else if (this.getPlayer1card3() != null) {
			
			this.setPlayer1betCard3(bet1);
			
			this.setPlayer2betCard3(bet2);
			
			
		}
		else if (this.getPlayer1card2() != null) {
			
			this.setPlayer1betCard2(bet1);
			
			this.setPlayer2betCard2(bet2);
			
			
		}
		
		
		
		
	}


	


	public double getTotalBetForWinner() {
		return totalBetForWinner;
	}

	public void setTotalBetForWinner(double totalBetForWinner) {
		this.totalBetForWinner = totalBetForWinner;
	}

	public String getWinnerUserName() {
		return winnerUserName;
	}

	public void setWinnerUserName(String winnerUserName) {
		this.winnerUserName = winnerUserName;
	}
	private double totalBetForWinner = 0.00;

	  private String winnerUserName = null;


	
	public Game() {
		
		gameNumber = ++uniqueSerialNumber;
		
	}
	
	public PlayingCard getPlayer1card1() {
		return player1card1;
	}

	public void setPlayer1card1(PlayingCard player1card1) {
		this.player1card1 = player1card1;
	}

	public PlayingCard getPlayer1card2() {
		return player1card2;
	}

	public void setPlayer1card2(PlayingCard player1card2) {
		this.player1card2 = player1card2;
	}

	public double getPlayer1betCard2() {
		return player1betCard2;
	}

	public void setPlayer1betCard2(double player1betCard2) {
		this.player1betCard2 = player1betCard2;
	}

	public PlayingCard getPlayer1card3() {
		return player1card3;
	}

	public void setPlayer1card3(PlayingCard player1card3) {
		this.player1card3 = player1card3;
	}

	public double getPlayer1betCard3() {
		return player1betCard3;
	}

	public void setPlayer1betCard3(double player1betCard3) {
		this.player1betCard3 = player1betCard3;
	}

	public PlayingCard getPlayer1card4() {
		return player1card4;
	}

	public void setPlayer1card4(PlayingCard player1card4) {
		this.player1card4 = player1card4;
	}

	public PlayingCard getPlayer1card5() {
		return player1card5;
	}

	public void setPlayer1card5(PlayingCard player1card5) {
		this.player1card5 = player1card5;
	}

	public double getPlayer1betCard5() {
		return player1betCard5;
	}

	public void setPlayer1betCard5(double player1betCard5) {
		this.player1betCard5 = player1betCard5;
	}

	public PlayingCard getPlayer2card1() {
		return player2card1;
	}

	public void setPlayer2card1(PlayingCard player2card1) {
		this.player2card1 = player2card1;
	}

	public PlayingCard getPlayer2card2() {
		return player2card2;
	}

	public void setPlayer2card2(PlayingCard player2card2) {
		this.player2card2 = player2card2;
	}

	public double getPlayer2betCard2() {
		return player2betCard2;
	}

	public void setPlayer2betCard2(double player2betCard2) {
		this.player2betCard2 = player2betCard2;
	}



	public double getPlayer2betCard3() {
		return player2betCard3;
	}

	public void setPlayer2betCard3(double player2betCard3) {
		this.player2betCard3 = player2betCard3;
	}

	public PlayingCard getPlayer2card4() {
		return player2card4;
	}

	public void setPlayer2card4(PlayingCard player2card4) {
		this.player2card4 = player2card4;
	}

	public double getPlayer2betCard4() {
		return player2betCard4;
	}

	public void setPlayer2betCard4(double player2betCard4) {
		this.player2betCard4 = player2betCard4;
	}

	public PlayingCard getPlayer2card5() {
		return player2card5;
	}

	public void setPlayer2card5(PlayingCard player2card5) {
		this.player2card5 = player2card5;
	}

	public double getPlayer2betCard5() {
		return player2betCard5;
	}

	public void setPlayer2betCard5(double player2betCard5) {
		this.player2betCard5 = player2betCard5;
	}

	public static int getUniqueSerialNumber() {
		return uniqueSerialNumber;
	}

	public static void setUniqueSerialNumber(int uniqueSerialNumber) {
		Game.uniqueSerialNumber = uniqueSerialNumber;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	public DeckOfCards getDeckOfCards() {
		return deckOfCards;
	}
	public void setDeckOfCards(DeckOfCards deckOfCards) {
		this.deckOfCards = deckOfCards;
	}
	public User getPlayer1() {
		return player1;
	}
	public void setPlayer1(User player1) {
		this.player1 = player1;
	}
	public User getPlayer2() {
		return player2;
	}
	public void setPlayer2(User player2) {
		this.player2 = player2;
	}
	
	
	

}