/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681Poker;

import ISA681.CardDeck.Card;
import ISA681.CardDeck.DeckOfCards;
import ISA681.data.MySQLConnection;
import ISA681.data.dataconnection;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.KXml2DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author b1wolt
 */
public class JoinGame extends ActionSupport implements LoginRequired, SessionAware{
    
    private int gameID;
    private Map<String, Object> session;
    
    
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
    @Override
    public String execute()
  {
     dataconnection con = new MySQLConnection();
     pokerHands pokerHands = new pokerHands();
    DeckOfCards deck = new DeckOfCards(true);
    List<Card> Hand1 = new ArrayList<>();
    List<Card> Hand2 = new ArrayList<>();
    deck.draw();
    for (int i = 0; i < 2; i++)
    {
        Hand1.add(deck.draw());
        Hand2.add(deck.draw());
    }
     pokerHands.setDeckOfCards(deck);
    pokerHands.setPlayer1Hand1(Hand1);
    pokerHands.setPlayer2Hand2(Hand2);
    NameCoder code = new XmlFriendlyNameCoder("_-", "_");
    XStream xstream = new XStream(new KXml2DomDriver(code));
    con.joinGame((int)session.get("UserID"), gameID, xstream.toXML(pokerHands));
     
     
     return SUCCESS;
  }
    
    
    public int getGameID()
    {
        return gameID;
    }
    
    public void setGameID(int gameID)
    {
        this.gameID = gameID;
    }
    
    
}
