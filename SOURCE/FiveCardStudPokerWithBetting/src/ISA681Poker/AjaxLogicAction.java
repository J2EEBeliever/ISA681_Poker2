package ISA681Poker;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts2.ServletActionContext;
import org.json.HTTP;

import com.opensymphony.xwork2.ActionSupport;

//import cipher.CipherUtils;;

public class AjaxLogicAction extends ActionSupport{

//	   private String name;
	   
		final static Logger log = Logger.getLogger(AjaxLogicAction.class);
				
//		public static void main(String[] args_) {
//			
//			String password = "password1";
//
//			log.debug("Debug: password = " + password);
//			
//			String encryptedPassword = CipherUtils.encrypt(password);
//			
//			log.debug("Debug: encryptedPassword = " + encryptedPassword);
//			
//			String unEncryptedPassword = CipherUtils.decrypt(encryptedPassword);
//			
//			log.debug("Debug: unEncryptedPassword = " + unEncryptedPassword);
//			
//			
//			
//			
//			
//		}

	   public String execute() {
		
		/* public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
		        HttpServletRequest request, HttpServletResponse response)
		        throws Exception { */

			
		   
	      log.debug("Debug: AjaxAction:  execute()");
	      log.info("Info: AjaxAction: execute()");
	      
	      //AjaxLogicForm ajaxLogicForm = (AjaxLogicForm)actionForm;
	      
	      
	      
	      //String actionType = ajaxLogicForm.getPlayerContent();
	      
			HttpServletRequest request = ServletActionContext.getRequest();
			
			
	      
	      String actionType = (String) request.getParameter("playerContent");
	      
	      if(actionType != null && actionType.equalsIgnoreCase("yes")) {
	    	  
	    	  //PrintWriter printWritter = ServletActionContext.getResponse().getWriter();
	    	  
	    	  
	    	  //HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
	    	  
	    	  //httpServletResponse


		      log.debug("\n\nDebug: AjaxAction:  getPlayerContent == 'yes'\n\n");

	    	  
	    	  String playerContent = "<BR><BR>Player1 cards (Ace Spaces, 2 Clubs, 2 Diamonds)<BR><BR>" +
	    	  "<BR><BR>Player2 cards (King Diamonds, 3 Hearts, King Spades)<BR><BR>";
	    	  
	    	  PrintWriter printWriter;
			try {
				  printWriter = ServletActionContext.getResponse().getWriter();
		    	  printWriter.print(playerContent);
		    	  
		    	  printWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  

//	    	  ServletActionContext.getServletContext().setAttribute(playerContent, playerContent);

	    	  
	    	  //printWritter.close();
	    	  
		      


	      
	      }
	    	  
    	  return "success";	      
	      
	      
	      
	   }
	   
	   
	   
	   
	   
	}