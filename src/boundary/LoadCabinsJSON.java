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

import object.Cabin;
import persistence.CabinManager;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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
		int cabinId;
        
        // set up the response writer
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter toClient = response.getWriter();
        
        //cabinId = Integer.parseInt( request.getParameter("cabinId") );
        
        List<Cabin> cabins = CabinManager.restore(null);
        
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder cabinArray = Json.createArrayBuilder();
        for(int i = 0; i < cabins.size(); i++)
        {
        	Cabin cabin = cabins.get( i );
        	cabinArray.add( Json.createObjectBuilder()
        			.add("title", cabin.get))
        }
            .add("cabins", Json.createArrayBuilder()
            		.add( Json.createObjectBuilder()
            				.add(arg0, arg1))
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
