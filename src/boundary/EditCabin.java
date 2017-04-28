package boundary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boundary.TemplateProcessor;
import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

import logic.LogicLayerImpl;

import object.CabinPicture;
import persistence.CabinPictureManager;

import object.CabinPicture;
import object.Availability;
import object.Group;
import object.User;
import object.Cabin;
import object.Feature;
import persistence.CabinManager;
import persistence.FeatureManager;
import object.Amenities;
import persistence.AmenitiesManager;
import persistence.AvailabilityManager;

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
				String addAvailability = request.getParameter("addAvailability");
				
				String prepareEdit = request.getParameter("prepareEdit");
				String submitEdit = request.getParameter("submitEdit");
				
//				String addPhoto = request.getParameter("addPhoto");
				String deletePhoto = request.getParameter("deletePhoto");
				
				String addFeature = request.getParameter("addFeature");
				String editFeature = request.getParameter("editFeature");
				String deleteFeature = request.getParameter("deleteFeature");
				String submitEditFeature = request.getParameter("submitEditedFeature");
				
				
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
				else if(deleteFeature != null)
					try {
						deleteFeature(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				else if(addFeature != null)
					try {
						addFeature(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				else if(editFeature != null)
					try {
						editFeature(request, response);
					} catch (CCException e) {
				
						e.printStackTrace();
					}
				else if(submitEditFeature != null)
					try {
						submitEditedFeature(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				else if(addAvailability != null)
					try {
						addAvailability(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				else if(deletePhoto != null)
					try {
						deletePhoto(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
							
			} // end of doGet
			
			private void deletePhoto(HttpServletRequest request, HttpServletResponse response) throws CCException
			{

				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute( "user");
			        root.put("username", user.getUsername());
				
				// Retrieve cabin picture id
				
					CabinPicture cp = new CabinPicture();
					int cpId = Integer.parseInt(request.getParameter("cpId"));
					cp.setId(cpId);
					
				// Retrieve cabin picture from database 
					
					List<CabinPicture> cps = CabinPictureManager.restore(cp);
					
					if(cps.size() != 1) System.out.println("ERROR: incorrect cabin pictures found");
					else cp = cps.get(0);
					root.put("CP", cp);	
					
				// Retrieve cabin from cabin picture + all of cabin's pictures
					
					Cabin cabin = CabinPictureManager.restoreCabinFromCabinPicture(cp);
					List<CabinPicture> cps2 = CabinManager.restoreCabinPicturesFromCabin(cabin);
					
				// Delete photo various cases
					
					if(cps2.size() == 1)
					{
						// Delete picture and set default picture to cabin 
						CabinPictureManager.delete(cp);
						
						CabinPicture defaultPicture = new CabinPicture();
						defaultPicture.setCabin(cabin);
						defaultPicture.setFilePath("http://placehold.it/600x400");
						defaultPicture.setPriority(1);
						
						CabinPictureManager.store(defaultPicture);
						
					}
					else if(cp.getPriority() == 1 && cps2.size() > 1)
					{
						// Delete picture and make another picture the priority picture
						
							CabinPictureManager.delete(cp);
						
						// Retrieve cabin pictures again and assign next picture priority
						
							List<CabinPicture> cps3 = CabinManager.restoreCabinPicturesFromCabin(cabin);
							cps3.get(0).setPriority(1);
						
					}
					else CabinPictureManager.delete(cp);
					
					// Set and process template
					
						root.put("add", "picture");
						String templateName = "EditCabinConfirmation.ftl";
						processor.processTemplate(templateName, root, request, response);
					
			} // end of deletePhoto
			
			private void addAvailability(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				
				// create model availability
				
				Availability availability = new Availability();
				// cabin - price - date
				
				// get cabin id from ftl file and restore
				
					Cabin cabin = new Cabin();
					cabin.setId(Integer.parseInt(request.getParameter("cabinId")));
					
					List<Cabin> cabins = CabinManager.restore(cabin);
					
					if(cabins.size() != 1) System.out.println("ERROR: wrong cabin(s) found");
					else cabin = cabins.get(0);
					
				availability.setCabin(cabin);
				
				String stringPrice = request.getParameter("cabinPrice");
				availability.setPrice(Float.parseFloat(stringPrice));
				
				String dateString = request.getParameter("cabinAvailability");
				
				String year = dateString.substring(0,4);
				String month = dateString.substring(5,7);
				String day = dateString.substring(8,10);
				
				int yearInt = Integer.parseInt(year);
				int monthInt = Integer.parseInt(month);
				int dayInt = Integer.parseInt(day);
				
				Calendar cal = Calendar.getInstance();
				cal.set(yearInt, monthInt, dayInt);
				availability.setDate( cal );
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");		
				String date = sdf.format(availability.getDate().getTime());
				root.put("Date", date);
				
				// Store availability 
				
				AvailabilityManager.store(availability);
				
				root.put("Availability", availability);
				root.put("add", "availability");
				String templateName = "EditCabinConfirmation.ftl";
				processor.processTemplate(templateName, root, request, response);
				
			} // end of addAvailability

			
			private void prepareEdit(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				
				// Get cabin 
				
					Cabin modelCabin = new Cabin();
					modelCabin.setId(Integer.parseInt(request.getParameter("cabinId")));
				
				// Create group
				
					Group group = new Group();
					
				// Call logic layer to prepare cabin editing
				
					group = LogicLayerImpl.prepareEditCabin( modelCabin );
					
				// Place group into root
					
					root.put("Group", group);
					
				// Set and process template
				
					String templateName = "EditCabin.ftl";
					processor.processTemplate(templateName, root, request, response);
					
			} // end of prepareEdit
			
			private void submitEdit(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());	
				
				// Session Tracking
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				
				// Create Group
				
					Group group = new Group();
					
				// Get Cabin id 
					
					Cabin modelCabin = new Cabin();
					modelCabin.setId(Integer.parseInt(request.getParameter("cabinId")));
			
				// Restore original cabin from database
					
					Cabin cabin = new Cabin();
					List<Cabin> cabins = CabinManager.restore(modelCabin);
					
					if(cabins.size() != 1) System.out.println("ERROR: wrong cabin(s) found editcabin.java");
					else cabin = cabins.get(0);
					
				// Create model cabin object
				
					// Get cabin parameters
							
						String address = cabin.getAddress(); 
						if(!request.getParameter("newAddress").equals("") && !request.getParameter("newAddress").equals(cabin.getAddress())) 
							address = request.getParameter("newAddress");
						
						String city = cabin.getCity();
						if(!request.getParameter("newCity").equals("") && !request.getParameter("newCity").equals(cabin.getCity())) 
							city = request.getParameter("newCity");		
						
						String state = cabin.getState();
						if(!request.getParameter("newState").equals("") && !request.getParameter("newState").equals(cabin.getState())) 
							state = request.getParameter("newState");
						
						String description = cabin.getDescription();
						if(!request.getParameter("newDescription").equals("") && !request.getParameter("newDescription").equals(cabin.getDescription())) 
							description = request.getParameter("newDescription");
						
						String title = cabin.getTitle();
						if(!request.getParameter("newTitle").equals("") && !request.getParameter("newTitle").equals(cabin.getTitle())) 
							title = request.getParameter("newTitle");
						
						int bedCount = cabin.getBedroomCount();
						String bed = request.getParameter("newBedCount");
						if(!bed.equals("") && Integer.parseInt(bed) != cabin.getBedroomCount()) 
							bedCount = Integer.parseInt(bed);
					
						float bathCount = cabin.getBathCount();
						String bath = request.getParameter("newBathCount");
						if(!bath.equals("") && Integer.parseInt(bath) != cabin.getBathCount()) 
							bathCount = Integer.parseInt(bath);
						
						int maxOcc = cabin.getMaxOccupancy();
						String max = request.getParameter("newMaxOcc");
						if(!max.equals("") && Integer.parseInt(max) != cabin.getMaxOccupancy()) 
							maxOcc = Integer.parseInt(max);
					    
					    modelCabin.setAddress(address);
					    modelCabin.setCity(city);
					    modelCabin.setState(state);
					    modelCabin.setDescription(description);
					    modelCabin.setTitle(title);
					    modelCabin.setBedroomCount(bedCount);
					    modelCabin.setBathCount(bathCount);
					    modelCabin.setMaxOccupancy(maxOcc);	
				
				// Get original amenities from database
					    
					Amenities amenities = CabinManager.restoreAmenitiesFromCabin(cabin);
				
					// Variables
					
						boolean hasLake = false, hasRiver = false, hasPool = false, hasHotTub = false, hasWifi = false;
						boolean hasAirConditioning = false, hasWasherDryer = false, allowsPets = false, allowsSmoking = false;
					
						hasLake = amenities.isHasLake();
						hasRiver = amenities.isHasRiver();
						hasPool = amenities.isHasPool();
						hasHotTub = amenities.isHasHotTub();
						hasWifi = amenities.isHasWifi();
						hasAirConditioning = amenities.isHasAirConditioning();
						hasWasherDryer = amenities.isHasWasherDryer();
						allowsPets = amenities.isAllowsPets();
						allowsSmoking = amenities.isAllowsSmoking();
						
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
					
					String templateName = "EditCabin.ftl";
					processor.processTemplate(templateName, root, request, response);
					
			} // end of submitEdit
			
			private void addFeature(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				
				// Get cabin
				
					Cabin cabin = new Cabin();
					cabin.setId(Integer.parseInt(request.getParameter("cabinId")));
				
				// Create modelFeature
				
					Feature feature = new Feature();
					
				// Get user feature string and set
					
					String featureString = request.getParameter("newFeature");
					feature.setFeatureString(featureString);
					feature.setCabin(cabin);
					
				// Store feature into database
					
					FeatureManager.store(feature);
					
				// Set and process template
					
					root.put("Feature", feature);
					root.put("add", "add");
					String templateName = "EditCabinConfirmation.ftl";
					processor.processTemplate(templateName, root, request, response);
				
			} // end of addFeature
			
			private void deleteFeature(HttpServletRequest request, HttpServletResponse response) throws CCException
			{	
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				
				// Create modelFeature
					
					Feature modelFeature = new Feature();
					
				// Get feature id
					
					String featureIdString = request.getParameter("featureId");
					modelFeature.setId(Integer.parseInt(featureIdString));
			
				// Retrieve feature from the database
					
					List<Feature> features = FeatureManager.restore(modelFeature);
					Feature feature = new Feature();
					
					if(features.size() != 1) System.out.println("ERROR: wrong feature(s) found");
					else feature = features.get(0);
					
					root.put("Feature", feature);
					
				// delete feature
					
					FeatureManager.delete(feature);

					root.put("add", "delete");
					String templateName = "EditCabinConfirmation.ftl";
					processor.processTemplate(templateName, root, request, response);
					
			} // end of deleteFeature
			
			private void editFeature(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				System.out.println("enter edit feature");
				
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				
				Feature modelFeature = new Feature();
				modelFeature.setId(Integer.parseInt(request.getParameter("featureId")));
				
				List<Feature> features = FeatureManager.restore(modelFeature);
				Feature feature = new Feature();
				
				if(features.size() != 1) System.out.println("ERROR: wrong feature(s) found");
				else feature = features.get(0);
				
				root.put("Feature", feature);
				
				root.put("add", "edit");
				String templateName = "EditCabinConfirmation.ftl";
				processor.processTemplate(templateName, root, request, response);
				
			} // end of editFeature
	
			private void submitEditedFeature(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				
				
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				
				Feature modelFeature = new Feature();
				modelFeature.setId(Integer.parseInt(request.getParameter("featureId")));
				
				modelFeature.setId(5);
				
				List<Feature> features = FeatureManager.restore(modelFeature);
				Feature feature = new Feature();
				
				if(features.size() != 1) System.out.println("ERROR: wrong feature(s) found");
				else feature = features.get(0);
				
				String newFeatureString = request.getParameter("editFeatureString");
				feature.setFeatureString(newFeatureString);
				
				FeatureManager.store(feature);
				
				root.put("Feature", feature);
				
				root.put("add", "submitEditFeature");
				String templateName = "EditCabinConfirmation.ftl";
				processor.processTemplate(templateName, root, request, response);
			}
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
