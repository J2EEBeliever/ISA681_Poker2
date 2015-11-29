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
public class Session {
    private final int SessionID;
    private final int UserID;
    private final String Token;
    private final Long tokenRand;
    private final long timestamp;
    private final long currentTime;
    
    public Session(int SessionID, int UserID, String Token, Long tokenRand, long timestamp, long currentTime)
    {
        this.SessionID = SessionID;
        this.UserID = UserID;
        this.Token = Token;
        this.tokenRand = tokenRand;
        this.timestamp = timestamp;
        this.currentTime = currentTime;
    }
    
    public int getSessionID()
    {
        return SessionID;
    }
    
    public int getUserID()
    {
        return UserID;
    }
    
    public String getToken()
    {
        return Token;
    }
    
    public Long getTokenRand()
    {
        return tokenRand;
    }
    
    public long getTimestamp()
    {
        return timestamp;
    }
    
     public long getCurrentTime()
    {
        return currentTime;
    }
}
