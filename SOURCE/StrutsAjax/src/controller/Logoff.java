/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cipher.CipherUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entities.MySQLConnection;
import entities.RandomContainerEnum;
import entities.Session;
import entities.dataconnection;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author b1wolt
 */
public class Logoff extends ActionSupport implements LoginRequired {
     private static final long serialVersionUID = 2015_12_14_012L; 
    
     @Override
     public String execute()
     {
         HttpServletRequest servletRequest = ServletActionContext.getRequest();
          Map<String, Object> Servletsession = ActionContext.getContext().getSession();
          HttpServletResponse servletResponse = ServletActionContext.getResponse();
           Cookie tokenCookie = new Cookie("temp","temp");
        Cookie HMACCookie = new Cookie("temp2","temp");
        dataconnection connection = new MySQLConnection();
        UUID token = null;
        Session userSession;
        if (servletRequest.getCookies() != null)
            for(Cookie c : servletRequest.getCookies()) 
            {
                switch (c.getName()) 
                {
                    case "PokerToken":
                        token = UUID.fromString(c.getValue());
                        tokenCookie = c;
                        break;
                    case "TokenHMAC":
                        HMACCookie = c;
                        break;
                    default:
                        //not an authentication token
                        break;
                }
        }
         //If the get cookies is null or if there is no Token cookie/HMAC cookie or if the
         //User ID is not in  the session
       
         
          userSession = connection.getSession(token.toString());
           tokenCookie.setMaxAge(0);
            HMACCookie.setMaxAge(0);
            servletResponse.addCookie(tokenCookie);
            servletResponse.addCookie(HMACCookie); 
            tokenCookie.setMaxAge(RandomContainerEnum.sessionTimeout);
            tokenCookie.setHttpOnly(true);
            tokenCookie.setSecure(true);
            HMACCookie.setMaxAge(RandomContainerEnum.sessionTimeout);
            HMACCookie.setHttpOnly(true);
            HMACCookie.setSecure(true);
            servletResponse.addCookie(tokenCookie);
            servletResponse.addCookie(HMACCookie);
            Servletsession.clear();
            connection.revokeSession(userSession.getSessionID());
            return SUCCESS;
      
     }
    
}
