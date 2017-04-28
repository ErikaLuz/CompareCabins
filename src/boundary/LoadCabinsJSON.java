package boundary;

import java.io.IOException;
import java.io.PrintWriter;

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
import object.Cabin;
import object.CabinPicture;
import persistence.CabinManager;



/**
 * Servlet implementation class LoadCabinsJSON
 */
@WebServlet("/LoadCabinsJSON")
public class LoadCabinsJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadCabinsJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] primaryPhotoFilePath = null;
        
        // set up the response writer
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter toClient = response.getWriter();
        
        //cabinId = Integer.parseInt( request.getParameter("cabinId") );
        
        List<Cabin> cabins = null;
		try {
			cabins = CabinManager.restore(null);
	
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
        	if( primaryPhotoFilePath[i] != null){
        	cabinArray.add( Json.createObjectBuilder()
        			.add("title", cabin.getTitle() )
        			.add("description", cabin.getDescription() )
        			.add("primaryPhotoPath", primaryPhotoFilePath[i] ) 
        			.add("cabinId", cabin.getId() ) );  
        	} else {
            	cabinArray.add( Json.createObjectBuilder()
            			.add("title", cabin.getTitle() )
            			.add("description", cabin.getDescription() )
            			.add("primaryPhotoPath", "" ) 
            			.add("cabinId", cabin.getId() ) ); 
        	}
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

}
