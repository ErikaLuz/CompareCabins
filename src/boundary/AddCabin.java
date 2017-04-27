package boundary;

import java.util.List;
import java.util.LinkedList;

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

import persistence.UserManager;
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
				{
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
					// Session Tracking
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute( "username");
			        root.put("username", user.getUsername());
					
					String templateName = "AddCabin.ftl";
					processor.processTemplate(templateName, root, request, response);
				}
				
				if(addCabin != null)
				{
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
					// Session Tracking
					HttpSession session = request.getSession();
					User userTracking = (User) session.getAttribute( "user");
			        root.put("username", userTracking.getUsername());
					
					// Get user
					
						User user = (User)session.getAttribute("user");
					
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
							
								
/*								// For testing purposes, print out values: 
							
									System.out.println("Lake: " + hasLake);
									System.out.println("River: " + hasRiver);
									System.out.println("Pool: " + hasPool);
									System.out.println("Hot Tub: " + hasHotTub);
									System.out.println("Wifi: " + hasWifi);
									System.out.println("AirConditioning: " + hasAirConditioning);
									System.out.println("WasherDryer: " + hasWasherDryer);
									System.out.println("Pets: " + allowsPets);
									System.out.println("Smoking: " + allowsSmoking);
*/
								
								// Store the amenities in database
								
									try {
										AmenitiesManager.store(cabinAmenities);
									} catch (CCException e1) {
										
										e1.printStackTrace();
									}
									
									root.put("Amenities", cabinAmenities);
									root.put("AmenitiesCheck", "notNull");
									
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
						 
							 try {
								LogicLayerImpl.addCabin(modelCabin);
							} catch (CCException e) {
								
								e.printStackTrace();
							}
							 
							 root.put("Cabin", modelCabin);
								 
					// Get cabin feature  TODO: get multiple cabin features using javascript
								 
						String feature = request.getParameter("features");
						Feature cabinFeature = new Feature();
						
						if(feature != null && !feature.equals(""))
						{
							cabinFeature = new Feature(feature);
							cabinFeature.setCabin(modelCabin);
							
							try {
								FeatureManager.store(cabinFeature);
							} catch (CCException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							root.put("Features", cabinFeature);
							root.put("FeaturesCheck", "notNull");
						}
						else root.put("FeaturesCheck", "null");
						
					// TODO: Delete or change template later ? will just go to cabin listing?
						
						String templateName = "AddCabinSuccess.ftl";
						processor.processTemplate(templateName, root, request, response);
				} 
				
			} // end of doGet
	
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}