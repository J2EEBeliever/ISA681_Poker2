/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author bwoltemate
 */
public interface dataconnection {
    
    public User getUserData(String UserName);
    public boolean CreateSession(int UserID,String SessionID, long rand);
    public Session getSession(String Token);
    public boolean revokeSession(int SessionID);
    public boolean revokeUserSession(int UserID);
    public boolean registerUser(String userName, String generatedSecuredPasswordHash);

}
