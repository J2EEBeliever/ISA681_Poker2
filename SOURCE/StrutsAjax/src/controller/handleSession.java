/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.RandomContainerEnum;
import entities.MySQLConnection;
import entities.Session;
import entities.dataconnection;
import cipher.CipherUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author bwoltemate
 */
public class handleSession extends AbstractInterceptor{
    


    @Override
    public String intercept(ActionInvocation ai) throws Exception {
     HttpServletRequest servletRequest = ServletActionContext.getRequest();
     HttpServletResponse servletResponse = ServletActionContext.getResponse();
     Map<String, Object> Servletsession = ActionContext.getContext().getSession();
        Object action = ai.getAction();
        if (!(action instanceof LoginRequired)) {
            return ai.invoke();
        }
        Cookie tokenCookie = new Cookie("temp","temp");
        Cookie HMACCookie = new Cookie("temp2","temp");
        dataconnection connection = new MySQLConnection();
        UUID token = null;
        String HMAC = "";
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
                        HMAC = c.getValue();
                        HMACCookie = c;
                        break;
                }
        }
         //If the get cookies is null or if there is no Token cookie/HMAC cookie or if the
         //User ID is not in  the session
        if(token != null && HMAC != null && Servletsession.get("UserID") != null)
            userSession = connection.getSession(token.toString());
        else
        {
            tokenCookie.setMaxAge(0);
            HMACCookie.setMaxAge(0);
            tokenCookie.setValue("");
            HMACCookie.setValue("");
            servletResponse.addCookie(tokenCookie);
            servletResponse.addCookie(HMACCookie);
            Servletsession.clear();
            return "SessionExpiredRedirect";
         }
         
        String recalculatedHMAC = CipherUtils.createHMACSHA256(userSession.getToken(),userSession.getTokenRand().toString());
       
        if(HMAC.equals(recalculatedHMAC) && userSession.getUserID() == (int)Servletsession.get("UserID") && (userSession.getCurrentTime() - userSession.getTimestamp()) < RandomContainerEnum.sessionTimeout)
        {
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
            return ai.invoke();
        }
        else
        {
            tokenCookie.setMaxAge(0);
            HMACCookie.setMaxAge(0);
            tokenCookie.setValue("");
            HMACCookie.setValue("");
            servletResponse.addCookie(tokenCookie);
            servletResponse.addCookie(HMACCookie);
            Servletsession.clear();
            connection.revokeSession(userSession.getSessionID());
             return "SessionExpiredRedirect";
        }

    }
  
}
