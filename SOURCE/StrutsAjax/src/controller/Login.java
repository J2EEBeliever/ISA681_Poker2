/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.RandomContainerEnum;
import com.opensymphony.xwork2.ActionSupport;
import java.util.UUID;
import entities.dataconnection;
import entities.MySQLConnection;
import entities.User;
import cipher.CipherUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author bwoltemate
 */

public class Login extends ActionSupport implements ServletResponseAware, SessionAware {
    
    private String userName;
    private String password;
    @SuppressFBWarnings(justification="No bug", value="SE_BAD_FIELD")
    private HttpServletResponse servletResponse;
    private Map<String, Object> session;

     @Override
     public String execute()
  {
      
      dataconnection connection = new MySQLConnection();
      if (userName == null)
      {
          
          return "gameRedirect";
          
          
          
      }
      User inComingUser = connection.getUserData(userName.toLowerCase());
      if (inComingUser.getUserID() == -1)
      {
          addActionError("Invalid user name or password! Please try again!");
          return ERROR;
      }
      String hashPassword = inComingUser.getPassword();
      if(BCrypt.checkpw(password, hashPassword))
      {
          try {
              connection.revokeUserSession(inComingUser.getUserID());
              UUID Token = UUID.randomUUID();
              Cookie PokerToken = new Cookie("PokerToken", Token.toString());
              Long TokenRand = RandomContainerEnum.INSTANCE.randomContainer.getRandom().nextLong();
              connection.createSession(inComingUser.getUserID(), Token.toString(), TokenRand);
              Cookie TokenHMAC = new Cookie("TokenHMAC", CipherUtils.createHMACSHA256(Token.toString(),TokenRand.toString()));
              PokerToken.setMaxAge(RandomContainerEnum.sessionTimeout);
              PokerToken.setHttpOnly(true);
              PokerToken.setSecure(true);
              //PokerToken.setDomain("b1wolt.com");
              //PokerToken.setPath("/ISA681Poker");
              TokenHMAC.setMaxAge(RandomContainerEnum.sessionTimeout);
              TokenHMAC.setHttpOnly(true);
              TokenHMAC.setSecure(true);
              //TokenHMAC.setDomain("b1wolt.com");
              //TokenHMAC.setPath("/ISA681Poker");   
              servletResponse.addCookie(PokerToken);
              servletResponse.addCookie(TokenHMAC);
              this.session.put("UserID", inComingUser.getUserID());
              this.session.put("user", inComingUser);
          } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException ex) {
              Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
              addActionError("There was an error processing your request");
              return ERROR;
          }
      }
      else
      {
          addActionError("Invalid user name or password! Please try again!");
          return ERROR;
      }
       return "gameRedirect";
      //return "mainRedirect";
       
  }
     
     public String getUserID()
     {
         return null;
     }
     
     public String getPassword()
     {
         return null;
     }
     
     public void setUsername(String UserName)
     {
         this.userName = UserName;
     }
     
     public String getUsername()
     {
         return userName;
     }
     
     public void setPassword(String Password)
     {
         this.password = Password;
     }

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    
}
