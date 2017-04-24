package boundary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import logic.LogicLayerImpl;
import object.User;



/**
 * Servlet implementation class AustinTest
 */
@WebServlet("/AustinTest")
public class AustinTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    /**
     * @see HttpServlet#HttpServlet()
     */
    public AustinTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cabinId;
        
        // set up the response writer
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter toClient = response.getWriter();
        
        //cabinId = Integer.parseInt( request.getParameter("cabinId") );
        
        Calendar cal = Calendar.getInstance();
        int numDays = cal.getActualMaximum( Calendar.DAY_OF_MONTH );
        int currentDay = cal.get( Calendar.DAY_OF_MONTH );
        int dayOfWeek = cal.get( Calendar.DAY_OF_WEEK );
        JsonObjectBuilder builder = Json.createObjectBuilder()
            .add("dayOfWeek", dayOfWeek )
        	.add("currentDay", currentDay )
        	.add("numDays", numDays );
        JsonObject json = builder.build();
        
        toClient.write( json.toString() );
        toClient.flush();
            
        // load the session
        // session = request.getSession();
        
        

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
