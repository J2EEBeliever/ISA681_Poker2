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
public class Game {
    
    private final int gameID;
    private final String gameName;
    private final int gameStatus;
    private final String gameState;
    
    public Game(int gameID, String gameName, int gameStatus, String gameState)
    {
        this.gameID = gameID;
        this.gameName = gameName;
        this.gameStatus = gameStatus;
        this.gameState = gameState;
    }
    
    public int getGameID()
    {
        return gameID;
    }
    
    public String getGameName()
    {
        return gameName;
    }
    
    public int getGameStatus()
    {
        return gameStatus;
    }
    
    public String getGameState()
    {
        return gameState;
    }
    
}
