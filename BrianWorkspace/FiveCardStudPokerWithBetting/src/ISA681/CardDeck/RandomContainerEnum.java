/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681.CardDeck;

/**
 *
 * @author bwoltemate
 */
public enum RandomContainerEnum  {
    INSTANCE;
    public RandomContainer RandomContainer = new RandomContainer();
    public DeckContainer continuousDeck = new DeckContainer();
    public static int reseedAfter = 100;
    public static int sessionTimeout = 3600;
    
    
}
