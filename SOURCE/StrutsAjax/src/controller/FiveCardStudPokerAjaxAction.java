	/*
	 * Author: Linus Freeman
	 * 
	 */

package controller;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


import entities.*;



import java.util.*;


import org.mindrot.jbcrypt.BCrypt;

public class FiveCardStudPokerAjaxAction extends ActionSupport implements  LoginRequired{

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

	public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
		// create our mysql database connection
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/fcs";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "fcs_user", "7yXw8dDaNMBNBbW5");

		return conn;

	}
	
	public String register() throws Exception {
		
		log.debug("\n\nDebug: In Method register()\n\n");
		
		//String originalPassword;

		HttpServletRequest request = ServletActionContext.getRequest();

		String userName = request.getParameter("userName"); // "password1";
		
		String password1 = request.getParameter("password1"); // "password1";

		String password2 = request.getParameter("password2"); // "password1";
		
		if(password1 != null && password2 != null && password1.length() >= 8 && userName != null && userName.length() >= 3 && !password1.equals(password2)) {

 			String errors = "Passwords do not match.";
			 
			 request.setAttribute("errors", errors);
			
			 log.debug("\n\nDebug: Exiting Method register() reutrning 'registrationError'\n\n");
			
			return "registrationError";

			
		}
		else if(password1 != null && password2 != null && password1.length() >= 8 && userName != null && userName.length() >= 3 && password1.equals(password2)) {
			
			String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt());

			
			User user = this.getUserByUserName(userName);
			
			if(user != null) {
				
				String errors = "Incorrect Username and/or Password";
				 
				 request.setAttribute("errors", errors);
				
				 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");
				
				return "registrationError";

				
			}
			
			//this.getUserByUserName(userName);
			
			Connection conn = this.getDatabaseConnection();
			
			
			  String query = " insert into users (UserName, Password, Timestamp) values (?, ?, ?)";
			
			/*
			 * -- -- Table structure for table `users` --
			 * 
			 * CREATE TABLE IF NOT EXISTS `users` ( `UserID` int(11) NOT NULL
			 * AUTO_INCREMENT, `UserName` varchar(20) NOT NULL, `Password`
			 * char(60) NOT NULL, `Timestamp` timestamp NOT NULL DEFAULT
			 * CURRENT_TIMESTAMP, PRIMARY KEY (`UserID`), UNIQUE KEY `UserName`
			 * (`UserName`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1
			 * AUTO_INCREMENT=3 ;
			 * 
			 */
			      
			      Calendar calendar = Calendar.getInstance();
			      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			 

					//String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));

			      
			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
			      preparedStmt.setString (1, userName);
			      preparedStmt.setString (2, generatedSecuredPasswordHash);
			      preparedStmt.setDate   (3, startDate);
			 
			      // execute the preparedstatement
			      preparedStmt.execute();
			      
			      try {
			    	  preparedStmt.close();
				      conn.close();
			      }
			      catch(Exception e) {
			    	  
			  		String errors = "Incorrect Username and/or Password";
					 
					 request.setAttribute("errors", errors);

					 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");

					
					return "registrationError";
					
			      }
			      
					 
				  		String errors = "New User Registration Successful.  Please Logon.";
						 
						 request.setAttribute("errors", errors);

						 log.debug("\n\nDebug: Exiting Method register() reutrning 'success'\n\n");

			      
					return "success";
					
		}

		 //ttpServletResponse  responce = ServletActionContext.getResponse();
		
		String errors = "Incorrect Username and/or Password";
		 
		 request.setAttribute("errors", errors);

		 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");
		 
		
		return "registrationError";
		
	}


	
	public User getUserByUserName(String userName) throws Exception {

		log.debug("\n\nDebug: inside getUserByUserName\n");
		
		Connection conn = this.getDatabaseConnection(); 

		
		User user = null;


		try {

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM users where UserName = ? ";

			// create the java statement
			PreparedStatement pstmnt = conn.prepareStatement(query);

			pstmnt.setString(1, userName);
			
			

			// execute the query, and get a java resultset
			ResultSet rs = pstmnt.executeQuery();

			/*
			 * -- -- Table structure for table `users` --
			 * 
			 * CREATE TABLE IF NOT EXISTS `users` ( `UserID` int(11) NOT NULL
			 * AUTO_INCREMENT, `UserName` varchar(20) NOT NULL, `Password`
			 * char(60) NOT NULL, `Timestamp` timestamp NOT NULL DEFAULT
			 * CURRENT_TIMESTAMP, PRIMARY KEY (`UserID`), UNIQUE KEY `UserName`
			 * (`UserName`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1
			 * AUTO_INCREMENT=3 ;
			 * 
			 */


			// iterate through the java resultset
			if (rs != null && rs.next()) {
				int userId1 = rs.getInt("UserId");
				String userName1 = rs.getString("UserName");
				String password1 = rs.getString("Password");

				user = new User(-1,"","",new Timestamp(1));
				
				user.setUserID(userId1);
				user.setUserName(userName1);
				user.setPassword(password1);

				// Date dateCreated = rs.getDate("date_created");
				// boolean isAdmin = rs.getBoolean("is_admin");
				// int numPoints = rs.getInt("num_points");

				// print the results
				// System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName,
				// lastName, dateCreated, isAdmin, numPoints);
			}
			if (rs.next()) { // more than one record
				
				throw new Exception("FiveCardStudPokerAjaxAction::getUser(...) incorrectly returned more than one user from table users");

			}

			rs.close();
			pstmnt.close();
			conn.close();
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}

		log.debug("\n\nDebug: exiting getUserByUserName\n");

		return user;
	}

	
	

	public String logon_DeleteMe() {

		// this.h

		// HttpServletRequest request = ServletActionContext.getRequest();
		//
		// String password = "password1";
		//
		// log.debug("Debug: password = " + password);
		//
		// String encryptedPassword = CipherUtils.encrypt(password);
		//
		// log.debug("Debug: encryptedPassword = " + encryptedPassword);
		//
		// String unEncryptedPassword = CipherUtils.decrypt(encryptedPassword);
		//
		// log.debug("Debug: unEncryptedPassword = " + unEncryptedPassword);

		log.debug("\n\nDebug: In Method Logon()\n\n");

		HttpServletRequest request = ServletActionContext.getRequest();

		String originalPassword = request.getParameter("password"); // "password1";

		// String originalPassword = "password1";
	//
		
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt());
		log.debug(generatedSecuredPasswordHash);

		boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
		log.debug(matched);

		
		log.debug("\n\nDebug: Exiting Method Logon()\n\n");

		
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
/*
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
	
	*/
}
        
        