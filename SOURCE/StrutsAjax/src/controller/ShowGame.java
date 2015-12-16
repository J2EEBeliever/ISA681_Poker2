/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import entities.User;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author bwoltemate
 */
public class ShowGame extends ActionSupport implements LoginRequired, SessionAware  {
    private static final long serialVersionUID = 2015_12_14_005L;
    
    //Map is initaized by struts when show game is loaded. Thus below is not an error
    @SuppressFBWarnings(justification="No bug", value="UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR")
    private Map<String, Object> session;

   
     @Override
     public String execute()
  {
      return SUCCESS;
  }
     
   
     
     public boolean getAdmin()
     {
        User theUser = (User)session.get("user");
        return theUser.getAdmin();
     }
    
       @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
