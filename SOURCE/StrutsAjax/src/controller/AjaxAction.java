package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import deck.cards.DeckOfCards;
import deck.cards.Game;
import deck.cards.PlayingCard;
import entities.*;

import java.util.*;

public class AjaxAction extends ActionSupport implements LoginRequired {

	/*
	 * Author: Linus Freeman
	 * 
	 */

	final static Logger log = Logger.getLogger(FiveCardStudPokerAjaxAction.class);

	private int a;
	private int b;
	private Map<String, Object> jsonData = new HashMap<String, Object>();

	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public void waitForTurnOrGameToStart() {
		
		HttpSession httpSession = ServletActionContext.getRequest().getSession();

		User user = (User) httpSession.getAttribute("user");

		if (user == null) {

			log.debug(
					"\n\nDebug: In method waitForTurnOrGameToStart() and user does not exist in [session] scope and exiting method.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println("There is a problem with your logon.");
			out.flush();

			return;

		}

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		List<Game> games = (ArrayList<Game>) servletContext.getAttribute("Games");
		


		if (games == null) {

			log.debug(
					"\n\nDebug: In method waitForTurnOrGameToStart() and [Games] does not exist in application scope and exiting method.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println("No curent existing Games available to Join.");
			out.flush();

			return;
		}
		
		Game game = (Game)httpSession.getAttribute("Game");

		
		//ServletContext servletContext = ServletActionContext.getRequest().getServletContext();
		
		int gameNumber = game.getGameNumber();
		
		Map<String,Game> currentGamesBeingPlayed1 = (Map<String,Game>)servletContext.getAttribute("currentGamesBeingPlayed");
		
		Game gameTemp = null;

		
		
		
		if (currentGamesBeingPlayed1 == null) {

			log.debug(
					"\n\nDebug: In method waitForTurnOrGameToStart() and [Application] scope [currentGamesBeingPlayed] does not  exist. \n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"<BR><BR>Debug: In method waitForTurnOrGameToStart() and [Application] scope [currentGamesBeingPlayed] does not  exist. Please start new game orjoin an existing game.<BR><BR>");

			out.flush();

			return;
			
			
		}
		
		
		Game gameApplicationScope_ = currentGamesBeingPlayed1.get("" +gameNumber );
		
		
		//Game appSoceGame = currentGamesBeingPlayed1.get("" + game.getGameNumber());
		
		if(gameApplicationScope_ == null) {
			
			
			log.debug(
					"\n\nDebug: In method waitForTurnOrGameToStart() and [Application] scope [currentGamesBeingPlayed] does not contain session  scope game number. \n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"<BR><BR>Debug: In method waitForTurnOrGameToStart() and [Application] scope [currentGamesBeingPlayed] does not contain session  scope game number. Please start new game orjoin an existing game.<BR><BR>");

			out.flush();

			return;
			
		}

//		if (game.getPlayer1().getUserName().equals(user.getUserName())  ) {
			
			
			
			servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
			
			httpSession.setAttribute("Game", game);

			
			log.debug(
					"\n\nDebug: In method waitForTurnOrGameToStart() Player 1 has bet and now it is player 2's turn.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Hand: " + game.getPlayers1HandsForDisplay(user));
			out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Bets: " + game.getPlayer1BetAmountsForDisplay(user));

			out.println("<BR>");
			
			out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Hand: " + game.getPlayers2HandsForDisplay(user));
			out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Bets: " + game.getPlayer2BetAmountsForDisplay(user));
			out.println("<BR>");
			
			String whoseTurnIsIt = game.whoseTurnIsIt();
			
			if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER1)) {
				out.println("<BR>It is now Player 1 " + game.getPlayer1().getUserName() + " Turn!");
				
			}
			else if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER2)) {
				out.println("<BR>It is now Player 2 " + game.getPlayer1().getUserName() + " Turn!");
				
			}
			else if(whoseTurnIsIt.equals(game.FINAL_CARD_BET)) {
				out.println("<BR>It is now Final Card Bet " + game.getPlayer1().getUserName() + " Turn!");
				
			}
			else if(whoseTurnIsIt.equals(game.GAME_COMPLETE)) {
				out.println("<BR>The game is now coplete. ");
				
			}
			else if(whoseTurnIsIt.equals(game.UNKNOWN_STATE)) {
				out.println("<BR>The game is in amn unknown state. ");
				
			}
			

			
			out.flush();
			
			log.debug(
					"\n\nDebug: Exiting method waitForTurnOrGameToStart()\n\n");


			return;
			
	//	}


		
		
		
		
		
		
	}
		
	
	public void raise() {
		
		log.debug("\n\nDebug: In method raise()\n\n");

		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		
		if (user == null) {

			log.debug(
					"\n\nDebug: In method raise() and Session scope hss null for user object.  Please logon.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"\n\nDebug: In method raise() and Session scope hss null for user object.  Please logon.\n\n");

			out.flush();

			return;
			
			
		}

		
		Game game = (Game)httpSession.getAttribute("Game");
		
		if(game == null) {

			log.debug(
					"\n\nDebug: In method raise() and Session scope has no Game object. \n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"<BR><BR>Debug: In method raise() and Session scope has no Game object. Please join a game.<BR><BR>");

			out.flush();

			return;
			
			
		}
		
//		int gameNumber = game.getGameNumber();
		
//		HttpServletRequest request = ServletActionContext.getRequest().getServletContext().getAttribute("Games")

//		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();
		
//		List<Game> games = (ArrayList<Game>) servletContext.getAttribute("Games");
		
		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();
		
		int gameNumber = game.getGameNumber();
		
		Map<String,Game> currentGamesBeingPlayed1 = (Map<String,Game>)servletContext.getAttribute("currentGamesBeingPlayed");
		
		Game gameTemp = null;

		
		
		
		if (currentGamesBeingPlayed1 == null) {

			log.debug(
					"\n\nDebug: In method raise() and [Application] scope [currentGamesBeingPlayed] does not  exist. \n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"<BR><BR>Debug: In method raise() and [Application] scope [currentGamesBeingPlayed] does not  exist. Please start new game orjoin an existing game.<BR><BR>");

			out.flush();

			return;
			
			
		}
		
		
		Game gameApplicationScope_ = currentGamesBeingPlayed1.get("" +gameNumber );
		
		
		//Game appSoceGame = currentGamesBeingPlayed1.get("" + game.getGameNumber());
		
		if(gameApplicationScope_ == null) {
			
			
			log.debug(
					"\n\nDebug: In method raise() and [Application] scope [currentGamesBeingPlayed] does not contain session  scope game number. \n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"<BR><BR>Debug: In method raise() and [Application] scope [currentGamesBeingPlayed] does not contain session  scope game number. Please start new game orjoin an existing game.<BR><BR>");

			out.flush();

			return;
			
		}

		
		
		String betAmount = request.getParameter("betAmount");
		
		double betAmountDouble = 0;
		
		try {
		
			if(betAmount == null || betAmount.equals("") || Double.parseDouble(betAmount) <= 0 || Double.parseDouble(betAmount) > 1) {
				
			}
			else {
				betAmountDouble = Double.parseDouble(betAmount);
			}
			
		}
		
		catch(Exception e2) {
			e2.printStackTrace();

			log.debug(
					"\n\nDebug: In method raise() processing [request] scope parameter 'betAmount'.  Try raising again.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"<BR><BR>Debug: In method raise() processing [request] scope parameter 'betAmount'.  Try raising again.<BR><BR>");

			out.flush();

			return;
		
		}
		
		

		//betAmountDouble
		
		

		if (game.getPlayer1().getUserName().equals(user.getUserName())  ) {
			
			
			gameApplicationScope_.player1makeBet(betAmountDouble);
			
			
			
			
			servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
			
			httpSession.setAttribute("Game", game);

			
			log.debug(
					"\n\nDebug: In method raise() Player 1 has bet and now it is player 2's turn.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Hand: " + game.getPlayers1HandsForDisplay(user));
			out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Bets: " + game.getPlayer1BetAmountsForDisplay(user));

			out.println("<BR>");
			
			out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Hand: " + game.getPlayers2HandsForDisplay(user));
			out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Bets: " + game.getPlayer2BetAmountsForDisplay(user));
			out.println("<BR>");
//			out.println("<BR>It is now Player 2 " + game.getPlayer2().getUserName() + " Turn!");
			
			String whoseTurnIsIt = game.whoseTurnIsIt();
			
			if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER1)) {
				out.println("<BR>It is now Player 1 " + game.getPlayer1().getUserName() + " Turn!");
				
			}
			else if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER2)) {
				out.println("<BR>It is now Player 2 " + game.getPlayer1().getUserName() + " Turn!");
				
			}
			else if(whoseTurnIsIt.equals(game.FINAL_CARD_BET)) {
				out.println("<BR>It is now Final Card Bet " + game.getPlayer1().getUserName() + " Turn!");
				
			}
			else if(whoseTurnIsIt.equals(game.GAME_COMPLETE)) {
				out.println("<BR>The game is now coplete. ");
				
			}
			else if(whoseTurnIsIt.equals(game.UNKNOWN_STATE)) {
				out.println("<BR>The game is in amn unknown state. ");
				
			}
			

			
			out.flush();
			
			log.debug(
					"\n\nDebug: Exiting method raise()\n\n");


			return;
			
		}

		
		
		
//		Map<String,Game> currentGamesBeingPlayed = new HashMap<String,Game>();
		
//		Map<String,Game> currentGamesBeingPlayed1 = (Map<String,Game>)servletContext.getAttribute("currentGamesBeingPlayed");
//		
//		Game gameTemp = null;
		
		gameApplicationScope_.player2makeBet(betAmountDouble);

		
		DeckOfCards deckOfCards = gameApplicationScope_.getDeckOfCards();
		
		
		PlayingCard player1card = deckOfCards.dealCard();
		PlayingCard player2card = deckOfCards.dealCard();
		
		game.dealNextTwoCards(player1card, player2card);



		
		currentGamesBeingPlayed1.put("" + game.getGameNumber(), gameApplicationScope_);

		
		servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
		
		httpSession.setAttribute("Game", currentGamesBeingPlayed1);
		
		

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Hand: " + game.getPlayers1HandsForDisplay(user));
		out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Bets: " + game.getPlayer1BetAmountsForDisplay(user));

		out.println("<BR>");
		
		out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Hand: " + game.getPlayers2HandsForDisplay(user));
		out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Bets: " + game.getPlayer2BetAmountsForDisplay(user));
		out.println("<BR>");
//		out.println("<BR>It is now Player 1 " + game.getPlayer1().getUserName() + " Turn!");
		
		String whoseTurnIsIt = game.whoseTurnIsIt();
		
		if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER1)) {
			out.println("<BR>It is now Player 1 " + game.getPlayer1().getUserName() + " Turn!");
			
		}
		else if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER2)) {
			out.println("<BR>It is now Player 2 " + game.getPlayer1().getUserName() + " Turn!");
			
		}
		else if(whoseTurnIsIt.equals(game.FINAL_CARD_BET)) {
			out.println("<BR>It is now Final Card Bet " + game.getPlayer1().getUserName() + " Turn!");
			
		}
		else if(whoseTurnIsIt.equals(game.GAME_COMPLETE)) {
			out.println("<BR>The game is now coplete. ");
			
		}
		else if(whoseTurnIsIt.equals(game.UNKNOWN_STATE)) {
			out.println("<BR>The game is in amn unknown state. ");
			
		}
		
		
		
		out.flush();
		
		log.debug(
				"\n\nDebug: Exiting method raise()\n\n");


		
		servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed1);
		
		httpSession.setAttribute("Game", game);

		

		return;
		
	}

	public void joinGameNow() {

		log.debug("\n\nDebug: In method joinGameNow()\n\n");

		HttpServletRequest request = ServletActionContext.getRequest();
		
		String gamePlayer1UserName = request.getParameter("gamePlayer1UserName");
		
		if(gamePlayer1UserName == null || gamePlayer1UserName.trim().equals("")) {

			log.debug(
					"\n\nDebug: In method joinGameNow() and requsst scope parameter: gamePlayer1UserName == null .\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(
					
					"\n\nDebug: In method joinGameNow() and requsst scope parameter: gamePlayer1UserName == null .\n\n");

			out.flush();

			return;

			
		}


		HttpSession httpSession = ServletActionContext.getRequest().getSession();

		User user = (User) httpSession.getAttribute("user");

		if (user == null) {

			log.debug(
					"\n\nDebug: In method joinGameNow() and user does not exist in [session] scope and exiting method.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println("There is a problem with your logon.");
			out.flush();

			return;

		}

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		List<Game> games = (ArrayList<Game>) servletContext.getAttribute("Games");
		


		if (games == null) {

			log.debug(
					"\n\nDebug: In method joinGameNow() and [Games] does not exist in application scope and exiting method.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println("No curent existing Games available to Join.");
			out.flush();

			return;

			// ArrayList<User> users = new ArrayList<User>();

			// List<Game> games = new ArrayList<Game>();
			//
			// games.add(new Product("p1", "Name 1", 1000));
			// games.add(new Product("p2", "Name 2", 2000));
			// games.add(new Product("p3", "Name 3", 3000));
			// jsonData.put("lu", users);

			// log.debug("\n\nDebug: Exiting method joinGame() and returning
			// success but no glabal Games exists.\n\n");

			// return;

		} else {

			log.debug("\n\nDebug: in method joinGameNow() and Glabal [Games] exists.\n\n");

			ArrayList<User> users = new ArrayList<User>();

			Iterator iterGames = games.iterator();

			StringBuffer stringBuffer = new StringBuffer();

			int x = 0;
			

			while (iterGames.hasNext()) {

				Game game = (Game) iterGames.next();

				User user1 = game.getPlayer1();

				users.add(user1);

				User user2 = game.getPlayer2();

				++x;
				
				if (user1 == null && user2 == null) {

					// Incorrect game state. User1 (player1) must exist.
					
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/plain;charset=utf-8");
					PrintWriter out;
					try {
						out = response.getWriter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					
					
					out.println("<BR>Incorrect game state. User1 (player1) must exist.");

					
				}
				else if (user1 == null && user2 != null) {
					
					// Incorrect game state. User1 (player1) must exist.
					
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/plain;charset=utf-8");
					PrintWriter out;
					try {
						out = response.getWriter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					
					
					out.println("<BR>Incorrect game state. User1 (player1) must exist.");


				}

				else if ((user1 != null && user2 == null || user1 != null && user2 != null) ) {
					
					//&& game.getDeckOfCards() == null) {
					
					int gameNumber = game.getGameNumber();
					
					/* HERE #1 */
					
					Map<String,Game> currentGamesBeingPlayed1 = (Map<String,Game>)servletContext.getAttribute("currentGamesBeingPlayed");
					
					Game gameTemp = null;
					
					if(currentGamesBeingPlayed1 != null) {
						
					
						gameTemp = currentGamesBeingPlayed1.get("" + game.getGameNumber());
					
					}
					
					if(gameTemp != null) {

						
						log.debug(
								"\n\nDebug: In method joinGameNow() and Game already exists in hash map global veriable. Therfore resumeing game.\n\n");

						HttpServletResponse response = ServletActionContext.getResponse();
						response.setContentType("text/plain;charset=utf-8");
						PrintWriter out;
						try {
							out = response.getWriter();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}
						
						
//						out.println("<BR>Player 1 " + gameTemp.getPlayer1().getUserName() + " Hand: " + gameTemp.getPlayers1HandsForDisplay(user));
//						out.println("<BR>Player 1 " + gameTemp.getPlayer1().getUserName() + " Bets: " + gameTemp.getPlayer1BetAmountsForDisplay(user));
//						
//						out.print("<BR>");
//						out.println("<BR>Player 2 " + gameTemp.getPlayer2().getUserName() + " Hand: " + gameTemp.getPlayers2HandsForDisplay(user));
//						out.println("<BR>Player 2 " + gameTemp.getPlayer2().getUserName() + " Bets: " + gameTemp.getPlayer2BetAmountsForDisplay(user));
//						out.println("<BR>");
						
						out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Hand: " + game.getPlayers1HandsForDisplay(user));
						out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Bets: " + game.getPlayer1BetAmountsForDisplay(user));

						out.println("<BR>");
						
						out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Hand: " + game.getPlayers2HandsForDisplay(user));
						out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Bets: " + game.getPlayer2BetAmountsForDisplay(user));
						out.println("<BR>");
//						out.println("<BR>It is now Player 1 " + game.getPlayer1().getUserName() + " Turn!");
						
						String whoseTurnIsIt = game.whoseTurnIsIt();
						
						if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER1)) {
							out.println("<BR>It is now Player 1 " + game.getPlayer1().getUserName() + " Turn!");
							
						}
						else if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER2)) {
							out.println("<BR>It is now Player 2 " + game.getPlayer1().getUserName() + " Turn!");
							
						}
						else if(whoseTurnIsIt.equals(game.FINAL_CARD_BET)) {
							out.println("<BR>It is now Final Card Bet " + game.getPlayer1().getUserName() + " Turn!");
							
						}
						else if(whoseTurnIsIt.equals(game.GAME_COMPLETE)) {
							out.println("<BR>The game is now coplete. ");
							
						}
						else if(whoseTurnIsIt.equals(game.UNKNOWN_STATE)) {
							out.println("<BR>The game is in amn unknown state. ");
							
						}
						
						
						
						out.flush();
						
						log.debug(
								"\n\nDebug: Exiting method joinGameNow()\n\n");


						return;

						
					}
					
//					if ((user2 == null || user1 != null && user2 != null && (user.getUserName().equals(user1) || user.getUserName().equals(user2.getUserName()))) ) {
					
					if (game.getPlayer2() == null  ) {
						
						game.setPlayer2(user);
						
					}

					
					
					DeckOfCards deckOfCards = RandomContainerEnum.INSTANCE.continuousDeck.getDeck();
					
					game.setDeckOfCards(deckOfCards);
					
					deckOfCards.shuffleDeckCards();
                                        
                                        RandomContainerEnum.INSTANCE.continuousDeck.setDeck(deckOfCards);
					
					Map<String,Game> currentGamesBeingPlayed2 = new HashMap<String,Game>();
					
					

					
					PlayingCard player1card1 = deckOfCards.dealCard();
					PlayingCard player2card1 = deckOfCards.dealCard();
					
					game.dealNextTwoCards(player1card1, player2card1);


					PlayingCard player1card2 = deckOfCards.dealCard();
					PlayingCard player2card2 = deckOfCards.dealCard();
					
					game.dealNextTwoCards(player1card2, player1card2);

					
					currentGamesBeingPlayed2.put("" + game.getGameNumber(), game);

					
					servletContext.setAttribute("currentGamesBeingPlayed", currentGamesBeingPlayed2);
					
					httpSession.setAttribute("Game", game);
					
					
					log.debug(
							"\n\nDebug: In method joinGameNow() and starting new Game.\n\n");

					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/plain;charset=utf-8");
					PrintWriter out;
					try {
						out = response.getWriter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					
					
					out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Hand: " + game.getPlayers1HandsForDisplay(user));
					out.println("<BR>Player 1 " + game.getPlayer1().getUserName() + " Bets: " + game.getPlayer1BetAmountsForDisplay(user));

					out.println("<BR>");
					
					out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Hand: " + game.getPlayers2HandsForDisplay(user));
					out.println("<BR>Player 2 " + game.getPlayer2().getUserName() + " Bets: " + game.getPlayer2BetAmountsForDisplay(user));
					out.println("<BR>");
					String whoseTurnIsIt = game.whoseTurnIsIt();
					
					if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER1)) {
						out.println("<BR>It is now Player 1 " + game.getPlayer1().getUserName() + " Turn!");
						
					}
					else if(whoseTurnIsIt.equals(game.CURRENT_TURN_PLAYER2)) {
						out.println("<BR>It is now Player 2 " + game.getPlayer1().getUserName() + " Turn!");
						
					}
					else if(whoseTurnIsIt.equals(game.FINAL_CARD_BET)) {
						out.println("<BR>It is now Final Card Bet " + game.getPlayer1().getUserName() + " Turn!");
						
					}
					else if(whoseTurnIsIt.equals(game.GAME_COMPLETE)) {
						out.println("<BR>The game is now coplete. ");
						
					}
					else if(whoseTurnIsIt.equals(game.UNKNOWN_STATE)) {
						out.println("<BR>The game is in amn unknown state. ");
						
					}
					
					
					out.flush();
					
					log.debug(
							"\n\nDebug: Exiting method joinGameNow()\n\n");


					return;
					

				}
//				} else if (user.getUserName().equals(user2.getUserName())) {
//
//					stringBuffer.append("<BR>Join already started game with player 1 as: " + user1.getUserName()
//							+ "and player 2 as: (" + user2.getUserName()
//							+ ") <input type=\"button\"  onclick=\"joinGameNow('" + user1.getUserName()
//							+ "' )\"  id =\"joinGameNow" + x
//							+ "1\" value = \"Join Existing Game Now\" name = \"joinGameNow" + x + "1\">");
//
//				
//				} 
//				else if (user.getUserName().equals(user1.getUserName())) {
//
//				stringBuffer.append("<BR>Join already started game with player 1 as you: " + user1.getUserName()
//						+ "and player 2 as : (" + user2.getUserName()
//						+ ") <input type=\"button\"  onclick=\"joinGameNow('" + user1.getUserName()
//						+ "' )\"  id =\"joinGameNow" + x
//						+ "1\" value = \"Join Existing Game Now\" name = \"joinGameNow" + x + "1\">");
//
//				}

			}

		}
	}

	public void joinGame2() {

		log.debug("\n\nDebug: In method joinGame2()\n\n");

		HttpSession httpSession = ServletActionContext.getRequest().getSession();

		User user = (User) httpSession.getAttribute("user");

		if (user == null) {

			log.debug(
					"\n\nDebug: In method joinGame2() and user does not exist in [session] scope and exiting method.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println("There is a problem with your logon.");
			out.flush();

			return;

		}

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		List<Game> games = (ArrayList<Game>) servletContext.getAttribute("Games");

		if (games == null) {

			log.debug(
					"\n\nDebug: In method joinGame2() and [Games] does not exist in application scope and exiting method.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println("No curent existing Games available to Join.");
			out.flush();

			return;

			// ArrayList<User> users = new ArrayList<User>();

			// List<Game> games = new ArrayList<Game>();
			//
			// games.add(new Product("p1", "Name 1", 1000));
			// games.add(new Product("p2", "Name 2", 2000));
			// games.add(new Product("p3", "Name 3", 3000));
			// jsonData.put("lu", users);

			// log.debug("\n\nDebug: Exiting method joinGame() and returning
			// success but no glabal Games exists.\n\n");

			// return;

		} else {

			log.debug("\n\nDebug: in method joinGame() and Glabal [Games] exists.\n\n");

			ArrayList<User> users = new ArrayList<User>();

			Iterator iterGames = games.iterator();

			StringBuffer stringBuffer = new StringBuffer();

			int x = 0;

			while (iterGames.hasNext()) {

				Game game = (Game) iterGames.next();

				User user1 = game.getPlayer1();

				users.add(user1);

				User user2 = game.getPlayer2();

				++x;

				if (user1 == null && user2 == null) {

					// Incorrect game state. User1 (player1) must exist.
					
				}
				else if (user1 == null && user2 != null) {
					
					// Incorrect game state. User1 (player1) must exist.

				}

				else if (user1 != null && user2 == null) {

					stringBuffer.append("Join New Game with player 1 as: " + user1.getUserName()
							+ "and no player 2 yet. <input type=\"button\" onclick=\"joinGameNow('"
							+ user1.getUserName() + "' )\" id =\"joinGameNow" + x
							+ "1\" value = \"Join New Game Now\" name = \"joinGameNow" + x + "1\">");

				} else if (user.getUserName().equals(user2.getUserName())) {

					stringBuffer.append("<BR>Join already started game with player 1 as: " + user1.getUserName()
							+ "and player 2 as: (" + user2.getUserName()
							+ ") <input type=\"button\"  onclick=\"joinGameNow('" + user1.getUserName()
							+ "' )\"  id =\"joinGameNow" + x
							+ "1\" value = \"Join Existing Game Now\" name = \"joinGameNow" + x + "1\">");

				
				} 
				else if (user.getUserName().equals(user1.getUserName())) {

				stringBuffer.append("<BR>Join already started game with player 1 as you: " + user1.getUserName()
						+ "and player 2 as : (" + user2.getUserName()
						+ ") <input type=\"button\"  onclick=\"joinGameNow('" + user1.getUserName()
						+ "' )\"  id =\"joinGameNow" + x
						+ "1\" value = \"Join Existing Game Now\" name = \"joinGameNow" + x + "1\">");

				}

			}

			// games = new ArrayList<Game>();

			// List<Game> games = new ArrayList<Game>();

			// games.add(new Product("p1", "Name 1", 1000));
			// games.add(new Product("p2", "Name 2", 2000));
			// games.add(new Product("p3", "Name 3", 3000));

			// jsonData.put("lu", users);

			log.debug("\n\nDebug: Exiting method joinGame2() and returning success.\n\n");

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			out.println(stringBuffer);
			// out.println("Join Player 1 (" + user.g + "). Waiting for another
			// player to join your game.");
			out.flush();

			return;

		}
	}
	// getPlayerTurn

	public String joinGame_DeleteMe() {

		log.debug("\n\nDebug: In method joinGame()\n\n");

		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		List<Game> games = (ArrayList<Game>) servletContext.getAttribute("Games");

		if (games == null) {

			ArrayList<User> users = new ArrayList<User>();

			// List<Game> games = new ArrayList<Game>();
			//
			// games.add(new Product("p1", "Name 1", 1000));
			// games.add(new Product("p2", "Name 2", 2000));
			// games.add(new Product("p3", "Name 3", 3000));
			jsonData.put("lu", users);

			log.debug("\n\nDebug: Exiting method joinGame() and returning success but no glabal Games exists.\n\n");

			return SUCCESS;

		} else {

			log.debug("\n\nDebug: in method joinGame() and Glabal Games exists.\n\n");

			ArrayList<User> users = new ArrayList<User>();

			Iterator iterGames = games.iterator();

			while (iterGames.hasNext()) {

				Game game = (Game) iterGames.next();

				User user = game.getPlayer1();

				users.add(user);
			}

			// games = new ArrayList<Game>();

			// List<Game> games = new ArrayList<Game>();

			// games.add(new Product("p1", "Name 1", 1000));
			// games.add(new Product("p2", "Name 2", 2000));
			// games.add(new Product("p3", "Name 3", 3000));

			jsonData.put("lu", users);

			log.debug("\n\nDebug: Exiting method joinGame() and returning success.\n\n");

			return SUCCESS;

		}

	}

	public void newGame() {
		try {

			log.debug("\n\nDebug: In method newGame().\n\n");

			HttpSession httpSession = ServletActionContext.getRequest().getSession();

			User user = (User) httpSession.getAttribute("user");

			if (user == null) {

				log.debug(
						"\n\nDebug: In method newGame() and user does not exist in session scope and exiting method.\n\n");

				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("There is a problem with your logon.");
				out.flush();

				return;

			} else {

				log.debug("\n\nDebug: In method newGame() and user exists in session scope.\n\n");

				ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

				List<Game> games = (ArrayList<Game>) servletContext.getAttribute("Games");

				if (games == null) {

					games = new ArrayList<Game>();

				}

				Game game = new Game();

				game.setPlayer1(user);

				games.add(game);

				servletContext.setAttribute("Games", games);

				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(
						"Hello Player 1 (" + game.getPlayer1().getUserName() + ").  Waiting for another player to join your game.");
				out.flush();

				return;

			}

			// HttpServletResponse response =
			// ServletActionContext.getResponse();
			// response.setContentType("text/plain;charset=utf-8");
			// PrintWriter out = response.getWriter();
			// out.println("Hello Player 1. Waiting for another player to join
			// your game.");
			// out.flush();
		} catch (Exception e) {
		}
	}

	public void hello() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("Hello Ajax");
			out.flush();
		} catch (Exception e) {
		}
	}

	public void sum() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(this.a + this.b);
			out.flush();
		} catch (Exception e) {
		}
	}

	public String find() {
		jsonData.put("sp", new Product("p1", "Name 1", 1000));
		return SUCCESS;
	}

	public String findAll() {
		List<Product> listProduct = new ArrayList<Product>();
		listProduct.add(new Product("p1", "Name 1", 1000));
		listProduct.add(new Product("p2", "Name 2", 2000));
		listProduct.add(new Product("p3", "Name 3", 3000));
		jsonData.put("lp", listProduct);
		return SUCCESS;
	}

}