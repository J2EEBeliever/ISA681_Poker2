package com.tutorialspoint.struts2;

import org.apache.log4j.Logger;

import cipher.CipherUtils;import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
;

public class HelloWorldAction {

	   private String name;
	   
		final static Logger log = Logger.getLogger(HelloWorldAction.class);
				
		public static void main() {
			
               try {
                   String password = "password1";
                   
                   log.debug("Debug: password = " + password);
                   
                   String encryptedPassword = CipherUtils.encrypt(password);
                   
                   log.debug("Debug: encryptedPassword = " + encryptedPassword);
                   
                   String unEncryptedPassword = CipherUtils.decrypt(encryptedPassword);
                   
                   log.debug("Debug: unEncryptedPassword = " + unEncryptedPassword);
               } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
                   log.error(ex);
                  
                   
               }
			
			
			
			
			
		}
                
                

	   public String execute() throws Exception {
		   
	      log.debug("Debug: HelloWorldAction:  execute()");
	      log.info("Info: HelloWorldAction: execute()");
	      main();
	      return "success";
	      
	   }
	   
	   public String getName() {
		   
	      log.debug("Debug: HelloWorldAction: getName()");
	      log.info("Info: HelloWorldAction: getName()");

	      return name;
	   }

	   public void setName(String name) {

	      log.debug("Debug: HelloWorldAction: setName(name)");
	      log.info("Info: HelloWorldAction: setName(name)");
	      
	      this.name = name;
	   }
	}
