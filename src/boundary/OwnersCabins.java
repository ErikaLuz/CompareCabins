package boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boundary.TemplateProcessor;

import logic.LogicLayerImpl;

import object.Cabin;
import object.User;
import persistence.CabinManager;
import persistence.FeatureManager;
import persistence.UserManager;
import object.Feature;

import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

/**
 * Servlet implementation class OwnersCabins
 */
@WebServlet("/OwnersCabins")
public class OwnersCabins extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
    //@see HttpServlet#HttpServlet() 
		public OwnersCabins() 
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
			String userCabinListing = request.getParameter("userCabinListing");
			
			if(userCabinListing != null)
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Call cabin listing code
				
					String userIdString = request.getParameter("userId");
					User user = new User();
					user.setId(Integer.parseInt(userIdString));
				
				// Call logic layer to get owner's cabins	
				
					try{
						LogicLayerImpl.userCabinListings(root, user);
					}catch (CCException e)
					{
						e.printStackTrace();
					}
				
				// Set and process template
				
					String templateName = "UserCabinListing.ftl";
					processor.processTemplate(templateName, root, request, response);
				
/*				// For testing purposes - delete later: make dummy user with dummy cabins
				
					User user = new User("Cabin", "Listing", "firstName", "lastName", "email");
					
					try {
						LogicLayerImpl.register(user);
					} catch (CCException e1) {

						e1.printStackTrace();
					}
					
					Cabin cabin = new Cabin("address", "city", "state", "Great cabin in the mountains", "Great Cabin", 2, 3, 6);
					cabin.setUser(user);
					
					Cabin cabin2 = new Cabin("Street", "Athens" ,"Georgia", "cabin located right inside of snelling", "Cabin in UGA", 2, 6, 100);
					cabin2.setUser(user);
					
					Cabin cabin3 = new Cabin("science library", "UGA", "Georgia", "this cabin is great", "Cool cabin" ,5, 79, 4);
					cabin3.setUser(user);
					
					try {
						CabinManager.store(cabin);
						CabinManager.store(cabin2);
						CabinManager.store(cabin3);
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
					
					Feature feature2 = new Feature("This cabin sucks, don't rent it from me");
					feature.setCabin(cabin2);
					
					try {
						FeatureManager.store(feature2);
					} catch (CCException e1) {
						e1.printStackTrace();
					}
*/				
			
/*				
				// Delete dummy 
				
					try{
						FeatureManager.delete(feature);
						FeatureManager.delete(feature2);
						CabinManager.delete(cabin);
						CabinManager.delete(cabin2);
						CabinManager.delete(cabin3);
						UserManager.delete(user);
					}catch (CCException e)
					{
						e.printStackTrace();
					}*/
			}
		
		} // end of doGet

	
	//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doGet(request, response);
		} //end of doPost
}