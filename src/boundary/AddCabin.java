package boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boundary.TemplateProcessor;
import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

import logic.LogicLayerImpl;

import object.Amenities;
import object.Cabin;
import object.Feature;

import persistence.AmenitiesManager;
import persistence.CabinManager;
import persistence.FeatureManager;

/**
 * Servlet implementation class Servlet
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
					
					String templateName = "AddCabin.ftl";
					processor.processTemplate(templateName, root, request, response);
				}
				
				if(addCabin != null)
				{
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
					
					// Get user
					
						// TODO: find a way to pass user into this servlet, they should already be logged in ?
					
					// Get amenities
						
						// Variables
						
							boolean hasLake = false, hasRiver = false, hasPool = false, hasHotTub = false, hasWifi = false;
							boolean hasAirConditioning = false, hasWasherDryer = false, allowsPets = false, allowsSmoking = false;
						
						// Get user selected amenities	
							
							String[] checkBoxAmenities = request.getParameterValues("amenities");
							
						// See which checkboxes the user has chosen and assign booleans
						
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
							
							Amenities cabinAmenities = new Amenities(hasLake, hasRiver, hasPool, hasHotTub, hasWifi, hasAirConditioning,
																     hasWasherDryer, allowsPets, allowsSmoking);
							
						// For testing purposes, print out values: 
					
							System.out.println("Lake: " + hasLake);
							System.out.println("River: " + hasRiver);
							System.out.println("Pool: " + hasPool);
							System.out.println("Hot Tub: " + hasHotTub);
							System.out.println("Wifi: " + hasWifi);
							System.out.println("AirConditioning: " + hasAirConditioning);
							System.out.println("WasherDryer: " + hasWasherDryer);
							System.out.println("Pets: " + allowsPets);
							System.out.println("Smoking: " + allowsSmoking);
							
						// Store the amenities in database
						
							try {
								AmenitiesManager.store(cabinAmenities);
							} catch (CCException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							root.put("Amenities", cabinAmenities);
						
				    // Create model cabin object
							
							// Get cabin parameters
							
								String address = request.getParameter("address");
								String city = request.getParameter("city");
								String state = request.getParameter("state");
								String description = request.getParameter("description");
								String title = request.getParameter("title");
								
								String bed = request.getParameter("bedCount");
								int bedCount = Integer.parseInt(bed);
								
								String bath = request.getParameter("bathCount");
								int bathCount = Integer.parseInt(bath);
								
								String max = request.getParameter("maxOcc");
								int maxOcc = Integer.parseInt(max);
						
								Cabin modelCabin = new Cabin(address, city, state, description, title, bedCount, bathCount, maxOcc);
								modelCabin.setAmenities(cabinAmenities);
					 
						   // Store cabin in database
							 
								 try {
									LogicLayerImpl.addCabin(modelCabin);
								} catch (CCException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								 
								 root.put("Cabin", modelCabin);
								 
					// Get cabin feature  TODO: get multiple cabin features using javascript
								 
						String feature = request.getParameter("features");
						
						Feature cabinFeature = new Feature(feature);
						cabinFeature.setCabin(modelCabin);
						
						try {
							FeatureManager.store(cabinFeature);
						} catch (CCException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						root.put("Features", cabinFeature);
						 
					// For testing purposes: delete cabin and amenities and features -- delete this code later
					
						try {
							FeatureManager.delete(cabinFeature);
							CabinManager.delete(modelCabin);
							AmenitiesManager.delete(cabinAmenities);
						} catch (CCException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
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