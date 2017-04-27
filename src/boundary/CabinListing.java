package boundary;

import java.io.IOException;

//import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boundary.TemplateProcessor;

import logic.LogicLayerImpl;

import object.Cabin;
import object.User;

//import object.User;
//import object.Review;
//import object.Feature;
//import object.RentRecord;
//import object.Amenities;

//import persistence.UserManager;
//import persistence.ReviewManager;
//import persistence.FeatureManager;
//import persistence.RentRecordManager;
//import persistence.AmenitiesManager;
//import persistence.CabinManager;

import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

/**
 * Servlet implementation class CabinListing
 */
@WebServlet("/CabinListing")
public class CabinListing extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
    //@see HttpServlet#HttpServlet() 
		public CabinListing() 
		{
			super();
		} //end of constructor
    
	//@see Servlet#init(ServletConfig) 
		public void init(ServletConfig config) throws ServletException 
		{
			super.init(config);
			processor = new TemplateProcessor(templateDir, getServletContext());
		} //end of init
		
	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			String cabinListing = request.getParameter("cabinListing");
			
			if(cabinListing != null)
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				// Session Tracking
				HttpSession session = request.getSession();
				if(session.getAttribute("user") != null) {
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				}
/*				// For testing purposes - delete later
				
					User user = new User("Cabin", "Listing", "firstName", "lastName", "email");
					
					try {
						LogicLayerImpl.register(user);
					} catch (CCException e1) {

						e1.printStackTrace();
					}
					
					Amenities amenity = new Amenities(true, false, true, true, false, true, true, false, true);
					
					try {
						AmenitiesManager.store(amenity);;
					} catch (CCException e1) {

						e1.printStackTrace();
					}
					
					Cabin cabin = new Cabin("address", "city", "state", "this cabin rocks, it has a nice view","Great Cabin", 2, 3, 6);
					cabin.setUser(user);
					cabin.setAmenities(amenity);
					
					try {
						CabinManager.store(cabin);
					} catch (CCException e1) {
						e1.printStackTrace();
					}
					
					Feature feature = new Feature("This cabin is the greatest cabin ever, I love it a lot.");
					feature.setCabin(cabin);
					
					try {
						FeatureManager.store(feature);
					} catch (CCException e1) {
						e1.printStackTrace();
					}
					
					float z = 2, y = 3;
					Calendar date = Calendar.getInstance();
					
					RentRecord rr1 = new RentRecord(z, date, date);
					RentRecord rr2 = new RentRecord(y, date, date);
					rr1.setCabin(cabin);
					rr2.setCabin(cabin);
					
					try {
						RentRecordManager.store(rr1);
						RentRecordManager.store(rr2);
					} catch (CCException e1) {
						e1.printStackTrace();
					}
					
					Review review1 = new Review(10, "Great place", "Nice place, nice view");
					Review review2 = new Review(5, "this place blows", "dont rent, bad idea");
					review1.setRentRecord(rr1);
					review2.setRentRecord(rr2);
					
					try {
						ReviewManager.store(review1);
						ReviewManager.store(review2);
					} catch (CCException e1) {
						e1.printStackTrace();
					}		
*/				
				// Create model cabin with cabinId 
				
					String cabinIdString = request.getParameter("cabinId");
					int cabinId = Integer.parseInt(cabinIdString);
					Cabin modelCabin = new Cabin();
					modelCabin.setId(cabinId);
				
					
				// Call logic layer and call cabin listing code
					
					try{
						LogicLayerImpl.cabinListing(root, modelCabin);
					}catch (CCException e)
					{
						e.printStackTrace();
					}
					
				// Set and process templates
				
					String templateName = "GuestCabinListing.ftl";
					processor.processTemplate(templateName, root, request, response);
				
/*				// Delete dummy objects
				
					try {
						ReviewManager.delete(review1);
						ReviewManager.delete(review2);
						RentRecordManager.delete(rr1);
						RentRecordManager.delete(rr2);
						FeatureManager.delete(feature);
						CabinManager.delete(cabin);
						AmenitiesManager.delete(amenity);
						UserManager.delete(user);
					} catch (CCException e1) {
						e1.printStackTrace();
					} */
			}
			
		} // end of doGet

	//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doGet(request, response);
		} //end of doPost
}
