/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;
import static controller.FiveCardStudPokerAjaxAction.log;
import entities.MySQLConnection;
import entities.User;
import entities.dataconnection;
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
public class Register extends ActionSupport {
    
    private String Username;
    private String password;
    private String password2;
    
            
    
    @Override
    public String execute() throws Exception {
		
		log.debug("\n\nDebug: In Method register()\n\n");
		
		//String originalPassword;

		HttpServletRequest request = ServletActionContext.getRequest();

		String userName = Username;
		
		String password1 = password;

		String password2 = this.password2;
                
                 FiveCardStudPokerAjaxAction action = new FiveCardStudPokerAjaxAction();
		
		if(password1 != null && password2 != null && password1.length() >= 8 && userName != null && userName.length() >= 3 && !password1.equals(password2)) {

 			String errors = "Passwords do not match.";
			 
			 request.setAttribute("errors", errors);
			
			 log.debug("\n\nDebug: Exiting Method register() reutrning 'registrationError'\n\n");
			
			addActionError(errors);
                        return ERROR;

			
		}
		else if(password1 != null && password2 != null && password1.length() >= 8 && userName != null && userName.length() >= 3 && password1.equals(password2)) {
			
			String generatedSecuredPasswordHash = BCrypt.hashpw(password1, BCrypt.gensalt());

		
                                
			User user = action.getUserByUserName(userName);
			
			if(user != null) {
				
				String errors = "User already exists";
				 
				 request.setAttribute("errors", errors);
				
				 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");
				
				addActionError(errors);
                                return ERROR;
				
			}
			
			//this.getUserByUserName(userName);
		 dataconnection connection = new MySQLConnection();
			if(!connection.registerUser(userName, generatedSecuredPasswordHash))
                        {
			  		String errors = "Incorrect Username and/or Password";
					 request.setAttribute("errors", errors);
				 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");
					return "registrationError";
					
			      }
			      
					 
				  		String errors = "New User Registration Successful.  Please Logon.";
						 
						 request.setAttribute("errors", errors);

						 log.debug("\n\nDebug: Exiting Method register() reutrning 'success'\n\n");

			      
					addActionError(errors);
                                        return SUCCESS;
					
		}

		 //ttpServletResponse  responce = ServletActionContext.getResponse();
		
		String errors = "Incorrect Username and/or Password";
		 
		 request.setAttribute("errors", errors);

		 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");
		 
		
		addActionError(errors);
                return ERROR;
		
	}
      public String getPassword()
     {
         return password;
     }
     
     public void setUsername(String Username)
     {
         this.Username = Username;
     }
     
     public String getUsername()
     {
         return Username;
     }
     
     public void setPassword(String Password)
     {
         this.password = Password;
     }
    
     public void setPassword2(String Password2)
     {
         this.password2 = Password2;
     }
}
