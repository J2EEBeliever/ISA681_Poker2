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
public interface dataconnection {
    
    public User getUserData(String UserName);
    public void CreateSession(int UserID,String SessionID, long rand);
    public Session getSession(String Token);
    public void revokeSession(int SessionID);
    public void revokeUserSession(int UserID);
}