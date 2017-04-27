package boundary;

import java.io.IOException;
import java.util.Calendar;

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
import object.Cabin;
import object.RentRecord;
import object.Review;

import persistence.UserManager;
import persistence.CabinManager;
import persistence.RentRecordManager;
import persistence.ReviewManager;

/**
 * Servlet implementation class ViewEditUserProfile
 */
@WebServlet("/ViewEditUserProfile")
public class ViewEditUserProfile extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
	    //@see HttpServlet#HttpServlet() 
			public ViewEditUserProfile() 
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
				String editUser = request.getParameter("editUser");
				String updateUser = request.getParameter("updateUser");
				
				if(viewUser != null)
					try {
						viewOrEdit(request, response, "view");
					} catch (CCException e) {
					
						e.printStackTrace();
					}
				else if (editUser != null)
					try {
						viewOrEdit(request, response, "edit");
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				else if (updateUser != null)
					try {
						update(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				
			} // end of doGet
			
			private void viewOrEdit(HttpServletRequest request, HttpServletResponse response, String viewOrEdit) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				HttpSession session = request.getSession();
				User userTracking = (User) session.getAttribute( "user");
		        root.put("username", userTracking.getUsername());
				
				// Place view or edit into root for ftl template
				
					if (viewOrEdit.equals("view")) root.put("viewOrEdit", "view");
					else if (viewOrEdit.equals("edit")) root.put("viewOrEdit", "edit");
					
				// Get user 
					
					User user = new User();
					String userId = request.getParameter("userId");
					user.setId(Integer.parseInt(userId));
				
					LogicLayerImpl.viewUserProfile(root, user);	
					
				// Set and process template
				
					String templateName = "UserProfile.ftl";
					processor.processTemplate(templateName, root, request, response);
					
					
				
/*				// Create dummy user to test this method - delete code later
				
					User user  = new User("testUsername", "testPassword", "Erika", "Luz", "testEmail");
					UserManager.store(user);
					
				// Create dummy cabin with rent record and review - delete code later
					
					// Create cabin, set user, store
					
						Cabin cabin = new Cabin();
						cabin.setAddress("userProfileCabin");
						cabin.setUser(user);
						
						CabinManager.store(cabin);
					
					// Create rent record, set cabin, store
						
						float z = 2;
						Calendar date = Calendar.getInstance();
					
						RentRecord rr = new RentRecord(z, date, date);
						rr.setCabin(cabin);
					
						RentRecord rr2 = new RentRecord(z, date, date);
						rr2.setCabin(cabin);
					
						RentRecordManager.store(rr);
						RentRecordManager.store(rr2);

					// Create review, set rent record, store
					
						Review review = new Review(5, "Great Cabin", "This is a great cabin with great location. I really enjoyed my stay");
						review.setRentRecord(rr);
						
						Review review2 = new Review(4, "Nice Location", "This cabin is very family friendly.");
						review2.setRentRecord(rr2);
					
						ReviewManager.store(review);
						ReviewManager.store(review2);
*/					
				// Call logic layer to return user and user data
					
					
/*				// Delete dummy values - delete code later

						ReviewManager.delete(review);
						ReviewManager.delete(review2);
						RentRecordManager.delete(rr);
						RentRecordManager.delete(rr2);
						CabinManager.delete(cabin);
						UserManager.delete(user);
*/				
				
			}
			
			private void update(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Place updateConfirmation into root for ftl template
				
					root.put("viewOrEdit", "updateConfirmation");
					
				// Create model user
					
					User user = new User();
					String userId = request.getParameter("userId");
					user.setId(Integer.parseInt(userId));
					
				// Retrieve new values
					
					String newUsername = request.getParameter("newUsername");
					String newPassword = request.getParameter("newPassword");
					String newFN = request.getParameter("newFN");
					String newLN = request.getParameter("newLN");
					String newEmail = request.getParameter("newEmail");
					
				// Set new values to model user
					
					if(!newUsername.equals("")) user.setUsername(newUsername);
					if(!newPassword.equals("")) user.setPassword(newPassword);
					if(!newFN.equals("")) user.setFirstName(newFN);
					if(!newLN.equals("")) user.setLastName(newLN);
					if(!newEmail.equals("")) user.setEmail(newEmail);
					
				// Call logic layer to update user
					
					user = LogicLayerImpl.updateUser(user);	
					
				// Place user into SimpleHash
					
					root.put("User", user);
					
				// Set and process template
					
					String templateName = "UserProfile.ftl";
					processor.processTemplate(templateName, root, request, response);					
					
					
/*					// DUMMY TEST CODE - create new user to edit - delete later
					
						User user = new User("testUsername", "testPassword", "Erika", "Luz", "testEmail");
						
						UserManager.store(user);
						
					if(!newUsername.equals("")) user.setUsername(newUsername);
					if(!newPassword.equals("")) user.setPassword(newPassword);
					if(!newFN.equals("")) user.setFirstName(newFN);
					if(!newLN.equals("")) user.setLastName(newLN);
					if(!newEmail.equals("")) user.setEmail(newEmail);	
*/				
				
				// Delete dummy user
				
//					UserManager.delete(user);
			}
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
