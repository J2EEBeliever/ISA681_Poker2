/*
 * Author: Linus Freeman
 * 
 */


package entities;

import java.sql.Timestamp;

public class User {
	
	  private int userID;
	  private String username;
	  private String password;
	  private Timestamp timestamp;
          
        public User (int userID, String userName, String password, Timestamp timestamp)
        {
        this.userID = userID;
        this.username = userName;
        this.password = password;
        this.timestamp = (Timestamp)timestamp.clone();
        }
	  
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getTimestamp() {
		return (Timestamp)timestamp.clone();
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = (Timestamp)timestamp.clone();
	}
	  

	
	/*
	 * --
	-- Table structure for table `users`
	--

	CREATE TABLE IF NOT EXISTS `users` (
	  `UserID` int(11) NOT NULL AUTO_INCREMENT,
	  `UserName` varchar(20) NOT NULL,
	  `Password` char(60) NOT NULL,
	  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  PRIMARY KEY (`UserID`),
	  UNIQUE KEY `UserName` (`UserName`)
	) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
			      
	 */
	

}
