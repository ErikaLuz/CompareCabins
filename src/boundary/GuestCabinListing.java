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
import object.Group;

import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

/**
 * Servlet implementation class GuestCabinListing
 */
@WebServlet("/GuestCabinListing")
public class GuestCabinListing extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
    //@see HttpServlet#HttpServlet() 
		public GuestCabinListing() 
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
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			
			// Session Tracking
			
				HttpSession session = request.getSession();
				if(session.getAttribute("user") != null) {
				User user = (User) session.getAttribute( "user");
		        root.put("username", user.getUsername());
				}
				
			// Create Group object 
				
				Group group = new Group();
				
			// Create model cabin and set id 
				
				Cabin modelCabin = new Cabin();
				modelCabin.setId(Integer.parseInt(request.getParameter("cabinId")));
				
			// Call logic layer to get information about cabin
				
				try{
					group = LogicLayerImpl.cabinListing( modelCabin );
				}catch (CCException e)
				{
					e.printStackTrace();
				}
				
			// If statements for Hash
				
				if(group.getUser() != null) root.put("Usercheck", "notNull");
				else root.put("UserCheck", "null");
				
				if(group.getAmenities() != null) root.put("AmenitiesCheck", "notNull");
				else root.put("AmenitiesCheck", "null");
			
				if(group.getCabinPictureList() != null)
				{
					root.put("CPCheck", "notNull");
					
					if(group.getCabinPicture() != null) root.put("PriorityCheck", "notNull"); 
					else root.put("PriorityCheck", "null");
				}
				else root.put("CPCheck", "null");
				
				if(group.getFeatureList() != null) root.put("FeaturesCheck", "notNull");
				else root.put("FeaturesCheck", "null");
				
				if(group.getAvailabilityList() != null)	root.put("AvailabilitiesCheck", "notNull");
				else root.put("AvailabilitiesCheck", "null");
				
				if(group.getReviewList() != null) root.put("ReviewsCheck", "notNull");
				else root.put("ReviewsCheck", "null");
			
			// Place group into root
				
				root.put("Group", group);
				
			// Set and process template
		
				String templateName = "GuestCabinListing.ftl";
				processor.processTemplate(templateName, root, request, response);

		} // end of doGet

	//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doGet(request, response);
		} //end of doPost
}
