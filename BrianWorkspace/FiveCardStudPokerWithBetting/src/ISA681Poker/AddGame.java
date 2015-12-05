/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681Poker;

import ISA681.data.MySQLConnection;
import ISA681.data.dataconnection;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author b1wolt
 */
public class AddGame extends ActionSupport implements LoginRequired, SessionAware {
    private Map<String, Object> session;
    private String gameName;
   
    @Override
    public String execute()
  {
     dataconnection con = new MySQLConnection();
     con.addGame((int)session.get("UserID"), gameName);
     return SUCCESS;
  }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }
    
    public String getGameName()
    {
        return gameName;
    }
}
