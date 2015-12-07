package deck.cards;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import controller.FiveCardStudPokerAjaxAction;
import entities.User;

public class Game {
	
	final static Logger log = Logger.getLogger(FiveCardStudPokerAjaxAction.class);

	
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
	  
	  private PlayingCard player1card5 = null;
	  private double player1betCard5 = 0.00;


	  public double getPlayer1betCard4() {
		return player1betCard4;
	}


	  
	  
	  private PlayingCard player2card1 = null;
	  

	  private PlayingCard player2card2 = null;
	  private double player2betCard2 = 0.00;

	  private PlayingCard player2card3 = null;
	  private double player2betCard3 = 0.00;

	  private PlayingCard player2card4 = null;
	  private double player2betCard4 = 0.00;

	  private PlayingCard player2card5 = null;
	  private double player2betCard5 = 0.00;
		  

		public void setPlayer1betCard4(double player1betCard4) {
			this.player1betCard4 = player1betCard4;
		}
	  
	  
	  	  
	  public PlayingCard getPlayer2card3() {
		return player2card3;
	}


	public void setPlayer2card3(PlayingCard player2card3) {
		this.player2card3 = player2card3;
	}
	
	public String getPlayer1BetAmountsForDisplay(User user) {

		StringBuffer stringBuffer = new StringBuffer();
		
		
		if (this.getPlayer1card1() == null) {
			
			return "";
			
		}
		if (this.getPlayer1card1() != null ) {

			stringBuffer.append("No Bet Card 1");
			
		}
		
		
		
		if (this.getPlayer1card2() != null) {

			stringBuffer.append(" | " + this.getPlayer1betCard2());
			
			
			
		}
		if (this.getPlayer1card3() != null) {

			stringBuffer.append(" | " + this.getPlayer1betCard3());
			
			
			
		}
		if (this.getPlayer1card4() != null) {

			stringBuffer.append(" | " + this.getPlayer1betCard4());
			
			
			
		}
		
		
		if (this.getPlayer1card5() != null ) {

			stringBuffer.append(" | " + this.getPlayer1betCard5());
			
			
			
		}
		else if (this.getTotalBetForWinner() != 0.00) {

			stringBuffer.append(" | Total Bet For Winner = " + this.getTotalBetForWinner());
			
			
			
		}

		return stringBuffer.toString();

		
	}
	
	public String getPlayer2BetAmountsForDisplay(User user) {

		StringBuffer stringBuffer = new StringBuffer();
		
		
		if (this.getPlayer2card1() == null) {
			
			return "";
			
		}
		if (this.getPlayer2card1() != null ) {

			stringBuffer.append("No Bet Card 1");
			
		}
		
		
		
		if (this.getPlayer2card2() != null) {

			stringBuffer.append(" | " + this.getPlayer2betCard2());
			
			
			
		}
		if (this.getPlayer2card3() != null) {

			stringBuffer.append(" | " + this.getPlayer2betCard3());
			
			
			
		}
		if (this.getPlayer2card4() != null) {

			stringBuffer.append(" | " + this.getPlayer2betCard4());
			
			
			
		}
		
		
		if (this.getPlayer2card5() != null ) {

			stringBuffer.append(" | " + this.getPlayer2betCard5());
			
			
			
		}
		else if (this.getTotalBetForWinner() != 0.00) {

			stringBuffer.append(" | Total Bet For Winner = " + this.getTotalBetForWinner());
			
			
			
		}

		return stringBuffer.toString();

		
	}
	
	
	public String getPlayers1HandsForDisplay(User user) {
		
		StringBuffer stringBuffer = new StringBuffer();

		if (this.getPlayer1card1() == null) {
			
			return "";
			
		}
		if (this.getPlayer1card1() != null && user.getUserName().equals(player1.getUserName())) {

			stringBuffer.append(this.getPlayer1card1().toString());
			
			
			
		}
		else if (this.getPlayer1card1() != null && !(user.getUserName().equals(player1.getUserName()))) {

			stringBuffer.append("Card Face Down");
			
			
			
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

			stringBuffer.append(" | " + this.getPlayer1card5().toString());
			
			
			
		}
		else if (this.getPlayer1card5() != null && !(user.getUserName().equals(player1.getUserName()))) {

			stringBuffer.append(" | Card Face Down");
			
			
			
		}

		return stringBuffer.toString();
		
		
	}
	  

	
	public String getPlayers2HandsForDisplay(User user) {
		
		StringBuffer stringBuffer = new StringBuffer();

		if (this.getPlayer2card1() == null) {
			
			return "";
			
		}
		if (this.getPlayer2card1() != null && user.getUserName().equals(player2.getUserName())) {

			stringBuffer.append(this.getPlayer2card1().toString());
			
			
			
		}
		else if (this.getPlayer2card1() != null && !(user.getUserName().equals(player2.getUserName()))) {

			stringBuffer.append("Card Face Down");
			
			
			
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

			stringBuffer.append(" | " + this.getPlayer2card5().toString());
			
			
			
		}
		else if (this.getPlayer2card5() != null && !(user.getUserName().equals(player2.getUserName()))) {

			stringBuffer.append(" | Card Face Down");
			
			
			
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
	
	public static final String GAME_COMPLETE = "GAME_COMPLETE";
	
	public static final String FINAL_CARD_BET = "FINAL_CARD_BET";
	
	public static final String UNKNOWN_STATE = "UNKNOWN_STATE";
	
		
	public static final String CURRENT_TURN_PLAYER1 = "CURRENT_TURN_PLAYER1";

	public static final String CURRENT_TURN_PLAYER2 = "CURRENT_TURN_PLAYER2";

	
	
	public String whoseTurnIsIt() {
		
		if(this.getPlayer1betCard2() <= 0.00) {
			
			return CURRENT_TURN_PLAYER1;
			
		}
		else if(this.getPlayer2betCard2() <= 0.00) {
			
			return CURRENT_TURN_PLAYER2;
			
		}
		else if(this.getPlayer1betCard3() <= 0.00) {
			
			return CURRENT_TURN_PLAYER1;
			
		}
		else if(this.getPlayer2betCard3() <= 0.00) {
			
			return CURRENT_TURN_PLAYER2;
			
		}
		else if(this.getPlayer1betCard4() <= 0.00) {
			
			return CURRENT_TURN_PLAYER1;
			
		}
		else if(this.getPlayer2betCard4() <= 0.00) {
			
			return CURRENT_TURN_PLAYER2;
			
		}
		else if(this.getPlayer1betCard5() <= 0.00) {
			
			return CURRENT_TURN_PLAYER1;
			
		}
		else if(this.getPlayer2betCard5() <= 0.00) {
			
			return CURRENT_TURN_PLAYER2;
			
		}
		else if(this.getTotalBetForWinner() <= 0.00) {
			
			return FINAL_CARD_BET;
			
		}
		else if(this.getTotalBetForWinner() > 0.00) {
			
			return GAME_COMPLETE;
			
		}

		
		return "UNKNOWN_STATE";
				
		
	}

	public void  player1makeBet (double bet1) {
		
		if (this.getPlayer1card5() != null) {
			
			this.setPlayer1betCard5(bet1);
			
			
			
		}

		else if (this.getPlayer1card4() != null) {
			
			this.setPlayer1betCard4(bet1);
			
			
			
		}
		else if (this.getPlayer1card3() != null) {
			
			this.setPlayer1betCard3(bet1);
			
			
			
		}
		else if (this.getPlayer1card2() != null) {
			
			this.setPlayer1betCard2(bet1);
			
			
			
		}
		
		
		
		
	}


	public void  player2makeBet (double bet2) {
		
		if (this.getPlayer2card5() != null) {
			
			
			this.setPlayer2betCard5(bet2);
			
			
		}

		else if (this.getPlayer2card4() != null) {
			
			
			this.setPlayer2betCard4(bet2);
			
			
		}
		else if (this.getPlayer2card3() != null) {
			
			
			this.setPlayer2betCard3(bet2);
			
			
		}
		else if (this.getPlayer2card2() != null) {
			
			
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
	

	
	public static User getUserObjectFromSessionScope() {
		
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		
		User user = (User)httpSession.getAttribute("user");
		
		return user;
		

	}
	
	public static boolean setGameIdInSessionScope(String userGameId) {
		
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		
		httpSession.setAttribute("userGameId", userGameId);
		
		return true;
		

	}

	
	public static String getGameIdFromSessionScope() {
		
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		
		String userGameId = "" + httpSession.getAttribute("userGameId");
		
		return userGameId;
		

	}

	public static boolean addGameToApplicationScopeGameHashMap(Game game) {
		

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		
//		int gameNumber = game.getGameNumber();
		Map<String,Game> currentGamesBeingPlayed1 = (Map<String,Game>)servletContext.getAttribute("currentGamesBeingPlayed");
		
		if(currentGamesBeingPlayed1 == null) {

			currentGamesBeingPlayed1 = new HashMap<String,Game>();
			
//			currentGamesBeingPlayed1.put("" + game.getGameNumber(), game);
//			
//			servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
//			
//			return true;
			
		}
		
		currentGamesBeingPlayed1.put("" + game.getGameNumber(), game);
		
		servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
		
		return true;

	}
	
	public static List<Game> convertFromVCollectionToList(Collection<Game> collectionGames) {
		
		Iterator iter = collectionGames.iterator();
		
		List<Game> games = new ArrayList<Game>();
		
		while (iter.hasNext()) {
			
			Game gsme = (Game)iter.next();
			
			games.add(gsme);
			
		}
		
		return games;
		
	}
	

	public static List<Game> getAlltGamesFromApplicationScopeGameHashMap() {
		
		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		
//		int gameNumber = game.getGameNumber();
		Map<String,Game> currentGamesBeingPlayed1 = (Map<String,Game>)servletContext.getAttribute("currentGamesBeingPlayed");
		
		if(currentGamesBeingPlayed1 == null) {
			
			currentGamesBeingPlayed1 = new HashMap<String,Game>();
			
			servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
			
//			Collection<Game> collGames =currentGamesBeingPlayed1.values(); 
//			
//			return (List<Game>) convertFromVCollectionToList(collGames);
			
			
		}
		
		Collection<Game> collGames = currentGamesBeingPlayed1.values(); 
		
		return (List<Game>) convertFromVCollectionToList(collGames);

	}
	
	
	

	public static Game getGameFromApplicationScopeGameHashMap(String userGameId) {
		
		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		
//		int gameNumber = game.getGameNumber();
		Map<String,Game> currentGamesBeingPlayed1 = (Map<String,Game>)servletContext.getAttribute("currentGamesBeingPlayed");
		
		if(currentGamesBeingPlayed1 == null) {
			currentGamesBeingPlayed1 = new HashMap<String,Game>();
			
			servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
			
			return null;
			
		}
		
		Game game = (Game)currentGamesBeingPlayed1.get(userGameId);
		
		return game;
		

	}
	
	public boolean outputToResponseOutputStream(User user) {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		out.println(
				"<BR>Player 1 " + this.getPlayer1().getUserName() + " Hand: " + this.getPlayers1HandsForDisplay(user));
		out.println("<BR>Player 1 " + this.getPlayer1().getUserName() + " Bets: "
				+ this.getPlayer1BetAmountsForDisplay(user));

		out.println("<BR>");

		out.println(
				"<BR>Player 2 " + (this.getPlayer2() == null ? "Player 2 has not joined game yet" : this.getPlayer2().getUserName())  + " Hand: " + this.getPlayers2HandsForDisplay(user));
		out.println("<BR>Player 2 " + (this.getPlayer2() == null ? "Player 2 has not joined game yet" : this.getPlayer2().getUserName()) + " Bets: "
				+ this.getPlayer2BetAmountsForDisplay(user));
		out.println("<BR>");

		String whoseTurnIsIt = this.whoseTurnIsIt();

		if (whoseTurnIsIt.equals(this.CURRENT_TURN_PLAYER1)) {
			out.println("<BR>It is now Player 1 " + this.getPlayer1().getUserName() + " Turn!");

		} else if (whoseTurnIsIt.equals(this.CURRENT_TURN_PLAYER2)) {
			out.println("<BR>It is now Player 2 " + this.getPlayer2().getUserName() + " Turn!");

		} else if (whoseTurnIsIt.equals(this.FINAL_CARD_BET)) {
			out.println("<BR>It is now Final Card Bet " + this.getPlayer1().getUserName() + " Turn!");

		} else if (whoseTurnIsIt.equals(this.GAME_COMPLETE)) {
			out.println("<BR>The game is now coplete. ");

		} else if (whoseTurnIsIt.equals(this.UNKNOWN_STATE)) {
			out.println("<BR>The game is in amn unknown state. ");

		}

		out.flush();

		log.debug("\n\nDebug: Exiting method Game::outputToResponseOutputStream(user)\n\n");

		return true;

	}
	
	

}
