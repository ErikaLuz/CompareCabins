package boundary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.CCException;
import logic.LogicLayerImplAlt;
import object.Availability;
import object.Cabin;
import object.CabinPicture;
import persistence.CabinManager;

/**
 * Servlet implementation class CheckPriceJSON
 */
@WebServlet("/CheckPriceJSON")
public class CheckPriceJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPriceJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        float totalPrice = 0;
        boolean available = false;
        // set up the response writer
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter toClient = response.getWriter();
        
        int cabinId = Integer.parseInt( request.getParameter("cabinId") );
        
		String startDateString = request.getParameter("startAvailability");
		String endDateString = request.getParameter("endAvailability");
		
		Availability start;
		if(startDateString.length() > 0)
			start = parseDateString( startDateString );
		else
			start = null;
		Availability end;
		if(endDateString.length() > 0)
			end = parseDateString( endDateString );
		else
			end = null;
		
		
		

		try {
			totalPrice = LogicLayerImplAlt.getTotalPrice( start, end, cabinId );
			if( totalPrice < 0)
				available = false;
			else
				available = true;
			
		} catch (CCException cce ) {
			cce.printStackTrace();
		}
		
        JsonObject json = Json.createObjectBuilder()


        .add("totalPrice", totalPrice )
        .add("available", available )
        .build();
        
        toClient.write( json.toString() );
        toClient.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private Availability parseDateString( String dateString )
	{
		String year = dateString.substring(0,4);
		String month = dateString.substring(5,7);
		String day = dateString.substring(8,10);
		
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month);
		int dayInt = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yearInt, monthInt, dayInt);
		Availability availability = new Availability();
		availability.setDate( cal );
		
		return availability;
	}

}
