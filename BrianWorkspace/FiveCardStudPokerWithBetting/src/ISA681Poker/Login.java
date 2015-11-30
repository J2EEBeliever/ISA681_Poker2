/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681Poker;

import ISA681.CardDeck.RandomContainerEnum;
import com.opensymphony.xwork2.ActionSupport;
import java.util.UUID;
import ISA681.data.dataconnection;
import ISA681.data.MySQLConnection;
import ISA681.data.User;
import cipher.CipherUtils;
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
    
    private String UserName;
    private String Password;
    private HttpServletResponse servletResponse;
    private Map<String, Object> session;

     @Override
     public String execute()
  {
      
      dataconnection connection = new MySQLConnection();
      if (UserName == null)
      {
          
          return "mainRedirect";
          
          
          
      }
      User inComingUser = connection.getUserData(UserName);
      if (inComingUser.getUserID() == -1)
      {
          addActionError("Invalid user name or password! Please try again!");
          return ERROR;
      }
      String hashPassword = inComingUser.getPassword();
      if(BCrypt.checkpw(Password, hashPassword))
      {
          try {
              connection.revokeUserSession(inComingUser.getUserID());
              UUID Token = UUID.randomUUID();
              Cookie PokerToken = new Cookie("PokerToken", Token.toString());
              Long TokenRand = RandomContainerEnum.INSTANCE.RandomContainer.getRandom().nextLong();
              connection.CreateSession(inComingUser.getUserID(), Token.toString(), TokenRand);
              Cookie TokenHMAC = new Cookie("TokenHMAC", CipherUtils.createHMACSHA256(Token.toString(),TokenRand.toString()));
              PokerToken.setMaxAge(RandomContainerEnum.sessionTimeout);
              PokerToken.setHttpOnly(true);
              //PokerToken.setSecure(true);
              //PokerToken.setDomain("b1wolt.com");
              //PokerToken.setPath("/ISA681Poker");
              TokenHMAC.setMaxAge(RandomContainerEnum.sessionTimeout);
              TokenHMAC.setHttpOnly(true);
              //TokenHMAC.setSecure(true);
              //TokenHMAC.setDomain("b1wolt.com");
              //TokenHMAC.setPath("/ISA681Poker");   
              servletResponse.addCookie(PokerToken);
              servletResponse.addCookie(TokenHMAC);
              this.session.put("UserID", inComingUser.getUserID());
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
       
      return SUCCESS;
       
  }
     
     public String getUserID()
     {
         return null;
     }
     
     public String getPassword()
     {
         return null;
     }
     
     public void setUserName(String UserName)
     {
         this.UserName = UserName;
     }
     
     public void setPassword(String Password)
     {
         this.Password = Password;
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