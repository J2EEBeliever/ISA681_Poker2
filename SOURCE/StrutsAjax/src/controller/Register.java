/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;
import entities.MySQLConnection;
import entities.User;
import entities.dataconnection;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author bwoltemate
 */
public class Register extends ActionSupport {
    
    private String username;
    private String password;
    private String password2;
    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Register.class);
            
    
    @Override
    public String execute() throws Exception {
		
		log.debug("\n\nDebug: In Method register()\n\n");
		
		//String originalPassword;

		HttpServletRequest request = ServletActionContext.getRequest();

		String userName_ = username;
		
		String password1_ = password;

		String password2_ = password2;
                
                 
		dataconnection connection = new MySQLConnection();
		if(password1_ != null && password2_ != null && password1_.length() >= 8 && userName_ != null && userName_.length() >= 3 && !password1_.equals(password2_)) {

 			String errors = "Passwords do not match.";
			 
			 request.setAttribute("errors", errors);
			
			 log.debug("\n\nDebug: Exiting Method register() reutrning 'registrationError'\n\n");
			
			addActionError(errors);
                        return ERROR;

			
		}
		else if(password1_ != null && password2_ != null && password1_.length() >= 8 && userName_ != null && userName_.length() >= 3 && password1_.equals(password2_)) {
			
			String generatedSecuredPasswordHash = BCrypt.hashpw(password1_, BCrypt.gensalt());

		
                                
			User user = connection.getUserByUserName(userName_);
			
			if(user != null) {
				
				String errors = "User already exists";
				 
				 request.setAttribute("errors", errors);
				
				 log.debug("\n\nDebug: Exiting Method register() reutrning 'logonError'\n\n");
				
				addActionError(errors);
                                return ERROR;
				
			}
			
			//this.getUserByUserName(userName);
		 
			if(!connection.registerUser(userName_.toLowerCase(Locale.ENGLISH), generatedSecuredPasswordHash))
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
         this.username = Username;
     }
     
     public String getUsername()
     {
         return username;
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
