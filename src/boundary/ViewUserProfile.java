package boundary;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boundary.TemplateProcessor;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

import exception.CCException;

import logic.LogicLayerImpl;

import object.User;
import object.Cabin;
import object.RentRecord;
import object.Review;

import persistence.UserManager;
import persistence.CabinManager;
import persistence.RentRecordManager;
import persistence.ReviewManager;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ViewUserProfile")
public class ViewUserProfile extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
	    //@see HttpServlet#HttpServlet() 
			public ViewUserProfile() 
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
				String viewUser = request.getParameter("viewUser");
				
				if(viewUser != null)
				{
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
					
					// Create dummy user to test this method - delete later
					
						User user  = new User("testUsername", "testPassword", "Erika", "Luz", "testEmail");
						
						try {
							UserManager.store(user);
						} catch (CCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					// Create dummy cabin with rent record and review
						
						Cabin cabin = new Cabin();
						cabin.setAddress("userProfileCabin");
						cabin.setUser(user);
						
						try {
							CabinManager.store(cabin);
						} catch (CCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						float z = 2;
						Calendar date = Calendar.getInstance();
						
						RentRecord rr = new RentRecord(z, date, date);
						rr.setCabin(cabin);
						rr.setUser(user);
						
						RentRecord rr2 = new RentRecord(z, date, date);
						rr2.setUser(user);
						rr2.setCabin(cabin);
						
						try {
							RentRecordManager.store(rr);
							RentRecordManager.store(rr2);
						} catch (CCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Review review = new Review(5, "Great Cabin", "This is a great cabin with great location. I really enjoyed my stay");
						review.setRentRecord(rr);
						
						Review review2 = new Review(4, "Nice Location", "This cabin is very family friendly.");
						review2.setRentRecord(rr2);
						
						try {
							ReviewManager.store(review);
							ReviewManager.store(review2);
						} catch (CCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					// Call logic layer to return user
						
						// TODO: somehow get the userid then create model user with id
						
						try {
							LogicLayerImpl.viewUserProfile(root, user);
						} catch (CCException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						// Delete dummy values

						try {
							ReviewManager.delete(review);
							ReviewManager.delete(review2);
							RentRecordManager.delete(rr);
							RentRecordManager.delete(rr2);
							CabinManager.delete(cabin);
							UserManager.delete(user);
						} catch (CCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					String templateName = "UserProfile.ftl";
					processor.processTemplate(templateName, root, request, response);
				}
			} // end of doGet
	
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
