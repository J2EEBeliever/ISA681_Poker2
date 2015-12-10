/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;
import static controller.FiveCardStudPokerAjaxAction.log;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author bwoltemate
 */
public class register extends ActionSupport {
    
    
    public String execute() throws Exception {
		
		log.debug("\n\nDebug: In Method register()\n\n");
		
		//String originalPassword;

		HttpServletRequest request = ServletActionContext.getRequest();

		String userName = request.getParameter("userName"); // "password1";
		
		String password1 = request.getParameter("password1"); // "password1";

		String password2 = request.getParameter("password2"); // "password1";
                
                 FiveCardStudPokerAjaxAction action = new FiveCardStudPokerAjaxAction();
		
		if(password1 != null && password2 != null && password1.length() >= 8 && userName != null && userName.length() >= 3 && !password1.equals(password2)) {

 			String errors = "Passwords do not match.";
			 
			 request.setAttribute("errors", errors);
			
			 log.debug("\n\nDebug: Exiting Method register() reutrning 'registrationError'\n\n");
			
			return "registrationError";

			
		}
		else if(password1 != null && password2 != null && password1.length() >= 8 && userName != null && userName.length() >= 3 && password1.equals(password2)) {
			
			String generatedSecuredPasswordHash = BCrypt.hashpw(password1, BCrypt.gensalt());

		
                                
			User user = action.getUserByUserName(userName);
			
			if(user != null) {
				
				String errors = "Incorrect Username and/or Password";
				 
				 request.setAttribute("errors", errors);
				
				 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");
				
				return "registrationError";

				
			}
			
			//this.getUserByUserName(userName);
			
			Connection conn = action.getDatabaseConnection();
			
			
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
    
}
