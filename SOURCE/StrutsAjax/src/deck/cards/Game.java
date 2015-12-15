package deck.cards;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import antlr.collections.impl.Vector;
import entities.CardRanking;
import entities.MySQLConnection;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Game {

	final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Game.class);
  
	private User player1 = null;
	private User player2 = null;

	private DeckOfCards deckOfCards = null;

	private int uniqueSerialNumber = 0;

	private int gameNumber = -1;

	private String player1FinalHandRanking = null;

	private String player2FinalHandRanking = null;

	private String player1UserName = null; // Added 2015/12/10 2:30 PM LWF
	private String didPlayer1Fold = "no"; // Added 2015/12/10 2:30 PM LWF

	private String player2UserName = null; // Added 2015/12/10 2:30 PM LWF
	private String didPlayer2Fold = "no"; // Added 2015/12/10 2:30 PM LWF
	
	public static void main(String[] args) {
		
		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
//		playingCardArrayList.add(card2);
//		playingCardArrayList.add(card3);
//		playingCardArrayList.add(card4);
//		playingCardArrayList.add(card5);
		
//		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();
		
		PlayingCard playingCard1_ = new PlayingCard();
		
		playingCard1_.setCardNumber(1);
		playingCard1_.setCardString("Ace");
		playingCard1_.setCardSuit("Spades");
		
		playingCardArrayList.add(playingCard1_);

//		mapOfPlayerCards.put(""+ playingCard1_.getCardNumber(), playingCard1_);
		
		PlayingCard playingCard2_ = new PlayingCard();

		playingCard2_.setCardNumber(11);
		playingCard2_.setCardString("Jack");
		playingCard2_.setCardSuit("Hearts");
		
//		mapOfPlayerCards.put(""+ playingCard2_.getCardNumber(), playingCard2_);
		
		playingCardArrayList.add(playingCard2_);


		
		PlayingCard playingCard3_ = new PlayingCard();

		playingCard3_.setCardNumber(2);
		playingCard3_.setCardString("2");
		playingCard3_.setCardSuit("Diamonds");

//		mapOfPlayerCards.put(""+ playingCard3_.getCardNumber(), playingCard3_);
		
		playingCardArrayList.add(playingCard3_);
	


		PlayingCard playingCard4_ = new PlayingCard();

		playingCard4_.setCardNumber(8);
		playingCard4_.setCardString("8");
		playingCard4_.setCardSuit("Clubs");

//		mapOfPlayerCards.put(""+ playingCard4_.getCardNumber(), playingCard4_);
		
		playingCardArrayList.add(playingCard4_);
		

		PlayingCard playingCard5_ = new PlayingCard();

		playingCard5_.setCardNumber(4);
		playingCard5_.setCardString("4");
		playingCard5_.setCardSuit("Spaces");

//		mapOfPlayerCards.put(""+ playingCard4_.getCardNumber(), playingCard4_);
		
		playingCardArrayList.add(playingCard5_);

		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		
//		Collections.sort(playingCardArrayList, PlayingCard.playingCardBySuitNameComparator);
		
		Iterator<PlayingCard> iter_PlayingCard = playingCardArrayList.iterator();



		
//		Collection<PlayingCard> collPlayingCards = mapOfPlayerCards.values();
		
//		Iterator<PlayingCard> iter_PlayingCard = collPlayingCards.iterator();
		
		while(iter_PlayingCard.hasNext()) {
			
			PlayingCard playingCard = iter_PlayingCard.next();
			
			System.out.print("\nCardNumber=" + playingCard.getCardNumber());
			
			System.out.print("\nCardString=" + playingCard.getCardString());

			System.out.print("\nSuit=" + playingCard.getCardSuit());
			
			System.out.print("\n");
			
		}

		
		
	}

	public String getDidPlayer1Fold() {
		return didPlayer1Fold;
	}

	public void setDidPlayer1Fold(String didPlayer1Fold) {
		this.didPlayer1Fold = didPlayer1Fold;
	}

	public String getDidPlayer2Fold() {
		return didPlayer2Fold;
	}

	public void setDidPlayer2Fold(String didPlayer2Fold) {
		this.didPlayer2Fold = didPlayer2Fold;
	}

	public String getPlayer1FinalHandRanking() {
		return player1FinalHandRanking;
	}

	public void setPlayer1FinalHandRanking(String player1FinalHandRanking) {
		this.player1FinalHandRanking = player1FinalHandRanking;
	}

	public String getPlayer2FinalHandRanking() {
		return player2FinalHandRanking;
	}

	public void setPlayer2FinalHandRanking(String player2FinalHandRanking) {
		this.player2FinalHandRanking = player2FinalHandRanking;
	}

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

		StringBuilder stringBuffer = new StringBuilder();

		if (this.getPlayer1card1() == null) {

			return "";

		}
		if (this.getPlayer1card1() != null) {

			stringBuffer.append("No Bet Card 1");

		}

		if (this.getPlayer1card2() != null) {

			stringBuffer.append(" | ").append(this.getPlayer1betCard2());

		}
		if (this.getPlayer1card3() != null) {

			stringBuffer.append(" | ").append(this.getPlayer1betCard3());

		}
		if (this.getPlayer1card4() != null) {

			stringBuffer.append(" | ").append(this.getPlayer1betCard4());

		}

		if (this.getPlayer1card5() != null) {

			stringBuffer.append(" | ").append(this.getPlayer1betCard5());

		} else if (this.getTotalBetForWinner() != 0.00) {

			stringBuffer.append(" |  Total Amount Winner Won = ").append(this.getTotalBetForWinner());

		}

		return stringBuffer.toString();

	}

	public String getPlayer2BetAmountsForDisplay(User user) {

		StringBuilder stringBuffer = new StringBuilder();

		if (this.getPlayer2card1() == null) {

			return "";

		}
		if (this.getPlayer2card1() != null) {

			stringBuffer.append("No Bet Card 1");

		}

		if (this.getPlayer2card2() != null) {

			stringBuffer.append(" | ").append(this.getPlayer2betCard2());

		}
		if (this.getPlayer2card3() != null) {

			stringBuffer.append(" | ").append(this.getPlayer2betCard3());

		}
		if (this.getPlayer2card4() != null) {

			stringBuffer.append(" | ").append(this.getPlayer2betCard4());

		}

		if (this.getPlayer2card5() != null) {

			stringBuffer.append(" | ").append(this.getPlayer2betCard5());

		} else if (this.getTotalBetForWinner() != 0.00) {

			stringBuffer.append(" |  Total Amount Winner Won = ").append(this.getTotalBetForWinner());

		}

		return stringBuffer.toString();

	}

	public String getPlayer1UserName() {
		return player1UserName;
	}

	public void setPlayer1UserName(String player1UserName) {
		this.player1UserName = player1UserName;
	}

	public String getPlayer2UserName() {
		return player2UserName;
	}

	public void setPlayer2UserName(String player2UserName) {
		this.player2UserName = player2UserName;
	}

	public String getPlayers1HandsForDisplay(User user) {

		StringBuilder stringBuffer = new StringBuilder();

		if (this.getPlayer1card1() == null) {

			return "";

		}
		if (this.getPlayer1card1() != null && user.getUsername().equals(player1.getUsername())) {

			stringBuffer.append(this.getPlayer1card1().toString());

		} else if (this.getPlayer1card1() != null && !(user.getUsername().equals(player1.getUsername()))) {

			stringBuffer.append("Card Face Down");

		}

		if (this.getPlayer1card2() != null) {

			stringBuffer.append(" | ").append(this.getPlayer1card2().toString());

		}
		if (this.getPlayer1card3() != null) {

			stringBuffer.append(" | ").append(this.getPlayer1card3().toString());

		}
		if (this.getPlayer1card4() != null) {

			stringBuffer.append(" | ").append(this.getPlayer1card4().toString());

		}

		if (this.getPlayer1card5() != null && user.getUsername().equals(player1.getUsername())) {

			stringBuffer.append(" | ").append(this.getPlayer1card5().toString());

		} else if (this.getPlayer1card5() != null && !(user.getUsername().equals(player1.getUsername()))) {

			stringBuffer.append(" | Card Face Down");

		}

		return stringBuffer.toString();

	}

	public String getPlayers2HandsForDisplay(User user) {

		StringBuilder stringBuffer = new StringBuilder();

		if (this.getPlayer2card1() == null) {

			return "";

		}
		if (this.getPlayer2card1() != null && user.getUsername().equals(player2.getUsername())) {

			stringBuffer.append(this.getPlayer2card1().toString());

		} else if (this.getPlayer2card1() != null && !(user.getUsername().equals(player2.getUsername()))) {

			stringBuffer.append("Card Face Down");

		}

		if (this.getPlayer2card2() != null) {

			stringBuffer.append(" | ").append(this.getPlayer2card2().toString());

		}
		if (this.getPlayer2card3() != null) {

			stringBuffer.append(" | ").append(this.getPlayer2card3().toString());

		}
		if (this.getPlayer2card4() != null) {

			stringBuffer.append(" | ").append(this.getPlayer2card4().toString());

		}

		if (this.getPlayer2card5() != null && user.getUsername().equals(player2.getUsername())) {

			stringBuffer.append(" | ").append(this.getPlayer2card5().toString());

		} else if (this.getPlayer2card5() != null && !(user.getUsername().equals(player2.getUsername()))) {

			stringBuffer.append(" | Card Face Down");

		}

		return stringBuffer.toString();

	}

	public void dealNextTwoCards(PlayingCard playingCard1, PlayingCard playingCard2) {

		if (this.getPlayer1card1() == null) {

			this.setPlayer1card1(playingCard1);

			this.setPlayer2card1(playingCard2);

		} else if (this.getPlayer1card2() == null) {

			this.setPlayer1card2(playingCard1);

			this.setPlayer2card2(playingCard2);

		} else if (this.getPlayer1card3() == null) {

			this.setPlayer1card3(playingCard1);

			this.setPlayer2card3(playingCard2);

		} else if (this.getPlayer1card4() == null) {

			this.setPlayer1card4(playingCard1);

			this.setPlayer2card4(playingCard2);

		} else if (this.getPlayer1card5() == null) {

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

		if (this.getPlayer1betCard2() <= 0.00) {

			return CURRENT_TURN_PLAYER1;

		} else if (this.getPlayer2betCard2() <= 0.00) {

			return CURRENT_TURN_PLAYER2;

		} else if (this.getPlayer1betCard3() <= 0.00) {

			return CURRENT_TURN_PLAYER1;

		} else if (this.getPlayer2betCard3() <= 0.00) {

			return CURRENT_TURN_PLAYER2;

		} else if (this.getPlayer1betCard4() <= 0.00) {

			return CURRENT_TURN_PLAYER1;

		} else if (this.getPlayer2betCard4() <= 0.00) {

			return CURRENT_TURN_PLAYER2;

		} else if (this.getPlayer1betCard5() <= 0.00) {

			return CURRENT_TURN_PLAYER1;

		} else if (this.getPlayer2betCard5() <= 0.00) {

			return CURRENT_TURN_PLAYER2;

		} else if (this.getTotalBetForWinner() <= 0.00) {

			return FINAL_CARD_BET;

		} else if (this.getTotalBetForWinner() > 0.00) {

			return GAME_COMPLETE;

		}

		return "UNKNOWN_STATE";

	}

	public double getPlayersLastBet() {

		User user = Game.getUserObjectFromSessionScope();

		if (this.getPlayer1card5() != null) {

			if (this.getPlayer1().getUsername().equals(user.getUsername())) {

				return this.getPlayer2betCard5();

			} else {

				return this.getPlayer1betCard5();

			}

		} else if (this.getPlayer1card4() != null) {

			if (this.getPlayer1().getUsername().equals(user.getUsername())) {

				return this.getPlayer2betCard4();

			} else {

				return this.getPlayer1betCard4();

			}

		} else if (this.getPlayer1card3() != null) {

			if (this.getPlayer1().getUsername().equals(user.getUsername())) {

				return this.getPlayer2betCard3();

			} else {

				return this.getPlayer1betCard3();

			}

		} else if (this.getPlayer1card2() != null) {

			if (this.getPlayer1().getUsername().equals(user.getUsername())) {

				return this.getPlayer2betCard2();

			} else {

				return this.getPlayer1betCard2();

			}

		}

		return 0.00;

	}

	public void player1makeBet(double bet1) {

		if (this.getPlayer1card5() != null) {

			this.setPlayer1betCard5(bet1);

		}

		else if (this.getPlayer1card4() != null) {

			this.setPlayer1betCard4(bet1);

		} else if (this.getPlayer1card3() != null) {

			this.setPlayer1betCard3(bet1);

		} else if (this.getPlayer1card2() != null) {

			this.setPlayer1betCard2(bet1);

		}

	}

	public void player2makeBet(double bet2) {

		if (this.getPlayer2card5() != null) {

			this.setPlayer2betCard5(bet2);

		}

		else if (this.getPlayer2card4() != null) {

			this.setPlayer2betCard4(bet2);

		} else if (this.getPlayer2card3() != null) {

			this.setPlayer2betCard3(bet2);

		} else if (this.getPlayer2card2() != null) {

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

	public int getUniqueSerialNumber() {
		return uniqueSerialNumber;
	}

	public void setUniqueSerialNumber(int uniqueSerialNumber) {
		this.uniqueSerialNumber = uniqueSerialNumber;
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

		User user = (User) httpSession.getAttribute("user");

		return user;

	}

	public static boolean setGameIdInSessionScope(String userGameId) {

		HttpSession httpSession = ServletActionContext.getRequest().getSession();

		httpSession.setAttribute("userGameId", userGameId);

		return true;

	}

	public static boolean removeGameIdInSessionScope(String userGameId) {

		HttpSession httpSession = ServletActionContext.getRequest().getSession();

		httpSession.removeAttribute("userGameId");

		return true;

	}

	public static String getGameIdFromSessionScope() {

		HttpSession httpSession = ServletActionContext.getRequest().getSession();

		String userGameId = "" + httpSession.getAttribute("userGameId");

		return userGameId;

	}

	public static boolean addGameToApplicationScopeGameHashMap(Game game) {

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		// int gameNumber = game.getGameNumber();
		@SuppressWarnings("unchecked")
		Map<String, Game> currentGamesBeingPlayed1 = (Map<String, Game>) servletContext
				.getAttribute("currentGamesBeingPlayed");

		if (currentGamesBeingPlayed1 == null) {

			currentGamesBeingPlayed1 = new HashMap<>();

			// currentGamesBeingPlayed1.put("" + game.getGameNumber(), game);
			//
			// servletContext.setAttribute("currentGamesBeingPlayed",
			// currentGamesBeingPlayed1);
			//
			// return true;

		}

		currentGamesBeingPlayed1.put("" + game.getGameNumber(), game);

		servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);

		return true;

	}

	public static List<Game> convertFromVCollectionToList(Collection<Game> collectionGames) {

		Iterator iter = collectionGames.iterator();

		List<Game> games = new ArrayList<>();

		while (iter.hasNext()) {

			Game gsme = (Game) iter.next();

			games.add(gsme);

		}

		return games;

	}

	public static List<Game> getAlltGamesFromApplicationScopeGameHashMap() {

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		// int gameNumber = game.getGameNumber();
		@SuppressWarnings("unchecked")
		Map<String, Game> currentGamesBeingPlayed1 = (Map<String, Game>) servletContext
				.getAttribute("currentGamesBeingPlayed");

		if (currentGamesBeingPlayed1 == null) {

			currentGamesBeingPlayed1 = new HashMap<>();

			servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);

			// Collection<Game> collGames =currentGamesBeingPlayed1.values();
			//
			// return (List<Game>) convertFromVCollectionToList(collGames);

		}

		Collection<Game> collGames = currentGamesBeingPlayed1.values();

		return (List<Game>) convertFromVCollectionToList(collGames);

	}

	public static Game getGameFromApplicationScopeGameHashMap(String userGameId) {

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		// int gameNumber = game.getGameNumber();
		@SuppressWarnings("unchecked")
		Map<String, Game> currentGamesBeingPlayed1 = (Map<String, Game>) servletContext
				.getAttribute("currentGamesBeingPlayed");

		if (currentGamesBeingPlayed1 == null) {
			currentGamesBeingPlayed1 = new HashMap<>();

			servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);

			return null;

		}

		Game game = (Game) currentGamesBeingPlayed1.get(userGameId);

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
			log.error(e);
			return false;
		}

		out.println(
				"<BR>Player 1 " + this.getPlayer1().getUsername() + " Hand: " + this.getPlayers1HandsForDisplay(user));
		out.println("<BR>Player 1 " + this.getPlayer1().getUsername() + " Bets: "
				+ this.getPlayer1BetAmountsForDisplay(user));

		out.println("<BR>");

		out.println("<BR>Player 2 "
				+ (this.getPlayer2() == null ? "Player 2 has not joined game yet" : this.getPlayer2().getUsername())
				+ " Hand: " + this.getPlayers2HandsForDisplay(user));
		out.println("<BR>Player 2 "
				+ (this.getPlayer2() == null ? "Player 2 has not joined game yet" : this.getPlayer2().getUsername())
				+ " Bets: " + this.getPlayer2BetAmountsForDisplay(user));
		out.println("<BR>");

		String whoseTurnIsIt = this.whoseTurnIsIt();

		if (this.getDidPlayer1Fold() != null && this.getDidPlayer1Fold().equalsIgnoreCase("yes")) {

			out.println("<BR>Player 1 has folded and the game is now complete and by foreit Player 2 "
					+ this.getPlayer2().getUsername()
					+ " wins.<script language=\"javascript\" type=\"text/javascript\">" +

			"isGameOver = 'yes';</script>");

			try {

				if (this.getTotalBetForWinner() > 0) {

				} else {

					this.setWinnerUserName(this.getPlayer2().getUsername());

					this.setPlayer1UserName(this.getPlayer1().getUsername());

					this.setPlayer2UserName(this.getPlayer2().getUsername());

					this.setTotalBetForWinner(this.getPlayer1betCard2() + this.getPlayer1betCard3()
							+ this.getPlayer1betCard4() + this.getPlayer1betCard5());

					this.insertIntoGamesForGameComplete();

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		}

		else if (this.getDidPlayer2Fold() != null && this.getDidPlayer2Fold().equalsIgnoreCase("yes")) {

			out.println("<BR>Player 2 has folded and the game is now complete and by foreit Player 1 "
					+ this.getPlayer1().getUsername()
					+ " wins.<script language=\"javascript\" type=\"text/javascript\">" +

			"isGameOver = 'yes';</script>");

			try {

				if (this.getTotalBetForWinner() > 0) {

				} else {

					this.setWinnerUserName(this.getPlayer1().getUsername());

					this.setPlayer1UserName(this.getPlayer1().getUsername());

					this.setPlayer2UserName(this.getPlayer2().getUsername());

					this.setTotalBetForWinner(this.getPlayer2betCard2() + this.getPlayer2betCard3()
							+ this.getPlayer2betCard4() + this.getPlayer2betCard5());

					this.insertIntoGamesForGameComplete();

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		} else if (whoseTurnIsIt.equals(Game.GAME_COMPLETE) || whoseTurnIsIt.equals(Game.FINAL_CARD_BET)) {

			out.println("<BR>The game is now complete.<BR><BR>" +

			"<script language=\"javascript\" type=\"text/javascript\">" +

			"isGameOver = 'yes';</script>");

			try {

				// int player1HandRanking = getPlayer1HandRanking(out);

				CardRanking player1HandRanking = getPlayer1HandRanking(out);

				this.setPlayer1FinalHandRanking(player1HandRanking.getStringCardRanking());

				// int player2HandRanking = getPlayer2HandRanking(out);

				CardRanking player2HandRanking = getPlayer2HandRanking(out);

				this.setPlayer2FinalHandRanking(player2HandRanking.getStringCardRanking());

				if (player1HandRanking.getIntCardRanking() > player2HandRanking.getIntCardRanking()) {

					this.setWinnerUserName(this.getPlayer1().getUsername());

					out.println("<BR><BR>Player #1 " + this.getPlayer1().getUsername() + " is the Winner of $" + this.getTotalBetForWinner() + "!!!");

				} else if (player1HandRanking.getIntCardRanking() < player2HandRanking.getIntCardRanking()) {

					this.setWinnerUserName(this.getPlayer2().getUsername());

					out.println("<BR><BR>Player #2 " + this.getPlayer2().getUsername() + " is the Winner of $" + this.getTotalBetForWinner() + "!!!");

				} else {

					int player1HighCard = this.getHighCard(this.getPlayer1card1(), this.getPlayer1card2(),
							this.getPlayer1card3(), this.getPlayer1card4(), this.getPlayer1card5());

					int player2HighCard = this.getHighCard(this.getPlayer2card1(), this.getPlayer2card2(),
							this.getPlayer2card3(), this.getPlayer2card4(), this.getPlayer2card5());

					if (player1HighCard > player2HighCard) {

						out.println("<BR><BR>Player #1 " + this.getPlayer1().getUsername() + " is the Winner of $" + this.getTotalBetForWinner() + "!!!");
						
						if(player1HighCard == 1) { //Ace

							this.setPlayer1FinalHandRanking("player 1 with high card of Ace"); // Here
							
						}
						else if(player1HighCard <= 10) { // 10 or less but not Ace
							
							this.setPlayer1FinalHandRanking("player 1 with high card of " + player1HighCard); // Here
							
						}
						else if(player1HighCard == 11) { // Jack

							this.setPlayer1FinalHandRanking("player 1 with high card of Jack"); // Here
							
						}
						else if(player1HighCard == 12) { // Quean
							
							this.setPlayer1FinalHandRanking("player 1 with high card of Queen"); // Here
							
						}
						else if(player1HighCard == 13) { // King
							
							this.setPlayer1FinalHandRanking("player 1 with high card of King"); // Here
							
						}

//						this.setPlayer1FinalHandRanking("player 1 high card " + (player1HighCard == 1 : ); // Here

						this.setWinnerUserName(this.getPlayer1().getUsername());

					} else if (player1HighCard < player2HighCard) {

						out.println("<BR><BR>Player #2 " + this.getPlayer2().getUsername() + " is the Winner of $" + this.getTotalBetForWinner() + "!!!");
						
						if(player2HighCard == 1) { //Ace

							this.setPlayer2FinalHandRanking("player 2 with high card of Ace"); // Here
							
						}
						else if(player2HighCard <= 10) { // 10 or less but not Ace
							
							this.setPlayer2FinalHandRanking("player 2 with high card of " + player1HighCard); // Here
							
						}
						else if(player2HighCard == 11) { // Jack

							this.setPlayer2FinalHandRanking("player 2 with high card of Jack"); // Here
							
						}
						else if(player2HighCard == 12) { // Quean
							
							this.setPlayer2FinalHandRanking("player 2 with high card of Queen"); // Here
							
						}
						else if(player2HighCard == 13) { // King
							
							this.setPlayer2FinalHandRanking("player 2 with high card of King"); // Here
							
						}

//						this.setPlayer1FinalHandRanking("player 1 high card " + (player1HighCard == 1 : ); // Here

						this.setWinnerUserName(this.getPlayer2().getUsername());

					} else {

						this.setWinnerUserName("<BR><BR>Game is a Draw");

					}

				}

				if (this.getTotalBetForWinner() > 0) {

				} else {

					// this.setWinnerUserName(this.getPlayer1().getUserName());
					// //To Do 2015/12/11 1:00 PM LWF

					this.setPlayer1UserName(this.getPlayer1().getUsername());

					this.setPlayer2UserName(this.getPlayer2().getUsername());

					this.setTotalBetForWinner(this.getPlayer1betCard2() + this.getPlayer1betCard3()
							+ this.getPlayer1betCard4() + this.getPlayer1betCard5());

					this.insertIntoGamesForGameComplete();

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		}

		else if (whoseTurnIsIt.equals(Game.CURRENT_TURN_PLAYER1)) {
			out.println("<BR>It is now Player 1 " + this.getPlayer1().getUsername() + " Turn!");

		} else if (whoseTurnIsIt.equals(Game.CURRENT_TURN_PLAYER2)) {
			out.println("<BR>It is now Player 2 " + this.getPlayer2().getUsername() + " Turn!");

			// } else if (whoseTurnIsIt.equals(this.FINAL_CARD_BET)) {
			// out.println("<BR>It is now Final Card Bet " +
			// this.getPlayer1().getUserName() + " Turn!");
			//
		} else if (whoseTurnIsIt.equals(Game.UNKNOWN_STATE)) {
			out.println("<BR>The game is in amn unknown state. ");

		}

		out.flush();

		log.debug("\n\nDebug: Exiting method Game::outputToResponseOutputStream(user)\n\n");

		return true;

	}

	public boolean insertIntoGamesForGameComplete() throws Exception {

		log.debug("\n\nDebug: IN method insertIntoGamesForGameComplete()\n\n");

		this.setWinnerUserName("Winner not known");
		MySQLConnection connection = new MySQLConnection();
		String query2 = " insert into game (" + "" + "Player1, " + "" + "Player1Card1,  " + "Player1Card2, "
				+ "Player1BetCard2, " + "Player1Card3, " + "Player1BetCard3, " + "Player1Card4, " + "Player1BetCard4, "
				+ "Player1Card5, " + "Player1BetCard5, " + "" + "Player2, " + "Player2Card1, " + "Player2Card2, "
				+ "Player2BetCard2, " + "Player2Card3, " + "Player2BetCard3, " + "Player2Card4, " + "Player2BetCard4, "
				+ "Player2Card5, " + "Player2BetCard5, " + "" + "TotalBetForWinner, " + "WinnerUserName, " +

		"Player1UserName, " + "didPlayer1Fold," + "Player2UserName, " + "didPlayer2Fold," +

		"Player1FinalHandRanking, " + "Player2FinalHandRanking, " +

		"GameStatus)" +

		"" + "VALUES(" + "?," // "Player1, "
				+ "?," + // "Player1Card1, " +
				"?," + // "Player1Card2, " +
				"?," + // "Player1BetCard2, "
				"?," + // "Player1Card3, " +
				"?," + // "Player1BetCard3, " +
				"?," + // "Player1Card4, " +
				"?," + // "Player1BetCard4, "
				"?," + // "Player1Card5, " +
				"?," + // "Player1BetCard5, " + "" +
				"?, " + // "Player2, " +
				"?," + // "Player2Card1, "
				"?," + // "Player2Card2, " +
				"?, " + // "Player2BetCard2, " +
				"?, " + // "Player2Card3, " +
				"?, " + // "Player2BetCard3, "
				"?, " + // "Player2Card4, " +
				"?," + // "Player2BetCard4, " +
				"?, " + // "Player2Card5, "
				"?, " + // "Player2BetCard5, " + ""
				"?, " + // "TotalBetForWinner, " +
				"?, " + // "WinnerUserName, " +

		"?, " + // "Player1UserName, " +
				"?, " + // "didPlayer1Fold," +
				"?, " + // "Player2UserName, " +
				"?, " + // "didPlayer2Fold," +

		"?, " + // "Player1FinalHandRanking, " +
				"?, " + // "Player2FinalHandRanking, " +

		"?)"; // + "GameStatus)" +
		try (Connection conn = connection.getDatabaseConnection();
				PreparedStatement preparedStmt = conn.prepareStatement(query2)) {

			/*
			 * 
			 * String query = " insert into game (" + "" + "Player1, " +
			 * "Player1Card1,  " + "Player1Card2, Player1BetCard2, " +
			 * "Player1Card3, Player1BetCard3, " +
			 * "Player1Card4, Player1BetCard4, " +
			 * "Player1Card5, Player1BetCard5, " + "" + "Player2, " +
			 * "Player2Card1, " + "Player2Card2, Player2BetCard2, " +
			 * "Player2Card3, Player2BetCard3, " +
			 * "Player2Card4, Player2BetCard4, " +
			 * "Player2Card5, Player2BetCard5, " + "" +
			 * "TotalBetForWinner, WinnerUserName, " +
			 * 
			 * "Player1UserName, didPlayer1Fold," + // Added 2015/12/10 2:30 PM
			 * LWF "Player2UserName, didPlayer2Fold," + // Added 2015/12/10 2:30
			 * PM // LWF
			 * 
			 * "Player1FinalHandRanking, " + "Player2FinalHandRanking, " +
			 * 
			 * 
			 * "GameStatus)" +
			 * 
			 * "" + "VALUES(" + " " + "? , " + "?, " + "?, ?, " + "?, ?, " +
			 * "?, ?, " + "?, ?, " + "" + "?, " + "?, " + "?, ?, " + "?, ?, " +
			 * "?, ?, " + "?, ?, " + "" + "?, ?, " + "?, ?, " + // Added //
			 * 2015/12/10 // 2:30 // PM // LWF "?, ?, " + // Added 2015/12/10
			 * 2:30 PM LWF "?, ?, " + // Added 2015/12/10 2:30 PM LWF "?)";
			 * 
			 */

			// Calendar calendar = Calendar.getInstance();
			// java.sql.Date startDate = new
			// java.sql.Date(calendar.getTime().getTime());

			// String generatedSecuredPasswordHash = BCrypt.hashpw(password,
			// BCrypt.gensalt(12));

			// create the mysql insert preparedstatement

			/*
			 * 
			 * "" + "Player1, " + "Player1Card1,  " +
			 * "Player1Card2, Player1BetCard2, " +
			 * "Player1Card3, Player1BetCard3, " +
			 * "Player1Card4, Player1BetCard4, " +
			 * "Player1Card5, Player1BetCard5, " + "" + "Player2, " +
			 * "Player2Card1,  " + "Player2Card2, Player2BetCard2, " +
			 * "Player2Card3, Player2BetCard3, " +
			 * "Player2Card4, Player2BetCard4, " +
			 * "Player2Card5, Player2BetCard5, " + "" +
			 * "TotalBetForWinner, WinnerUserName, " + "GameStatus, SameState)"
			 * +
			 * 
			 * 
			 * "" +
			 * 
			 * 
			 */

			/*
			 * "?," //"Player1, " + "?," + //"Player1Card1,  " + "?," + //
			 * "Player1Card2, " + "?," + //"Player1BetCard2, " "?," + //
			 * "Player1Card3, " + "?," + //"Player1BetCard3, " + "?," + //
			 * "Player1Card4, " + "?," + //"Player1BetCard4, " "?," + //
			 * "Player1Card5, " + "?," + //"Player1BetCard5, " + "" + "?, " + //
			 * "Player2, " + "?," + //"Player2Card1, " "?," + //"Player2Card2, "
			 * + "?, " + //"Player2BetCard2, " + "?, " + //"Player2Card3, " +
			 * "?, " + //"Player2BetCard3, " "?, " + //"Player2Card4, " + "?," +
			 * // "Player2BetCard4, " + "?, " + //"Player2Card5, " "?, " + //
			 * "Player2BetCard5, " + "" "?, " + // "TotalBetForWinner, " + "?, "
			 * + //"WinnerUserName, " +
			 * 
			 * "?, " + //"Player1UserName, " + "?, " + //"didPlayer1Fold," +
			 * "?, " + //"Player2UserName, " + "?, " + //"didPlayer2Fold," +
			 * 
			 * 
			 * "?, " + //"Player1FinalHandRanking, " + "?, " + //
			 * "Player2FinalHandRanking, " +
			 * 
			 * 
			 * "?)"; //+ "GameStatus)" +
			 */

			preparedStmt.setInt(1, this.getPlayer1().getUserID()); //

			preparedStmt.setString(2, "" + this.getPlayer1card1()); //

			preparedStmt.setString(3, "" + this.getPlayer1card2());
			preparedStmt.setDouble(4, this.getPlayer1betCard2());

			preparedStmt.setString(5, "" + this.getPlayer1card3());
			preparedStmt.setDouble(6, this.getPlayer1betCard3());

			preparedStmt.setString(7, "" + this.getPlayer1card4());
			preparedStmt.setDouble(8, this.getPlayer1betCard4());

			preparedStmt.setString(9, "" + this.getPlayer1card5());
			preparedStmt.setDouble(10, this.getPlayer1betCard5());

			preparedStmt.setInt(11, this.getPlayer2().getUserID());

			preparedStmt.setString(12, "" + this.getPlayer2card1());

			preparedStmt.setString(13, "" + this.getPlayer2card2());
			preparedStmt.setDouble(14, this.getPlayer2betCard2());

			preparedStmt.setString(15, "" + this.getPlayer2card3());
			preparedStmt.setDouble(16, this.getPlayer2betCard3());

			preparedStmt.setString(17, "" + this.getPlayer2card4());
			preparedStmt.setDouble(18, this.getPlayer2betCard4());

			preparedStmt.setString(19, "" + this.getPlayer2card5());
			preparedStmt.setDouble(20, this.getPlayer2betCard5());

			preparedStmt.setDouble(21, this.getTotalBetForWinner());

			preparedStmt.setString(22, this.getWinnerUserName());

			preparedStmt.setString(23, this.getPlayer1UserName()); // Added
																	// 2015/12/10
																	// 2:30 PM
																	// LWF
			preparedStmt.setString(24, "" + this.getDidPlayer1Fold()); // Added
																		// 2015/12/10
																		// 2:30
																		// PM
																		// LWF

			preparedStmt.setString(25, this.getPlayer2UserName()); // Added
																	// 2015/12/10
																	// 2:30 PM
																	// LWF
			preparedStmt.setString(26, "" + this.getDidPlayer2Fold()); // Added
																		// 2015/12/10
																		// 2:30
																		// PM
																		// LWF

			preparedStmt.setString(27, this.getPlayer1FinalHandRanking()); // Added
																			// 2015/12/10
																			// 3:30
																			// PM
																			// LWF

			preparedStmt.setString(28, this.getPlayer2FinalHandRanking()); // Added
																			// 2015/12/10
																			// 3:30
																			// PM
																			// LWF

			preparedStmt.setString(29, "Game Finished");

			// byteArrayInputStream

			// byte[] buf = new byte[500];

			// ByteArrayInputStream byteArrayInputStream = new
			// ByteArrayInputStream(buf);

			// preparedStmt.setBlob(24, (InputStream)byteArrayInputStream);

			// InputStream inputStream = new BufferInputStream();

			// preparedStmt.setBlob(24, inputStream);

			// preparedStmt.setDate (3, startDate);

			// execute the preparedstatement

			log.debug("\n\n SQL insert into gamess table = XXX\n\n" + query2 + "\n\nXXX");

			preparedStmt.executeUpdate();


		
		} catch (ClassNotFoundException|SQLException|IOException e) {


			log.error(e);

			// request.setAttribute("errors", errors);

			log.debug(
					"\n\nDebug: IN method insertIntoGamesForGameComplete() but could not insert into the Game table. Game is finished.\n\n");

			return false;

		}

		// request.setAttribute("errors", errors);

		log.debug("\n\nDebug: Exiting Method insertIntoGamesForGameComplete() reutrning 'success'\n\n");

		return true;

	}

	public int getHighCard(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return -1;
		}

		
		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int highCard = -1;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (highCard < playingCardTemp.getCardNumber()) {
				highCard = playingCardTemp.getCardNumber();
			}

		}

		return highCard;

	}

	public boolean isPair(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		int count = 0;

		boolean isPair = false;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {

				++count;

				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();

			} else if (previousCardNumber == playingCardTemp.getCardNumber()) {

				++count;

				// return false;
			} else if (previousCardNumber != playingCardTemp.getCardNumber()) {

				if (count == 2) {

					isPair = true;

				}

				count = 1;

			}

		}

		return isPair;

	}

	public boolean isFourStraight(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		int count = 0;

		boolean isFourStraight = false;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {

				++count;

				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();
			} else if ((++previousCardNumber) != playingCardTemp.getCardNumber()) {

				if (count == 4) {

					isFourStraight = true;

				}

				count = 1;
			} else {

				++count;

			}

		}

		return isFourStraight;

	}

	public boolean isFourFlush(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardBySuitNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		int count = 0;

		boolean isFourFlush = false;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {

				++count;

				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();
			} else if (!suit.equals(playingCardTemp.getCardSuit())) {

				if (count == 4) {

					isFourFlush = true;

				}

				count = 1;

			} else {
				++count;
			}

		}

		return isFourFlush;

	}

	public boolean isTwoPair(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		int count = 0;

		boolean isFirstPair = false;

		boolean isSecondPair = false;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {

				++count;

				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();

			} else if (previousCardNumber == playingCardTemp.getCardNumber()) {

				++count;

				// return false;

			} else if (previousCardNumber != playingCardTemp.getCardNumber()) {

				if (count == 2) {

					if (!isFirstPair) {

						isFirstPair = true;

					} else if (!isSecondPair) {

						isSecondPair = true;

					}

				}

				count = 1;

			}

		}

		return isFirstPair && isSecondPair;

	}

	public boolean isThreeOfaKind(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		int count = 0;

		boolean isThreeOfKind = false;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {

				++count;

				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();

			} else if (previousCardNumber == playingCardTemp.getCardNumber()) {

				++count;

				// return false;
			} else if (previousCardNumber != playingCardTemp.getCardNumber()) {

				if (count == 3) {

					isThreeOfKind = true;

				}

				count = 1;

			}

		}

		return isThreeOfKind;

	}

	public boolean isStraight(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {
				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();
			} else if ((++previousCardNumber) != playingCardTemp.getCardNumber()) {
				return false;
			}

		}

		return true;

	}

	public boolean isFlush(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardBySuitNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {
				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();
			} else if (!suit.equals(playingCardTemp.getCardSuit())) {
				return false;
			}

		}

		return true;

	}

	public boolean isStraightFlush(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {
				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();
			} else if (!suit.equals(playingCardTemp.getCardSuit())
					|| (++previousCardNumber) != playingCardTemp.getCardNumber()) {
				return false;
			}

		}

		return true;

	}

	public boolean isFullHouse(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		int count = 0;

		boolean isTwoOfKind = false;

		boolean isThreeOfKind = false;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {

				++count;

				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();

			} else if (previousCardNumber == playingCardTemp.getCardNumber()) {

				++count;

				// return false;
			} else if (previousCardNumber != playingCardTemp.getCardNumber()) {

				if (count == 2) {
					isTwoOfKind = true;

				} else if (count == 3) {
					isThreeOfKind = true;

				}

				count = 1;

			}

		}

		return isTwoOfKind && isThreeOfKind;

	}

	public boolean isFourOfaKind(PlayingCard card1, PlayingCard card2, PlayingCard card3, PlayingCard card4,
			PlayingCard card5) {

		if (card1 == null || card2 == null || card3 == null || card4 == null || card5 == null) {
			return false;
		}

		List<PlayingCard> playingCardArrayList = new ArrayList<PlayingCard>();
		
		
		
		playingCardArrayList.add(card1);
		playingCardArrayList.add(card2);
		playingCardArrayList.add(card3);
		playingCardArrayList.add(card4);
		playingCardArrayList.add(card5);
		
		Collections.sort(playingCardArrayList, PlayingCard.playingCardByCardNumberNameComparator);
		

		Iterator<PlayingCard> iterPlayingCard = playingCardArrayList.iterator();

		String suit = null;

		int previousCardNumber = -1;

		while (iterPlayingCard.hasNext()) {

			PlayingCard playingCardTemp = iterPlayingCard.next();

			if (suit == null) {
				suit = playingCardTemp.getCardSuit();

				previousCardNumber = playingCardTemp.getCardNumber();

			} else if (previousCardNumber != playingCardTemp.getCardNumber()) {
				return false;
			}

		}

		return true;

	}

	public CardRanking getPlayer1HandRanking(PrintWriter out) {

		CardRanking cardRanking = new CardRanking();

		if (this.isStraightFlush(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: a StraightFlush");

			cardRanking.setIntCardRanking(11);

			cardRanking.setStringCardRanking("Straight Flush");

			return cardRanking;

			// return 11;

		} else if (this.isFourOfaKind(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: Four of a Kind");

			cardRanking.setIntCardRanking(10);

			cardRanking.setStringCardRanking("Four of a Kind");

			return cardRanking;

			// return 10;

		} else if (this.isFullHouse(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: a full House");

			// out.println("<BR><BR>Player 1 has: Four of a Kind");

			cardRanking.setIntCardRanking(9);

			cardRanking.setStringCardRanking("Full House");

			return cardRanking;

			// return 9;

		} else if (this.isFlush(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: a Flush");

			cardRanking.setIntCardRanking(8);

			cardRanking.setStringCardRanking("Flush");

			return cardRanking;

			// return 8;

		} else if (this.isStraight(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: a Straight");

			cardRanking.setIntCardRanking(7);

			cardRanking.setStringCardRanking("Straight");

			return cardRanking;

			// return 7;

		} else if (this.isThreeOfaKind(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: Three of a Kind");

			cardRanking.setIntCardRanking(6);

			cardRanking.setStringCardRanking("Three of a Kind");

			return cardRanking;

			// return 6;

		} else if (this.isTwoPair(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: Two Pairs");

			cardRanking.setIntCardRanking(5);

			cardRanking.setStringCardRanking("Two Pairs");

			return cardRanking;

			// return 5;

		} else if (this.isFourFlush(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: a Four Card Flush");

			cardRanking.setIntCardRanking(4);

			cardRanking.setStringCardRanking("Four Card Flush");

			return cardRanking;

			// return 4;

		} else if (this.isFourStraight(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: a Four Card Straight");

			cardRanking.setIntCardRanking(3);

			cardRanking.setStringCardRanking("Four Card Staight");

			return cardRanking;

			// return 3;

		} else if (this.isPair(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
				this.getPlayer1card4(), this.getPlayer1card5())) {

			out.println("<BR><BR>Player 1 has: a Pair");

			cardRanking.setIntCardRanking(2);

			cardRanking.setStringCardRanking("a Pair");

			return cardRanking;

			// return 2;

		} else {

			int highCard = this.getHighCard(this.getPlayer1card1(), this.getPlayer1card2(), this.getPlayer1card3(),
					this.getPlayer1card4(), this.getPlayer1card5());

			if (highCard == 1) {

				out.println("<BR><BR>Player 1 has: a High Card of Ace");

				cardRanking.setStringCardRanking("Ace high card");

			} else if (highCard <= 10) {

				out.println("<BR><BR>Player 1 has: a High Card of " + highCard);

				cardRanking.setStringCardRanking("high card " + highCard);

			} else if (highCard == 11) {

				out.println("<BR><BR>Player 1 has: a High Card of Jack");

				cardRanking.setStringCardRanking("Jack high card ");

			} else if (highCard == 12) {

				out.println("<BR><BR>Player 1 has: a High Card of Queen");

				cardRanking.setStringCardRanking("Queen high card ");

			} else if (highCard == 13) {

				out.println("<BR><BR>Player 1 has: a High Card of King");

				cardRanking.setStringCardRanking("King high card ");

			} else {

				out.println("<BR><BR>Player 1 has: No Cards");

				cardRanking.setStringCardRanking("No Cards");

			}

			cardRanking.setIntCardRanking(1);

			return cardRanking;

			// return 1;

		}

	}

	/*
	 * public int getPlayer2HandRanking(PrintWriter out) {
	 * 
	 * if (this.isStraightFlush(this.getPlayer2card1(), this.getPlayer2card2(),
	 * this.getPlayer2card3(), this.getPlayer2card4(), this.getPlayer2card5()))
	 * {
	 * 
	 * out.println("<BR><BR>Player 2 has: a StraightFlush");
	 * 
	 * return 11;
	 * 
	 * } else if (this.isFourOfaKind(this.getPlayer2card1(),
	 * this.getPlayer2card2(), this.getPlayer2card3(), this.getPlayer2card4(),
	 * this.getPlayer2card5())) {
	 * 
	 * out.println("<BR><BR>Player 2 has: Four of a Kind");
	 * 
	 * return 10;
	 * 
	 * } else if (this.isFullHouse(this.getPlayer2card1(),
	 * this.getPlayer2card2(), this.getPlayer2card3(), this.getPlayer2card4(),
	 * this.getPlayer2card5())) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a full House");
	 * 
	 * return 9;
	 * 
	 * } else if (this.isFlush(this.getPlayer2card1(), this.getPlayer2card2(),
	 * this.getPlayer2card3(), this.getPlayer2card4(), this.getPlayer2card5()))
	 * {
	 * 
	 * out.println("<BR><BR>Player 2 has: a Flush");
	 * 
	 * return 8;
	 * 
	 * } else if (this.isStraight(this.getPlayer2card1(),
	 * this.getPlayer2card2(), this.getPlayer2card3(), this.getPlayer2card4(),
	 * this.getPlayer2card5())) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a Straight");
	 * 
	 * return 7;
	 * 
	 * } else if (this.isThreeOfaKind(this.getPlayer2card1(),
	 * this.getPlayer2card2(), this.getPlayer2card3(), this.getPlayer2card4(),
	 * this.getPlayer2card5())) {
	 * 
	 * out.println("<BR><BR>Player 2 has: Three of a Kind");
	 * 
	 * return 6;
	 * 
	 * } else if (this.isTwoPair(this.getPlayer2card1(), this.getPlayer2card2(),
	 * this.getPlayer2card3(), this.getPlayer2card4(), this.getPlayer2card5()))
	 * {
	 * 
	 * out.println("<BR><BR>Player 2 has: Two Pairs");
	 * 
	 * return 5;
	 * 
	 * } else if (this.isFourFlush(this.getPlayer2card1(),
	 * this.getPlayer2card2(), this.getPlayer2card3(), this.getPlayer2card4(),
	 * this.getPlayer2card5())) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a Four Card Flush");
	 * 
	 * return 4;
	 * 
	 * } else if (this.isFourStraight(this.getPlayer2card1(),
	 * this.getPlayer2card2(), this.getPlayer2card3(), this.getPlayer2card4(),
	 * this.getPlayer2card5())) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a Four Card Straight");
	 * 
	 * return 3;
	 * 
	 * } else if (this.isPair(this.getPlayer2card1(), this.getPlayer2card2(),
	 * this.getPlayer2card3(), this.getPlayer2card4(), this.getPlayer2card5()))
	 * {
	 * 
	 * out.println("<BR><BR>Player 2 has: a Pair");
	 * 
	 * return 2;
	 * 
	 * } else {
	 * 
	 * int highCard = this.getHighCard(this.getPlayer2card1(),
	 * this.getPlayer2card2(), this.getPlayer2card3(), this.getPlayer2card4(),
	 * this.getPlayer2card5());
	 * 
	 * if (highCard == 1) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a High Card of Ace");
	 * 
	 * } else if (highCard <= 10) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a High Card of " + highCard);
	 * 
	 * } else if (highCard == 11) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a High Card of Jack");
	 * 
	 * } else if (highCard == 12) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a High Card of Queen");
	 * 
	 * } else if (highCard == 13) {
	 * 
	 * out.println("<BR><BR>Player 2 has: a High Card of King");
	 * 
	 * } else {
	 * 
	 * out.println("<BR><BR>Player 2 has: No Cards");
	 * 
	 * }
	 * 
	 * return 1;
	 * 
	 * }
	 * 
	 * }
	 */

	public CardRanking getPlayer2HandRanking(PrintWriter out) {

		CardRanking cardRanking = new CardRanking();

		if (this.isStraightFlush(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: a StraightFlush");

			cardRanking.setIntCardRanking(11);

			cardRanking.setStringCardRanking("Straight Flush");

			return cardRanking;

			// return 11;

		} else if (this.isFourOfaKind(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: Four of a Kind");

			cardRanking.setIntCardRanking(10);

			cardRanking.setStringCardRanking("Four of a Kind");

			return cardRanking;

			// return 10;

		} else if (this.isFullHouse(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: a full House");

			// out.println("<BR><BR>Player 2 has: Four of a Kind");

			cardRanking.setIntCardRanking(9);

			cardRanking.setStringCardRanking("Full House");

			return cardRanking;

			// return 9;

		} else if (this.isFlush(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: a Flush");

			cardRanking.setIntCardRanking(8);

			cardRanking.setStringCardRanking("Flush");

			return cardRanking;

			// return 8;

		} else if (this.isStraight(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: a Straight");

			cardRanking.setIntCardRanking(7);

			cardRanking.setStringCardRanking("Straight");

			return cardRanking;

			// return 7;

		} else if (this.isThreeOfaKind(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: Three of a Kind");

			cardRanking.setIntCardRanking(6);

			cardRanking.setStringCardRanking("Three of a Kind");

			return cardRanking;

			// return 6;

		} else if (this.isTwoPair(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: Two Pairs");

			cardRanking.setIntCardRanking(5);

			cardRanking.setStringCardRanking("Two Pairs");

			return cardRanking;

			// return 5;

		} else if (this.isFourFlush(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: a Four Card Flush");

			cardRanking.setIntCardRanking(4);

			cardRanking.setStringCardRanking("Four Card Flush");

			return cardRanking;

			// return 4;

		} else if (this.isFourStraight(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: a Four Card Straight");

			cardRanking.setIntCardRanking(3);

			cardRanking.setStringCardRanking("Four Card Staight");

			return cardRanking;

			// return 3;

		} else if (this.isPair(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
				this.getPlayer2card4(), this.getPlayer2card5())) {

			out.println("<BR><BR>Player 2 has: a Pair");

			cardRanking.setIntCardRanking(2);

			cardRanking.setStringCardRanking("a Pair");

			return cardRanking;

			// return 2;

		} else {

			int highCard = this.getHighCard(this.getPlayer2card1(), this.getPlayer2card2(), this.getPlayer2card3(),
					this.getPlayer2card4(), this.getPlayer2card5());

			if (highCard == 1) {

				out.println("<BR><BR>Player 2 has: a High Card of Ace");

				cardRanking.setStringCardRanking("Ace high card");

			} else if (highCard <= 10) {

				out.println("<BR><BR>Player 2 has: a High Card of " + highCard);

				cardRanking.setStringCardRanking("high card " + highCard);

			} else if (highCard == 11) {

				out.println("<BR><BR>Player 2 has: a High Card of Jack");

				cardRanking.setStringCardRanking("Jack high card");

			} else if (highCard == 12) {

				out.println("<BR><BR>Player 2 has: a High Card of Queen");

				cardRanking.setStringCardRanking("Queen high card");

			} else if (highCard == 13) {

				out.println("<BR><BR>Player 2 has: a High Card of King");

				cardRanking.setStringCardRanking("King high card");

			} else {

				out.println("<BR><BR>Player 2 has: No Cards");

				cardRanking.setStringCardRanking("No Cards");

			}

			cardRanking.setIntCardRanking(1);

			return cardRanking;

			// return 1;

		}

	}

	public static boolean displayAllGamesOrderByGameId(PrintWriter out) throws Exception {

		log.debug("\n\nDebug: inside displayAllGamesOrderByGameId()\n");
		MySQLConnection connection = new MySQLConnection();
		try ( // create the java statement

				Connection conn = connection.getDatabaseConnection()) {

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM game order by GameID ";
			// pstmnt.setString(1, userName);
			// execute the query, and get a java resultset
			try ( // create the java statement
					PreparedStatement pstmnt = conn.prepareStatement(query); // pstmnt.setString(1,
																				// userName);
					// execute the query, and get a java resultset
					ResultSet rs = pstmnt.executeQuery()) {

				/*
				 * -- -- Table structure for table `users` --
				 *
				 * CREATE TABLE IF NOT EXISTS `users` ( `UserID` int(11) NOT
				 * NULL AUTO_INCREMENT, `UserName` varchar(20) NOT NULL,
				 * `Password` char(60) NOT NULL, `Timestamp` timestamp NOT NULL
				 * DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (`UserID`), UNIQUE KEY
				 * `UserName` (`UserName`) ) ENGINE=InnoDB DEFAULT
				 * CHARSET=latin1 AUTO_INCREMENT=3 ;
				 *
				 */

				// iterate through the java resultset

				out.print("<BR><BR>Listing of Completed Games:");
				// out.print("Game Id"Listing of Completed Games:<BR><BR>");

				while (rs != null && rs.next()) {

					out.println("<BR><BR>");

					int gameId_ = rs.getInt("GameID");

					// int userId1 = rs.getInt("UserId");
					// String userName1 = rs.getString("UserName");
					// String password1 = rs.getString("Password");

					// Started Paste here 2015/12/13 12:00 PM LWF

					// preparedStmt.setInt(1, this.getPlayer1().getUserID()); //

					String Player1Card1_ = rs.getString("Player1Card1");

					String Player1Card2_ = rs.getString("Player1card1");
					double Player1betCard2_ = rs.getDouble("Player1betCard2");

					String Player1Card3_ = rs.getString("Player1card3");
					double Player1betCard3_ = rs.getDouble("Player1betCard3");

					String Player1Card4_ = rs.getString("Player1card4");
					double Player1betCard4_ = rs.getDouble("Player1betCard4");

					String Player1Card5_ = rs.getString("Player1card5");
					double Player1betCard5_ = rs.getDouble("Player1betCard5");

					// preparedStmt.setString(2, "" + this.getPlayer1card1());
					// //
					//
					// preparedStmt.setString(3, "" + this.getPlayer1card2());
					// preparedStmt.setDouble(4, this.getPlayer1betCard2());
					//
					// preparedStmt.setString(5, "" + this.getPlayer1card3());
					// preparedStmt.setDouble(6, this.getPlayer1betCard3());

					// preparedStmt.setString(7, "" + this.getPlayer1card4());
					// preparedStmt.setDouble(8, this.getPlayer1betCard4());

					// preparedStmt.setString(9, "" + this.getPlayer1card5());
					// preparedStmt.setDouble(10, this.getPlayer1betCard5());
					//
					// preparedStmt.setInt(11, this.getPlayer2().getUserID());

					String Player2Card1_ = rs.getString("Player2Card1");

					String Player2Card2_ = rs.getString("Player2card1");
					double Player2betCard2_ = rs.getDouble("Player2betCard2");

					String Player2Card3_ = rs.getString("Player2card3");
					double Player2betCard3_ = rs.getDouble("Player2betCard3");

					String Player2Card4_ = rs.getString("Player2card4");
					double Player2betCard4_ = rs.getDouble("Player2betCard4");

					String Player2Card5_ = rs.getString("Player2card5");
					double Player2betCard5_ = rs.getDouble("Player2betCard5");

					// preparedStmt.setString(12, "" + this.getPlayer2card1());
					//
					// preparedStmt.setString(13, "" + this.getPlayer2card2());
					// preparedStmt.setDouble(14, this.getPlayer2betCard2());
					//
					// preparedStmt.setString(15, "" + this.getPlayer2card3());
					// preparedStmt.setDouble(16, this.getPlayer2betCard3());
					//
					// preparedStmt.setString(17, "" + this.getPlayer2card4());
					// preparedStmt.setDouble(18, this.getPlayer2betCard4());
					//
					// preparedStmt.setString(19, "" + this.getPlayer2card5());
					// preparedStmt.setDouble(20, this.getPlayer2betCard5());

					double TotalBetForWinner_ = rs.getDouble("TotalBetForWinner");

					String WinnerUserName_ = rs.getString("WinnerUserName");

					String Player1UserName_ = rs.getString("Player1UserName");
					String DidPlayer1Fold_ = rs.getString("DidPlayer1Fold");

					String Player2UserName_ = rs.getString("Player2UserName");
					String DidPlayer2Fold_ = rs.getString("DidPlayer2Fold");

					String Player1FinalHandRanking_ = rs.getString("Player1FinalHandRanking");

					String Player2FinalHandRanking_ = rs.getString("Player2FinalHandRanking");

					String GameStatus_ = rs.getString("GameStatus");

					out.println("Game Id: " + gameId_ + " ");

					out.println("Player #1: " + Player1UserName_ + ". ");
					out.println("Player #2: " + Player2UserName_ + ". ");

					if (DidPlayer1Fold_ != null && DidPlayer1Fold_.equalsIgnoreCase("yes")) {

						out.println("Player 1 folded and so Player 2 won $" + TotalBetForWinner_ +". ");

					} else if (DidPlayer2Fold_ != null && DidPlayer2Fold_.equalsIgnoreCase("yes")) {

						out.println("Player 2 folded and so Player 1 won $" + TotalBetForWinner_ +". ");

					} else if (TotalBetForWinner_ > 0 && WinnerUserName_ != null && Player1UserName_ != null
							&& WinnerUserName_.equals(Player1UserName_)) {

						out.println("Player 1 won with: " + Player1FinalHandRanking_ + " $" + TotalBetForWinner_ +". ");

						out.println("Player2 lost with: " + Player2FinalHandRanking_ + ". ");

					} else if (TotalBetForWinner_ > 0 && WinnerUserName_ != null && Player2UserName_ != null
							&& WinnerUserName_.equals(Player2UserName_)) {

						out.println("Player 2 won with: " + Player1FinalHandRanking_ + " $" + TotalBetForWinner_ +". ");

						out.println("Player 1 lost with: " + Player2FinalHandRanking_ + ". ");

					} else {

						out.println("Game was never completed.  No Winner determined. ");

					}

					// preparedStmt.setDouble(21, this.getTotalBetForWinner());

					// preparedStmt.setString(22, this.getWinnerUserName());
					//
					//
					// preparedStmt.setString(23, this.getPlayer1UserName()); //
					// Added
					// // 2015/12/10
					// // 2:30 PM LWF
					// preparedStmt.setString(24, "" +
					// this.getDidPlayer1Fold()); //
					// Added
					// // 2015/12/10
					// // 2:30 PM
					// // LWF
					//
					// preparedStmt.setString(25, this.getPlayer2UserName()); //
					// Added
					// // 2015/12/10
					// // 2:30 PM LWF
					// preparedStmt.setString(26, "" +
					// this.getDidPlayer2Fold()); //
					// Added
					// // 2015/12/10
					// // 2:30 PM
					// // LWF
					//
					// preparedStmt.setString(27,
					// this.getPlayer1FinalHandRanking()); // Added
					// // 2015/12/10
					// // 3:30
					// // PM
					// // LWF
					//
					// preparedStmt.setString(28,
					// this.getPlayer2FinalHandRanking()); // Added
					// // 2015/12/10
					// 3:30
					// PM
					// LWF

					// preparedStmt.setString(29, "Game Finished");

					// Ended Paste 2015/12/13 12:00 PM LWF

					// user = new User(-1,"","",new Timestamp(1));
					//
					// user.setUserID(userId1);
					// user.setUserName(userName1);
					// user.setPassword(password1);

					// Date dateCreated = rs.getDate("date_created");
					// boolean isAdmin = rs.getBoolean("is_admin");
					// int numPoints = rs.getInt("num_points");

					// print the results
					// System.out.format("%s, %s, %s, %s, %s, %s\n", id,
					// firstName,
					// lastName, dateCreated, isAdmin, numPoints);
				}

				rs.close();
				pstmnt.close();
				conn.close();
			} catch (Throwable t) {
				t.printStackTrace();
				throw t;
			}

		} catch (Throwable t) {
			log.error(t);
			throw t;
		}
		log.debug("\n\nDebug: exiting displayAllGamesOrderByGameId()\n");

		return true;
	}

}
