/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import cipher.CipherUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author bwoltemate
 */
public class MySQLConnection implements dataconnection
{
   final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MySQLConnection.class);
   @Override
     public User getUserData(String UserName) {
         User inComingUser = new User(-1,"","", false,new Timestamp(0));
         
         
          
       
             try (Connection conn = this.getDatabaseConnection(); 
              PreparedStatement stmt = conn.prepareStatement("SELECT UserID, UserName, Password, Admin, Timestamp From users WHERE UserName = ?")) 
             {
                 stmt.setString(1, UserName.toLowerCase(Locale.ENGLISH));
                 
                 
             try (ResultSet rs = stmt.executeQuery()) {
                 while (rs.next())
                 {
                     inComingUser = new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Password"), (rs.getInt("Admin") == 1),rs.getTimestamp("Timestamp"));
                 }
             }
             catch (SQLException ex)
             {
                 log.error(ex);
             }
             }
      catch (ClassNotFoundException|SQLException|IOException ex) {
             log.error(ex);
        }
      
         return inComingUser;
    }


   @Override
    public boolean createSession(int UserID, String SessionToken, long rand) {

            
            try (Connection conn = this.getDatabaseConnection(); 
              PreparedStatement stmt = conn.prepareStatement("INSERT INTO Session (UserID, Token, Rand,ActionCount,LoginTime ) VALUES (?, ?, ?,1,Now())"))
            {
            
            stmt.setInt(1, UserID);
            stmt.setString(2, SessionToken);
            stmt.setLong(3, rand);
            
          stmt.executeUpdate();
            // TODO code application logic here
        } catch (ClassNotFoundException|SQLException|IOException ex) {
             log.error(ex);
             return false;
        }
            return true;
    }
    
     
   @Override
    public Session getSession(String Token)
    {
        Session UserSession = new Session(-1,-1,"",(long)-1, (long)-1,(long)-1);
        try (Connection conn = this.getDatabaseConnection()){
            
           
             try(PreparedStatement stmt = conn.prepareStatement("SELECT `SessionID`,`UserID`,`Token`,`Rand`,UNIX_TIMESTAMP(`Timestamp`)utimestamp,UNIX_TIMESTAMP(Now())currentTime FROM `session` WHERE Token = ?"))
             {
           stmt.setString(1, Token);
        
       
                 try (ResultSet rs = stmt.executeQuery()) {
                     while (rs.next())
                     {
                         UserSession = new Session(rs.getInt("SessionID"), rs.getInt("UserID"), rs.getString("Token"), rs.getLong("Rand"), rs.getLong("utimestamp"), rs.getLong("currentTime"));
                     }        }
             }
               catch (SQLException ex) {
            log.error(ex);
           
        }
             
        try(PreparedStatement stmt2 = conn.prepareStatement("UPDATE session SET ActionCount = ActionCount+1, timestamp = now()"))
        {
            stmt2.executeUpdate();
        }
         catch (SQLException ex) {
            log.error(ex);
           
        }
    }
      catch (ClassNotFoundException|SQLException|IOException ex) {
            log.error(ex);
           
        }
         return UserSession;  
            
       
        
       
                
     
    }
 
   @Override
    public boolean revokeSession(int SessionID)
    {
    
         try (Connection conn = this.getDatabaseConnection(); 
              PreparedStatement stmt = conn.prepareStatement("DELETE FROM `session` WHERE SessionID = ?"))
         {
           stmt.setInt(1, SessionID);
        
       
        stmt.executeUpdate();
        
       
    }
      catch (ClassNotFoundException|SQLException|IOException ex) {
            log.error(ex);
            return false;
        }
         return true;
     
    }
    
   
   @Override
    public boolean revokeUserSession(int UserID)
    {
    
      
            try (Connection conn = this.getDatabaseConnection(); 
              PreparedStatement stmt = conn.prepareStatement("DELETE FROM `session` WHERE UserID = ?"))
            {
                
           stmt.setInt(1, UserID);
        
       
        stmt.executeUpdate();
        
       
    }
      catch (ClassNotFoundException|SQLException|IOException ex) {
            log.error(ex);
            return false;
    }
            return true;
    }
    
     @Override
   public boolean registerUser(String userName, String generatedSecuredPasswordHash)
{
    
    
        try (Connection conn = this.getDatabaseConnection(); 
              PreparedStatement stmt = conn.prepareStatement(" insert into users (UserName, Password, Admin) values (?, ?, 0)"))
        {
			
			/*
			 * -- -- Table structure for table `users` --
			 * 
			 * CREATE TABLE IF NOT EXISTS `users` ( `UserID` int(11) NOT NULL
			 * AUTO_INCREMENT, `UserName` varchar(20) NOT NULL, `Password`
			 * char(60) NOT NULL, `Timestamp` timestamp NOT NULL DEFAULT
			 * CURRENT_TIMESTAMP, PRIMARY KEY (`UserID`), UNIQUE KEY `UserName`
			 * (`UserName`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1
			 * AUTO_INCREMENT=3 ;
			 * 
			 */
			      
			     
			 

					//String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));

			      
			      // create the mysql insert preparedstatement
		
			      stmt.setString (1, userName);
			      stmt.setString (2, generatedSecuredPasswordHash);
		
			 
			      // execute the preparedstatement
			      stmt.execute();
			      
        }
       catch (ClassNotFoundException|SQLException|IOException ex) {
            log.error(ex);
            return false;
    }  
        return true;
}
   
   @Override
    public User getUserByUserName(String userName) throws Exception {

		log.debug("\n\nDebug: inside getUserByUserName\n");
		
		Connection conn = this.getDatabaseConnection(); 

		
		User user = null;


		try {

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM users where UserName = ? ";

                    try ( // create the java statement
                            PreparedStatement pstmnt = conn.prepareStatement(query)) {
                        pstmnt.setString(1, userName);
                        
                        
                        
                            // iterate through the java resultset
                            try ( // execute the query, and get a java resultset
                                    ResultSet rs = pstmnt.executeQuery()
                            /*
                             * -- -- Table structure for table `users` --
                             *
                             * CREATE TABLE IF NOT EXISTS `users` ( `UserID` int(11) NOT NULL
                             * AUTO_INCREMENT, `UserName` varchar(20) NOT NULL, `Password`
                             * char(60) NOT NULL, `Timestamp` timestamp NOT NULL DEFAULT
                             * CURRENT_TIMESTAMP, PRIMARY KEY (`UserID`), UNIQUE KEY `UserName`
                             * (`UserName`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1
                             * AUTO_INCREMENT=3 ;
                             *
                             */ ) {
                                // iterate through the java resultset
                                if (rs != null && rs.next()) {
                                    int userId1 = rs.getInt("UserId");
                                    String userName1 = rs.getString("UserName");
                                    String password1 = rs.getString("Password");
                                    
                                    user = new User(-1,"","",false,new Timestamp(1));
                                    
                                    user.setUserID(userId1);
                                    user.setUsername(userName1);
                                    user.setPassword(password1);
                                    
                                    // Date dateCreated = rs.getDate("date_created");
                                    // boolean isAdmin = rs.getBoolean("is_admin");
                                    // int numPoints = rs.getInt("num_points");
                                    
                                    // print the results
                                    // System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName,
                                    // lastName, dateCreated, isAdmin, numPoints);
                                }
                                if (rs.next()) { // more than one record
                                    
                                    throw new Exception("FiveCardStudPokerAjaxAction::getUser(...) incorrectly returned more than one user from table users");
                                    
                                }   }
                    }
			conn.close();
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
                

		log.debug("\n\nDebug: exiting getUserByUserName\n");

		return user;
	}
    
public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException, IOException {

     Properties props = new Properties();    
       
        
       props = FileTools.readProperties("DatabaseSettings.properties", props);
        
// create our mysql database connection
		String myDriver = props.getProperty("Driver");
		String myUrl = props.getProperty("Url");
		Class.forName(myDriver);
		Connection conn;
       try {
           conn = DriverManager.getConnection(myUrl, props.getProperty("UserName"), CipherUtils.decrypt(props.getProperty("EncyptedPassword"), "FR]k62tC_U?Au)pf"));
       } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
           log.fatal(ex);
           conn = null;
       }

		return conn;

	}

  

}
