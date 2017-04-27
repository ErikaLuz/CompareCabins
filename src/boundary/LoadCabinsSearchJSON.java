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
import logic.LogicLayerImplShep;
import object.Amenities;
import object.Availability;
import object.Cabin;
import object.CabinPicture;
import persistence.CabinManager;

/**
 * Servlet implementation class LoadCabinsSearchJSON
 */
@WebServlet("/LoadCabinsSearchJSON")
public class LoadCabinsSearchJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadCabinsSearchJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] primaryPhotoFilePath = null;
        
		StringBuffer requestURL = request.getRequestURL();
		if (request.getQueryString() != null) {
		    requestURL.append("?").append(request.getQueryString());
		}
		String completeURL = requestURL.toString();
		
		System.out.println(completeURL);
        // set up the response writer
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter toClient = response.getWriter();
        
        String hasLake = request.getParameter("hasLake");
		String hasRiver = request.getParameter("hasRiver");
		String hasPool = request.getParameter("hasPool");
		String hasHotTub = request.getParameter("hasHotTub");
		String hasWifi = request.getParameter("hasWifi");
		String hasAirConditioning = request.getParameter("hasAirConditioning");
		String hasWasherDryer = request.getParameter("hasWasherDryer");
		String allowsPets = request.getParameter("allowsPets");
		String allowsSmoking = request.getParameter("allowsSmoking");
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
		
		Amenities amenities = new Amenities();
		if(hasLake != null)
			amenities.setHasLake(true);
		if(hasRiver != null)
			amenities.setHasRiver(true);
		if(hasPool != null)
			amenities.setHasLake(true);
		if(hasHotTub != null)
			amenities.setHasHotTub(true);
		if(hasWifi != null)
			amenities.setHasWifi(true);
		if(hasAirConditioning != null)
			amenities.setHasAirConditioning(true);
		if(hasWasherDryer != null)
			amenities.setHasWasherDryer(true);
		if(allowsPets != null)
			amenities.setAllowsPets(true);
		if(allowsSmoking != null)
			amenities.setAllowsSmoking(true);
        
		
        
        List<Cabin> cabins = null;
		try {
			cabins = LogicLayerImplShep.search( amenities, start, end );
	
	        primaryPhotoFilePath = new String[ cabins.size() ];
	        
	        for( int i = 0; i < cabins.size(); i++ )
	        {
	        	List<CabinPicture> pictures = CabinManager.restoreCabinPicturesFromCabin( cabins.get(i) );
	        	for( int j = 0; j < pictures.size(); j++ )
	        	{
	        		CabinPicture cabinPicture = pictures.get(j);
	        		int priority = cabinPicture.getPriority();
	        		if( priority == 1)
	        		{
	        			primaryPhotoFilePath[i] = cabinPicture.getFilePath();
	        			break;
	        		}
	        	}
	        }
		} catch (CCException cce ) {
			cce.printStackTrace();
		}
		
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder cabinArray = Json.createArrayBuilder();
        for(int i = 0; i < cabins.size(); i++)
        {
        	Cabin cabin = cabins.get( i );
        	cabinArray.add( Json.createObjectBuilder()
        			.add("title", cabin.getTitle() )
        			.add("description", cabin.getDescription() )
        			.add("primaryPhotoPath", primaryPhotoFilePath[i] ) 
        			.add("cabinId", cabin.getId() ) );  
        }
        builder.add("cabins", cabinArray );
        JsonObject json = builder.build();
        
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
		monthInt--;
		int dayInt = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yearInt, monthInt, dayInt);
		Availability availability = new Availability();
		availability.setDate( cal );
		
		return availability;
	}


}
