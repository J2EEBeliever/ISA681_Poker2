/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681Poker;

import ISA681.data.Game;
import ISA681.data.MySQLConnection;
import ISA681.data.dataconnection;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author bwoltemate
 */
public class ListGames extends ActionSupport implements LoginRequired {
    
    private final List<Game> OpenGames;
    
    public ListGames()
    {
        dataconnection con = new MySQLConnection();
        OpenGames = con.getOpenGames();
                
    }
    
    public List<Game> getOpenGames()
    {
        return OpenGames;
    }
    
}
