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
public enum RandomContainerEnum  {
    INSTANCE;
    public final RandomContainer randomContainer = new RandomContainer();
    public final DeckContainer continuousDeck = new DeckContainer();
    public final static int reseedAfter = 100;
    public final static int sessionTimeout = 3600;
    
    
}
