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

import object.Group;
import object.Cabin;
import object.Feature;
import persistence.CabinManager;
import persistence.FeatureManager;
import object.Amenities;
import persistence.AmenitiesManager;

/**
 * Servlet implementation class EditCabin
 */
@WebServlet("/EditCabin")
public class EditCabin extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
	    //@see HttpServlet#HttpServlet() 
			public EditCabin() 
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
				String prepareEdit = request.getParameter("prepareEdit");
				String submitEdit = request.getParameter("submitEdit");
				
				String addPhoto = request.getParameter("addPhoto");
				String deletePhoto = request.getParameter("deletePhoto");
				
				String addFeature = request.getParameter("addFeature");
				String editFeature = request.getParameter("editFeature");
				String deleteFeature = request.getParameter("deleteFeature");
				
				
				if(prepareEdit != null)
					try {
						prepareEdit(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				else if(submitEdit != null)	
					try {
						submitEdit(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				
				
				
			} // end of doGet
			
			private void prepareEdit(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Get cabin 
				
//					Cabin modelCabin = new Cabin();
//					modelCabin.setId(Integer.parseInt(request.getParameter("cabinId")));
				
				// Create group
				
					Group group = new Group();
	
				// Dummy code - delete later 
				
					Cabin modelCabin = new Cabin();
					modelCabin.setId(3);
					
					Feature feature = new Feature("i'm a test feature");
					feature.setCabin(modelCabin);
					
					FeatureManager.store(feature);
					
				// Call logic layer to prepare cabin editing
				
					group = LogicLayerImpl.prepareEditCabin( modelCabin );
					
				// Dummy code - delete later	
				
					FeatureManager.delete(feature);
					
				// Place group into root
					
					root.put("Group", group);
					
				// Set and process template
				
				String templateName = "EditCabin.ftl";
				processor.processTemplate(templateName, root, request, response);
			}
			
			private void submitEdit(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());	
				
				// Create Group
				
					Group group = new Group();
					
				// Create model cabin object
				
					// Get cabin parameters
							
						String address = "No Address"; 
						if(!request.getParameter("newAddress").equals("")) address = request.getParameter("newAddress");
						
						String city = "No City";
						if(!request.getParameter("newCity").equals("")) city = request.getParameter("newCity");		
						
						String state = "No State";
						if(!request.getParameter("newState").equals("")) state = request.getParameter("newState");
						
						String description = "No Description";
						if(!request.getParameter("newDescription").equals("")) description = request.getParameter("newDescription");
						
						String title = "No Title";
						if(!request.getParameter("newTitle").equals("")) title = request.getParameter("newTitle");
						
						int bedCount = 0;
						String bed = request.getParameter("newBedCount");
						if(!bed.equals("")) bedCount = Integer.parseInt(bed);
					
						int bathCount = 0;
						String bath = request.getParameter("newBathCount");
						if(!bath.equals("")) bathCount = Integer.parseInt(bath);
						
						int maxOcc = 0;
						String max = request.getParameter("newMaxOcc");
						if(!max.equals("")) maxOcc = Integer.parseInt(max);
					
					Cabin modelCabin = new Cabin(address, city, state, description, title, bedCount, bathCount, maxOcc);
					modelCabin.setId(3);
			
				//TODO: PASS CABIN ID	
				
				// Get amenities
				
					// Variables
					
						boolean hasLake = false, hasRiver = false, hasPool = false, hasHotTub = false, hasWifi = false;
						boolean hasAirConditioning = false, hasWasherDryer = false, allowsPets = false, allowsSmoking = false;
					
					// Get user selected amenities	
						
						String[] checkBoxAmenities = request.getParameterValues("amenities");
						Amenities modelAmenities = new Amenities();
						
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
							
							modelAmenities = new Amenities(hasLake, hasRiver, hasPool, hasHotTub, hasWifi, hasAirConditioning,
													       hasWasherDryer, allowsPets, allowsSmoking);
							
						} // end of if
						
				// Call logic layer to update amenities + cabin
						
					group = LogicLayerImpl.updateCabin(modelCabin, modelAmenities);
					root.put("Group", group);
					
				// Set and process template
					
					String templateName = "EditCabinSuccess.ftl";
					processor.processTemplate(templateName, root, request, response);
					
			}
			
			private void addFeature(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				// Get cabin
				
					Cabin cabin = new Cabin();
					String cabinIdString = request.getParameter("cabinId");
					cabin.setId(Integer.parseInt(request.getParameter("cabinId")));
				
				// Create modelFeature
				
					Feature feature = new Feature();
					
				// Get user feature string and set
					
					String featureString = request.getParameter("featureString");
					feature.setFeatureString(featureString);
					feature.setCabin(cabin);
					
				// Store feature into database
					
					FeatureManager.delete(feature);
				
			}
			
			private void deleteFeature(HttpServletRequest request, HttpServletResponse response) throws CCException
			{	
				// Create modelFeature
					
					Feature feature = new Feature();
					
				// Get feature id
					
					String featureIdString = request.getParameter("featureId");
					feature.setId(Integer.parseInt(featureIdString));
					
				// delete feature
					
					FeatureManager.delete(feature);
			}
	
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
