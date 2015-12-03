package controller;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import deck.cards.Game;
import entities.*;

import java.util.*;

public class AjaxAction extends ActionSupport {
	
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
	
	//getPlayerTurn
	
	public String joinGame() {
		
		log.debug("\n\nDebug: In method joinGame()\n\n");
		
		ServletContext servletContext = ServletActionContext.getRequest().getServletContext();

		
		List<Game> games = (ArrayList<Game>)servletContext.getAttribute("Games");
		
		if(games == null) {
			
			
			ArrayList<User> users = new ArrayList<User>();
			
//			List<Game> games = new ArrayList<Game>();
//			
//			games.add(new Product("p1", "Name 1", 1000));
//			games.add(new Product("p2", "Name 2", 2000));
//			games.add(new Product("p3", "Name 3", 3000));
			jsonData.put("lu", users);
			
			log.debug("\n\nDebug: Exiting method joinGame() and returning success but no glabal Games exists.\n\n");

			
			return SUCCESS;
			
			
			
		}
		else {

			
			log.debug("\n\nDebug: in method joinGame() and Glabal Games exists.\n\n");

			
			ArrayList<User> users = new ArrayList<User>();
				
			
			Iterator iterGames = games.iterator();
			
			while (iterGames.hasNext()) {
				
				Game game = (Game)iterGames.next();
				
				User user = game.getPlayer1();
				
				users.add(user);
			}
				
				
				
				
				
			
				
//				games = new ArrayList<Game>();
				
	//			List<Game> games = new ArrayList<Game>();
				
//				games.add(new Product("p1", "Name 1", 1000));
//				games.add(new Product("p2", "Name 2", 2000));
//				games.add(new Product("p3", "Name 3", 3000));
			
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
			
			if(user ==  null) {
				
				log.debug("\n\nDebug: In method newGame() and user does not exist in session scope and exiting method.\n\n");

				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("There is a problem with your logon.");
				out.flush();
				
				return;
				
			}
			else {
				
				log.debug("\n\nDebug: In method newGame() and user exists in session scope.\n\n");


				ServletContext servletContext = ServletActionContext.getRequest().getServletContext();
				
				List<Game> games = (ArrayList<Game>)servletContext.getAttribute("Games");
				
				if(games == null) {
					
					
					games = new ArrayList<Game>();
					
				}
				
				Game game = new Game();
				
				game.setPlayer1(user);
				
				games.add(game);
				
				
				servletContext.setAttribute("Games", games);
				
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("Hello Player 1 (" + game.getPlayer1() + ").  Waiting for another player to join your game.");
				out.flush();
				
				return;
				
			}


//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setContentType("text/plain;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println("Hello Player 1.  Waiting for another player to join your game.");
//			out.flush();
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