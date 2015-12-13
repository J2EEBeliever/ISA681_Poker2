/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;

/**
 *
 * @author bwoltemate
 */
public final class RandomContainer implements Serializable{
    final static Logger log = Logger.getLogger(RandomContainer.class);
   
    private SecureRandom random = new SecureRandom();
    private int randomCount;
    
    
 
    public RandomContainer()
    {
        randomCount = 0;
    try 
    {
        random = SecureRandom.getInstance("SHA1PRNG");
    }
    catch (NoSuchAlgorithmException ex) 
    {
        log.error(ex);
        
    }
        seedRandom();
    }
    
    private void seedRandom()
    {

      byte bytes[] = new byte[20];
      random.nextBytes(bytes);
    }
    
    public synchronized SecureRandom getRandom()
    {
       if (randomCount < RandomContainerEnum.reseedAfter)
       {
           randomCount++;
           return random;
       }
       else
       {
          randomCount=0;
           seedRandom();
           return random;
       }
    }
    
}
