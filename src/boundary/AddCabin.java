package boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boundary.TemplateProcessor;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

import exception.CCException;

import logic.LogicLayerImpl;

import object.User;
import object.Amenities;
import object.Cabin;
import object.Feature;
import object.Group;

import persistence.AmenitiesManager;
import persistence.FeatureManager;

/**
 * Servlet implementation class AddCabin
 */
@WebServlet("/AddCabin")
public class AddCabin extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
	    //@see HttpServlet#HttpServlet() 
			public AddCabin() 
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
				String goToCabin = request.getParameter("goToCabin");
				String addCabin = request.getParameter("addCabin");
				
				if(goToCabin != null)
					try {
						goToCabin(request, response);
					} catch (CCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if(addCabin != null)
					try {
						addCabin(request,response);
					} catch (CCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			} // end of doGet
			
			/**
			 * Takes user to add cabin page
			 * 
			 * @param request	HttpServletRequest
			 * @param response	HttpServletResponse
			 * @throws CCException
			 */
			private void goToCabin(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				// Create DefaultObjectWrapperBuilder and SimpleHash
				
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
					
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute( "user");
			        root.put("username", user.getUsername());
			        
			     // Set and process template
				
			        String templateName = "AddCabin.ftl";
			        processor.processTemplate(templateName, root, request, response);
			        
			} //end of goToCabin
			
			/**
			 * Adds user specified cabin to the database
			 * 
			 * @param request
			 * @param response
			 * @throws CCException
			 */
			private void addCabin(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				// Create DefaultObjectWrapperBuilder and SimpleHash
				
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute( "user");
			        root.put("username", user.getUsername());

				// Get amenities
					
					// Variables
					
						boolean hasLake = false, hasRiver = false, hasPool = false, hasHotTub = false, hasWifi = false;
						boolean hasAirConditioning = false, hasWasherDryer = false, allowsPets = false, allowsSmoking = false;
					
					// Get user selected amenities	
						
						String[] checkBoxAmenities = request.getParameterValues("amenities");
						Amenities cabinAmenities = new Amenities();
						
					// See if any amenities have been chosen, if so - create amenities object
					
						if(checkBoxAmenities != null)
						{
							for(int i = 0; i < 9; i++)
							{
								for(int j = 0; j < checkBoxAmenities.length; j++)
								{
									if(checkBoxAmenities[j].equals("hasLake")) hasLake = true;
									if(checkBoxAmenities[j].equals("hasRiver")) hasRiver = true;
									if(checkBoxAmenities[j].equals("hasPool")) hasPool = true;
									if(checkBoxAmenities[j].equals("hasHotTub")) hasHotTub = true;
									if(checkBoxAmenities[j].equals("hasWifi")) hasWifi = true;
									if(checkBoxAmenities[j].equals("hasAirConditioning")) hasAirConditioning = true;
									if(checkBoxAmenities[j].equals("hasWasherDryer")) hasWasherDryer = true;
									if(checkBoxAmenities[j].equals("allowsPets")) allowsPets = true;
									if(checkBoxAmenities[j].equals("allowsSmoking")) allowsSmoking = true;
								}
							}
							
							// Create amenities object
							
							cabinAmenities = new Amenities(hasLake, hasRiver, hasPool, hasHotTub, hasWifi, hasAirConditioning,
													       hasWasherDryer, allowsPets, allowsSmoking);
							
							// Store the amenities in database
							
								AmenitiesManager.store(cabinAmenities);
								
							} // end of if
							else root.put("AmenitiesCheck", "null");
					
			    // Create model cabin object
						
					// Get cabin parameters
							
						String address = "No Address"; 
						if(!request.getParameter("address").equals("")) address = request.getParameter("address");
						
						String city = "No City";
						if(!request.getParameter("city").equals("")) city = request.getParameter("city");		
						
						String state = "No State";
						if(!request.getParameter("state").equals("")) state = request.getParameter("state");
						
						String description = "No Description";
						if(!request.getParameter("description").equals("")) description = request.getParameter("description");
						
						String title = "No Title";
						if(!request.getParameter("title").equals("")) title = request.getParameter("title");
						
						int bedCount = 0;
						String bed = request.getParameter("bedCount");
						if(!bed.equals("")) bedCount = Integer.parseInt(bed);
						
						int bathCount = 0;
						String bath = request.getParameter("bathCount");
						if(!bath.equals("")) bathCount = Integer.parseInt(bath);
						
						int maxOcc = 0;
						String max = request.getParameter("maxOcc");
						if(!max.equals("")) maxOcc = Integer.parseInt(max);
				
						Cabin modelCabin = new Cabin(address, city, state, description, title, bedCount, bathCount, maxOcc);
						if(checkBoxAmenities != null) 	modelCabin.setAmenities(cabinAmenities);
							
					// Set User
							
						modelCabin.setUser(user);
				 
				// Call logic layer and store cabin in database
					 
						LogicLayerImpl.addCabin(modelCabin);
							 
				// Get cabin feature  TODO: get multiple cabin features using javascript
							 
					String feature = request.getParameter("features");
					Feature cabinFeature = new Feature();
					
					if(feature != null && !feature.equals(""))
					{
						cabinFeature = new Feature(feature);
						cabinFeature.setCabin(modelCabin);
						
						FeatureManager.store(cabinFeature);						
					}
				
				// Create group object
					
					Group group = new Group();
					
				// Call logic layer to get CabinListing code
					
					group = LogicLayerImpl.cabinListing(modelCabin);
					
					// If statements for Hash
					
					if(group.getUser() != null) root.put("Usercheck", "notNull");
					else root.put("UserCheck", "null");
					
					if(group.getAmenities() != null) root.put("AmenitiesCheck", "notNull");
					else root.put("AmenitiesCheck", "null");
				
					if(group.getCabinPictureList() != null && group.getCabinPictureList().size() != 0)
					{
						root.put("CPCheck", "notNull");
						
						if(group.getCabinPicture() != null) root.put("PriorityCheck", "notNull"); 
						else root.put("PriorityCheck", "null");
					}
					else root.put("CPCheck", "null");
					
					if(group.getFeatureList() != null && group.getFeatureList().size() != 0) root.put("FeaturesCheck", "notNull");
					else root.put("FeaturesCheck", "null");
					
					if(group.getAvailabilityList() != null && group.getAvailabilityList().size() != 0) root.put("AvailabilitiesCheck", "notNull");
					else root.put("AvailabilitiesCheck", "null");
					
					if(group.getReviewList() != null && group.getReviewList().size() != 0) root.put("ReviewsCheck", "notNull");
					else root.put("ReviewsCheck", "null");
				
				// Place group into root
					
					root.put("Group", group);
					
				// Set and process template
					
					String templateName = "OwnerCabinListing.ftl";
					processor.processTemplate(templateName, root, request, response);
					
			} // end of addCabin
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}