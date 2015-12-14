package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AjaxAction.class);

	private int a;
	private int b;
	private Map<String, Object> jsonData = new HashMap<>();

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

		/*
		 * Pre-conditions:
		 * 
		 * User object is in session scope. userGameId is in session scope. Game
		 * is in hash map in global scope. Game has been already started.
		 * 
		 * Post-conditions:
		 * 
		 * Each user has each players cards redisplayed and next players turn is
		 * initiated.
		 * 
		 */

		User user = Game.getUserObjectFromSessionScope();

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

		String userGameId = Game.getGameIdFromSessionScope();

		if (userGameId == null) {

			log.debug(
					"\n\nDebug: In method waitForTurnOrGameToStart() and [userGameId] does not exist session scope.\n\n");

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
					"<BR>Debug: In method waitForTurnOrGameToStart() and [userGameId] does not exist session scope.<BR>");
			out.flush();

			return;

		}

		Game game = Game.getGameFromApplicationScopeGameHashMap(userGameId);

		if (game == null) {

			log.debug(
					"\n\nDebug: In method waitForTurnOrGameToStart() and [Games] does not exist in hash map in application scope. Exiting method.\n\n");

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
					"Debug: In method waitForTurnOrGameToStart() and [Games] does not exist in hash map in application scope. Exiting method");
			out.flush();

			return;
		}

		boolean success = game.outputToResponseOutputStream(user);
		
//		if(game.getTotalBetForWinner() > 0) {
//			
//			game.removeGameIdInSessionScope(userGameId);
//		}

		log.debug("\n\nDebug: Exiting method waitForTurnOrGameToStart()\n\n");

		return;

	}

	public void raise() {

		/*
		 * Pre-conditions:
		 * 
		 * User object exists in session scope. userGameId exists in session
		 * scope. Game object with userGameId exists in application scope hash
		 * map.
		 * 
		 * Post-conditions:
		 * 
		 * Either player 1 (game.getPlayer1) user has just raised the ante or
		 * player 2 (game.getPlayer2) has just called for the same amount
		 * raised. If player 1 raises then its player 2's turn. If player 2
		 * calls then two new cards are delt and it player 1's turn again to
		 * raise or fold.
		 * 
		 */

		log.debug("\n\nDebug: In method raise()\n\n");

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession httpSession = request.getSession();

		User user = Game.getUserObjectFromSessionScope();

		if (user == null) {

			log.debug("\n\nDebug: In method raise() and Session scope hss null for user object.  Please logon.\n\n");

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

		String userGameId = Game.getGameIdFromSessionScope();

		if (userGameId == null) {

			log.debug("\n\nDebug: In method raise() and userGameId is not in session scope. \n\n");

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

					"<BR><BR>Debug: In method raise() and userGameId is not in session scope.<BR><BR>");

			out.flush();

			return;

		}

		Game game = Game.getGameFromApplicationScopeGameHashMap(userGameId);

		if (game == null) {

			log.debug("\n\nDebug: In method raise() and game does not exist in hash map in application scope. \n\n");

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

					"<BR><BR>Debug: In method raise() and game does not exist in hash map in application scope.<BR><BR>");

			out.flush();

			return;

		}

		double betAmountDouble = 0;

		String betAmount = null;

		String actionType = request.getParameter("actionType");

		if (actionType != null && actionType.equals("fold")
				&& game.getPlayer1().getUsername().equals(user.getUsername())) {

//			game.setTotalBetForWinner(0.01);
			
			game.setDidPlayer1Fold("yes");

			Game.addGameToApplicationScopeGameHashMap(game);

			boolean success = game.outputToResponseOutputStream(user);

			log.debug("\n\nDebug: Exiting method raise() after executing 'hold' logic.\n\n");

			return;

		} else if (actionType != null && actionType.equals("fold")
				&& game.getPlayer2().getUsername().equals(user.getUsername())) {

//			game.setTotalBetForWinner(0.01);
			
			game.setDidPlayer2Fold("yes");

			Game.addGameToApplicationScopeGameHashMap(game);

			boolean success = game.outputToResponseOutputStream(user);

			log.debug("\n\nDebug: Exiting method raise() after executing 'hold' logic.\n\n");

			return;

		} else {

			if (actionType != null && actionType.equals("call")) {

				betAmount = "" + game.getPlayersLastBet();

			} else {

				betAmount = request.getParameter("betAmount");

			}

			try {

				if (betAmount == null || betAmount.equals("") || Double.parseDouble(betAmount) <= 0.00
						|| Double.parseDouble(betAmount) > 1) {

				} else {
					betAmountDouble = Double.parseDouble(betAmount);
				}

			}

			catch (Exception e2) {
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

		}

		if (game.getPlayer1().getUsername().equals(user.getUsername())) {

			game.player1makeBet(betAmountDouble);

			boolean success = game.outputToResponseOutputStream(user);

			log.debug("\n\nDebug: In method raise() Player 1 has bet and now it is player 2's turn.\n\n");

			log.debug("\n\nDebug: Exiting method raise()\n\n");

			return;

		} else if (game.getPlayer2().getUsername().equals(user.getUsername())) {

			game.player2makeBet(betAmountDouble);

			DeckOfCards deckOfCards = game.getDeckOfCards();

			PlayingCard player1card = deckOfCards.dealCard();
			PlayingCard player2card = deckOfCards.dealCard();

			game.dealNextTwoCards(player1card, player2card);

			Game.addGameToApplicationScopeGameHashMap(game);

			boolean success = game.outputToResponseOutputStream(user);

			log.debug("\n\nDebug: Exiting method raise()\n\n");

			return;

		} else {

			log.debug(
					"\n\nDebug: In method raise() processing [request] and current user is neither  game.getPlayer1() or game.getPlayer2().\n\n");

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

					"<BR><BR>Debug: In method raise() processing [request] and current user is neither  game.getPlayer1() or game.getPlayer2().<BR><BR>");

			out.flush();

			return;

		}

	}

	public void joinGameNow() {

		/*
		 * Pre-conditions:
		 * 
		 * The user is logged on and a user objecct is in session scope. The
		 * user just clicked on a "joinGameNow" action button.
		 * 
		 * Post-conditions:
		 * 
		 * The user has a gameId in session scope and the game object in a hash
		 * map in application scope has player2 equal to the user object.
		 * 
		 * 
		 * 
		 * 
		 */

		log.debug("\n\nDebug: In method joinGameNow()\n\n");

		HttpServletRequest request = ServletActionContext.getRequest();

		String gameNumber = request.getParameter("gameNumber");

		if (gameNumber == null || gameNumber.trim().equals("")) {

			log.debug("\n\nDebug: In method joinGameNow() and requsst scope parameter: gameNumber == null .\n\n");

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

					"\n\nDebug: In method joinGameNow() and requsst scope parameter: gameNumber == null .\n\n");

			out.flush();

			return;

		}

		User user = Game.getUserObjectFromSessionScope();

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

		Game game = Game.getGameFromApplicationScopeGameHashMap(gameNumber);

		if (game == null) {

			log.debug(
					"\n\nDebug: In method joinGameNow() and [Game] object does not exist in hash map in application scope. Exiting method.\n\n");

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
					"Debug: From method joinGameNow(). [Game] object does not exist in hash map in application scope.");
			out.flush();

			return;

		} else {

			User user1 = game.getPlayer1();

			User user2 = game.getPlayer2();

			if (user1 == null && user2 == null) {

				log.debug(
						"\n\nDebug: In method joinGameNow(). [Game] object getPlayer1 == null and getPlayer2 == null.\n\n");

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

				out.println(
						"<BR>Debug: In method joinGameNow(). [Game] object getPlayer1 == null and getPlayer2 == null.");

			} else if (user1 == null && user2 != null) {

				// Incorrect game state. User1 (player1) must exist.

				log.debug("\n\nDebug: In method joinGameNow(). [Game] object getPlayer1 == null.\n\n");

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

				out.println("<BR>Debug: In method joinGameNow(). [Game] object getPlayer1 == null");

			}

			else if ((user1 != null && user2 == null || user1 != null && user2 != null)) {

				if (game.getPlayer2() == null && game.getPlayer1() != null && !(game.getPlayer1().equals(user))) {

					game.setPlayer2(user);

					game.setPlayer2UserName(user.getUsername());

				}

				if (game != null && game.getDeckOfCards() != null) {

					Game.setGameIdInSessionScope("" + game.getGameNumber());

					boolean success = game.outputToResponseOutputStream(user);

					log.debug(
							"\n\nDebug: In method joinGameNow() and Game already exists in hash map global veriable. Therfore resumeing game.\n\n");

					log.debug("\n\nDebug: Exiting method joinGameNow()\n\n");

					return;

				}

				Game.setGameIdInSessionScope("" + game.getGameNumber()); // Added
																			// this
																			// statement
																			// 2015/12/06
																			// 4:30
																			// PM
																			// LWF

				DeckOfCards deckOfCards = RandomContainerEnum.INSTANCE.continuousDeck.getDeck();

				game.setDeckOfCards(deckOfCards);

				deckOfCards.shuffleDeckCards();

				RandomContainerEnum.INSTANCE.continuousDeck.setDeck(deckOfCards);

				PlayingCard player1card1 = deckOfCards.dealCard();
				PlayingCard player2card1 = deckOfCards.dealCard();

				game.dealNextTwoCards(player1card1, player2card1);

				PlayingCard player1card2 = deckOfCards.dealCard();
				PlayingCard player2card2 = deckOfCards.dealCard();

				game.dealNextTwoCards(player1card2, player2card2);

				Game.addGameToApplicationScopeGameHashMap(game);

				log.debug("\n\nDebug: In method joinGameNow() and starting new Game.\n\n");
				
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

				out.print("<script language=\"javascript\" type=\"text/javascript\">" +
						"isGameOver = 'no';</script>");

				boolean success = game.outputToResponseOutputStream(user);

				log.debug("\n\nDebug: Exiting method joinGameNow()\n\n");

				return;

			}

		}

	}
	
	public void listCompletedGames() {
		
		/*
		 * Pre-conditions:
		 * 
		 * The user is loggon on and a user object is in sesison scope.
		 * 
		 * Post-conditions:
		 * 
		 * A list of games to join with actions buttonss is displayed.
		 * 
		 */

		log.debug("\n\nDebug: In method listCompletedGames()\n\n");



		User user = Game.getUserObjectFromSessionScope();

		if (user == null) {

			log.debug(
					"\n\nDebug: In method listCompletedGames() and user does not exist in [session] scope and exiting method.\n\n");

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
		
		try {
			Game.displayAllGamesOrderByGameId(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.flush();

		return;


		
		
		

		
	}
		
		
	

	public void joinGame2() {

		/*
		 * Pre-conditions:
		 * 
		 * The user is loggon on and a user object is in sesison scope.
		 * 
		 * Post-conditions:
		 * 
		 * A list of games to join with actions buttonss is displayed.
		 * 
		 */

		log.debug("\n\nDebug: In method joinGame2()\n\n");

	

		User user = Game.getUserObjectFromSessionScope();

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

		List<Game> games = Game.getAlltGamesFromApplicationScopeGameHashMap();

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

		} else {

			log.debug("\n\nDebug: in method joinGame() and Glabal [Games] exists.\n\n");

			Iterator iterGames = games.iterator();

			StringBuffer stringBuffer = new StringBuffer();
			int x = 0;

			while (iterGames.hasNext()) {

				Game game = (Game) iterGames.next();

				User user1 = game.getPlayer1();

				User user2 = game.getPlayer2();

				++x;

				if (game != null && game.getTotalBetForWinner() > 0) {

					// Incorrect game state. User1 (player1) must exist.

					log.debug(
							"\n\nDebug: in method joinGame2() and game is completed and therefore not included in the games to join listing.\n\n");

				} 
				else if (user1 == null && user2 == null) {

					// Incorrect game state. User1 (player1) must exist.

					log.debug(
							"\n\nDebug: in method joinGame2() and game object from hash map in applicatio scope has no player1 or player2 objects.\n\n");

				} 
				else if (user1 == null) {

					// Incorrect game state. User1 (player1) must exist.
					log.debug(
							"\n\nDebug: in method joinGame2() and game object from hash map in applicatio scope has player1 == null.\n\n");

				}

				else if (user1 != null && user2 == null) {

					stringBuffer.append("<BR>" + "Join New Game #" + game.getGameNumber() + " with player 1 as: "
							+ user1.getUsername()
							+ "and no player 2 yet. <input type=\"button\" onclick=\"joinGameNow('"
							+ game.getGameNumber() + "' )\" id =\"joinGameNow" + x
							+ "1\" value = \"Join New Game Now\" name = \"joinGameNow" + x + "1\">");

				} else if (user2 != null && user.getUsername().equals(user2.getUsername())) {

					stringBuffer.append("<BR>Join already started game with player 1 as: " + user1.getUsername()
							+ "and player 2 as: (" + user2.getUsername()
							+ ") <input type=\"button\"  onclick=\"joinGameNow('" + game.getGameNumber()
							+ "' )\"  id =\"joinGameNow" + x
							+ "1\" value = \"Join Existing Game Now\" name = \"joinGameNow" + x + "1\">");

				} else if (user1 != null && user.getUsername().equals(user1.getUsername())) {

					stringBuffer.append("<BR>Join already started game with player 1 as you: " + user1.getUsername()
							+ "and player 2 as : (" + user2.getUsername()
							+ ") <input type=\"button\"  onclick=\"joinGameNow('" + game.getGameNumber()
							+ "' )\"  id =\"joinGameNow" + x
							+ "1\" value = \"Join Existing Game Now\" name = \"joinGameNow" + x + "1\">");

				}

			}

			log.debug("\n\nDebug: Exiting method joinGame2().\n\n");

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
			out.flush();

			return;

		}
	}

	public void newGame() {

		/*
		 * Pre-conditions:
		 * 
		 * User is logged on and a "user" object is in session scope.
		 * 
		 * Post-conditions:
		 * 
		 * A gameId and user object is in session scope and a game object is in
		 * a hash map in application scope. The game object in a hash map in
		 * application scope has a player1 object equal to the current user
		 * object from session scope.
		 * 
		 * 
		 * 
		 */
		try {

			log.debug("\n\nDebug: In method newGame().\n\n");

			

			User user = Game.getUserObjectFromSessionScope();

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

				Game game = new Game();

				game.setPlayer1(user);

				game.setPlayer1UserName(user.getUsername());

				Game.setGameIdInSessionScope("" + game.getGameNumber());

				Game.addGameToApplicationScopeGameHashMap(game);

				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script language=\"javascript\" type=\"text/javascript\">" +
						"isGameOver = 'no';</script>");

				out.println("Hello Player 1 (" + game.getPlayer1().getUsername()
						+ ").  Waiting for another player to join your game.");
				
				out.flush();

				return;

			}

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
                    log.error(e);
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
                    log.error(e);
		}
	}

	/*
	 * public String find() { jsonData.put("sp", new Product("p1", "Name 1",
	 * 1000)); return SUCCESS; }
	 * 
	 * public String findAll() { List<Product> listProduct = new
	 * ArrayList<Product>(); listProduct.add(new Product("p1", "Name 1", 1000));
	 * listProduct.add(new Product("p2", "Name 2", 2000)); listProduct.add(new
	 * Product("p3", "Name 3", 3000)); jsonData.put("lp", listProduct); return
	 * SUCCESS; }
	 */

}