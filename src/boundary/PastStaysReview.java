package boundary;

import java.io.IOException;

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

import object.User;
import object.Cabin;
import object.RentRecord;
import object.Review;

import persistence.UserManager;
import persistence.CabinManager;
import persistence.RentRecordManager;
import persistence.ReviewManager;

/**
 * Servlet implementation class PastStays
 */
@WebServlet("/PastStaysReview")
public class PastStaysReview extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
	    //@see HttpServlet#HttpServlet() 
			public PastStaysReview() 
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
				String pastStays = request.getParameter("pastStays");
				String addReview = request.getParameter("addReview");
				String editReview = request.getParameter("editReview");
				
				if(pastStays != null)
					try {
						viewPastStays(request, response);
					} catch (CCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if(addReview != null)
				{
					
				}
				
				
			} // end of doGet
			
			private void viewPastStays(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// TODO: figure out how to pass user id 
				
//					User user = new User();
//					user.setId(id);
				
				// DUMMY CODE - delete later 
				
					// Create User 
				
						User user = new User("testUser", "testPass", "First", "Last", "Email");
						UserManager.store(user);
						
					// Create Cabin
						
						Cabin cabin = new Cabin("Address", "City", "State", "Description", "Title", 3 ,4, 6);
						
					
					
				
				String templateName = "PastStays.ftl";
				processor.processTemplate(templateName, root, request, response);
			}
	
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
