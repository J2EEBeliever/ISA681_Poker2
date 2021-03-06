package ISA681Poker;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * Servlet implementation class AjaxLogicServlet
 */
@WebServlet("/AjaxLogicServlet")
public class AjaxLogicServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	final static Logger log = Logger.getLogger(AjaxLogicServlet.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxLogicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		   
	      log.debug("Debug: AjaxLogicServlet:  execute()");
	      log.info("Info: AjaxLogicServlet: execute()");
	      
	      //AjaxLogicForm ajaxLogicForm = (AjaxLogicForm)actionForm;
	      
	      
	      
	      //String actionType = ajaxLogicForm.getPlayerContent();
	      
			//HttpServletRequest request = ServletActionContext.getRequest();
			
			
	      
	      String actionType = (String) request.getParameter("playerContent");
	      
	      if(actionType != null && actionType.equalsIgnoreCase("yes")) {
	    	  
	    	  
	    	  
	    	  HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
	    	  
	    	  PrintWriter printWriter = httpServletResponse.getWriter();
	    	  

		      log.debug("\n\nDebug: AjaxAction:  getPlayerContent == 'yes'\n\n");

	    	  
		      printWriter.print("<BR><BR>Player1 cards (Ace Spaces, 2 Clubs, 2 Diamonds)<BR><BR>" +
		    		  "<BR><BR>Player2 cards (King Diamonds, 3 Hearts, King Spades)<BR><BR>");

	      
	      }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
