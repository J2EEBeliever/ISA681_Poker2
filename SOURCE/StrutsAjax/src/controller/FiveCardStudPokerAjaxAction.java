package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


import cipher.CipherUtils;
import entities.*;

import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

public class FiveCardStudPokerAjaxAction extends ActionSupport {
	
	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger log = Logger.getLogger(FiveCardStudPokerAjaxAction.class);
				

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String userName = "";
	
	private String password = "";

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
	
	public String logon() {
		
		//this.h
		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		
//		String password = "password1";
//
//		log.debug("Debug: password = " + password);
//		
//		String encryptedPassword = CipherUtils.encrypt(password);
//		
//		log.debug("Debug: encryptedPassword = " + encryptedPassword);
//		
//		String unEncryptedPassword = CipherUtils.decrypt(encryptedPassword);
//		
//		log.debug("Debug: unEncryptedPassword = " + unEncryptedPassword);
		
        log.debug("\n\nDebug: In Method Logon()\n\n");

        
        
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String  originalPassword = request.getParameter("password"); //"password1";
		
		//String  originalPassword = "password1";
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        log.debug(generatedSecuredPasswordHash);
         
        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        log.debug(matched);

		
		
		return "success";
	}
	
	public void getPlayerTurn() {
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<BR><BR>Player 1: King Hearts, Jack Spaces, 10 Diamonds");
			out.println("<BR><BR>Player 2: Queen Clubs, 2 Diamonds, 2 Clubs");
			out.println("<BR><BR>");
			out.println("Player Status:  Player 1's bid");
			out.flush();
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