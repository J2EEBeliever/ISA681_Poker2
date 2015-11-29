/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681.data;

/**
 *
 * @author bwoltemate
 */
public class User {
    private int UserID;
    private String UserName;
    private String Password;
    
    public User (int UserID, String UserName, String Password)
    {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
    }
      
    public int getUserID()
    {
        return UserID;
    }
    public String getUserName()
    {
        return UserName;
    }
    public String getPassword()
    {
        return Password;
    }
    
}
