/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author b1wolt
 */
public class GameCountContainer implements Serializable {
    private static final long serialVersionUID = 2015_12_14_014L;
    private int gameCount = 0;
    
     public synchronized int getAndIncramentCount()
    {
        return ++gameCount;
    }
    
    
}
