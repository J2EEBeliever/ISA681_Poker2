/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import controller.FiveCardStudPokerAjaxAction;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author bwoltemate
 */
public class MySQLConnection implements dataconnection
{
   final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MySQLConnection.class);
   @Override
     public User getUserData(String UserName) {
         User inComingUser = new User(-1,"","", new Timestamp(0));
        try {
            
          
       
          Connection conn = this.getDatabaseConnection(); 
        
        
       
           PreparedStatement stmt = conn.prepareStatement("SELECT UserID, UserName, Password, Timestamp From users WHERE UserName = ?");
           stmt.setString(1, UserName);
        
       
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next())
        {
           inComingUser = new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Password"), rs.getTimestamp("Timestamp"));
        }
       
    }
      catch (ClassNotFoundException|SQLException ex) {
             log.error(ex);
        }
         return inComingUser;
    }


   @Override
    public void CreateSession(int UserID, String SessionToken, long rand) {

            try
            {
            Connection conn = this.getDatabaseConnection();         
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO Session (UserID, Token, Rand,ActionCount,LoginTime ) VALUES (?, ?, ?,1,Now())");
            stmt.setInt(1, UserID);
            stmt.setString(2, SessionToken);
            stmt.setLong(3, rand);
            
          stmt.executeUpdate();
            // TODO code application logic here
        } catch (ClassNotFoundException| SQLException e) {
             log.error(e);
        }
    }
    
     
   @Override
    public Session getSession(String Token)
    {
        Session UserSession = new Session(-1,-1,"",(long)-1, (long)-1,(long)-1);
        try {
            Connection conn = this.getDatabaseConnection();  
           
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
            log.error(ex);
           
        }
         return UserSession;  
            
       
        
       
                
     
    }
 
   @Override
    public void revokeSession(int SessionID)
    {
    
        try {
            Connection conn = this.getDatabaseConnection(); 
           
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM `session` WHERE SessionID = ?");
           stmt.setInt(1, SessionID);
        
       
        stmt.executeUpdate();
        
       
    }
      catch (ClassNotFoundException|SQLException ex) {
            log.error(ex);
        }
     
    }
    
   
   @Override
    public void revokeUserSession(int UserID)
    {
    
        try {
            Connection conn = this.getDatabaseConnection(); 
           
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM `session` WHERE UserID = ?");
           stmt.setInt(1, UserID);
        
       
        stmt.executeUpdate();
        
       
    }
      catch (ClassNotFoundException|SQLException ex) {
            log.error(ex);
     
    }
    }
public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
		// create our mysql database connection
		String myDriver = "org.gjt.mm.mysql.Driver";
		String myUrl = "jdbc:mysql://localhost/fcs";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "fcs_user", "7yXw8dDaNMBNBbW5");

		return conn;

	}
}
