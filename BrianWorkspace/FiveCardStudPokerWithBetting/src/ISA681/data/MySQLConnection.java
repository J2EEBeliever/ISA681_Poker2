/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681.data;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bwoltemate
 */
public class MySQLConnection implements dataconnection
{
   
    @Override
    public User getUserData(String UserName) {
         User inComingUser = new User(-1,"","");
        try {
            
          
       
          Connection conn = createConnection();
        
        
       
           PreparedStatement stmt = conn.prepareStatement("SELECT UserID, UserName, Password From users WHERE UserName = ?");
           stmt.setString(1, UserName);
        
       
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next())
        {
           inComingUser = new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Password"));
        }
       
    }
      catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
         return inComingUser;
    }

    @Override
    public void CreateSession(int UserID, String SessionToken, long rand) {

            try
            {
            Connection conn = createConnection();        
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO Session (UserID, Token, Rand,ActionCount,LoginTime ) VALUES (?, ?, ?,1,Now())");
            stmt.setInt(1, UserID);
            stmt.setString(2, SessionToken);
            stmt.setLong(3, rand);
            
          stmt.executeUpdate();
            // TODO code application logic here
        } catch (ClassNotFoundException| SQLException e) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
        @Override
    public Session getSession(String Token)
    {
        Session UserSession = new Session(-1,-1,"",(long)-1, (long)-1,(long)-1);
        try {
            Connection conn = createConnection();
           
             PreparedStatement stmt = conn.prepareStatement("SELECT `SessionID`,`UserID`,`Token`,`Rand`,UNIX_TIMESTAMP(`Timestamp`)utimestamp,UNIX_TIMESTAMP(Now())currentTime FROM `session` WHERE Token = ?");
           stmt.setString(1, Token);
        
       
        ResultSet rs = stmt.executeQuery();
        while (rs.next())
        {
           UserSession = new Session(rs.getInt("SessionID"), rs.getInt("UserID"), rs.getString("Token"), rs.getLong("Rand"), rs.getLong("utimestamp"), rs.getLong("currentTime"));
        }
            stmt = conn.prepareStatement("UPDATE session SET ActionCount = ActionCount+1, timestamp = now()");
            stmt.executeUpdate();
    }
      catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
           
        }
         return UserSession;  
            
       
        
       
                
     
    }
    @Override
    public void revokeSession(int SessionID)
    {
    
        try {
            Connection conn = createConnection();
           
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM `session` WHERE SessionID = ?");
           stmt.setInt(1, SessionID);
        
       
        stmt.executeUpdate();
        
       
    }
      catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
      @Override
    public void revokeUserSession(int UserID)
    {
    
        try {
            Connection conn = createConnection();
           
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM `session` WHERE UserID = ?");
           stmt.setInt(1, UserID);
        
       
        stmt.executeUpdate();
        
       
    }
      catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

     @Override
    public List<Game> getOpenGames() {
       
           List<Game> openGames = new ArrayList<>();
        try {
            
          
       
          Connection conn = createConnection();
        
        
       
           PreparedStatement stmt = conn.prepareStatement("SELECT GameID, GameName, GameStatus, GameState From game WHERE GameStatus =0");
          
        
       
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next())
        {
        
           
           openGames.add(new Game(rs.getInt("GameID"), rs.getString("GameName"), rs.getInt("GameStatus"), ""));
        }
       
    }
      catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
         return openGames;
    }
        
     @Override
    public void addGame(int Player1ID, String GameName) {
           try
            {
            Connection conn = createConnection();        
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO game (GameName, Player1, GameStatus) VALUES (?, ?, 0)");
            stmt.setString(1, GameName);
            stmt.setInt(2, Player1ID);
            stmt.executeUpdate();
            // TODO code application logic here
        } catch (ClassNotFoundException| SQLException e) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

   @Override
    public void joinGame(int Player2ID, int gameID, String gameState) {
        try
    { 
        Connection conn = createConnection();  
        PreparedStatement stmt;
        stmt = conn.prepareStatement("UPDATE game SET Player2 = ?, GameStatus=1, GameState=? WHERE gameID = ?");
        stmt.setInt(1, Player2ID);
        Blob blob = conn.createBlob();
        blob.setBytes(1, gameState.getBytes());
        stmt.setBlob(2, blob);
        stmt.setInt(3, gameID);
        stmt.executeUpdate();
    }
      catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
    }     
    
    
private Connection createConnection() throws ClassNotFoundException, SQLException
{
     Class.forName("com.mysql.jdbc.Driver");
       
            Connection conn ;
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fcs", "fcs_user", "7yXw8dDaNMBNBbW5");
            return conn;
}

    

   
   


    
}
