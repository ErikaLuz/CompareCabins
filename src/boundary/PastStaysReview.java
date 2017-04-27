package boundary;

import java.io.IOException;

import java.util.List;
import java.util.LinkedList;

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
import logic.LogicLayerImpl;
import exception.CCException;

import object.User;
import object.RentRecord;
import object.Review;
import object.Group;

/**
 * Servlet implementation class PastStaysReview
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
				String submitReview = request.getParameter("submitReview");
				
				if(pastStays != null)
					try {
						viewPastStays(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				else if(addReview != null)
				{
					try {
						goToReview(request, response);
					} catch (CCException e) {
					
						e.printStackTrace();
					}
				}
				else if(submitReview != null)
				{
					try {
						addReview(request,response);
					} catch (CCException e) {
					
						e.printStackTrace();
					}
				}
				
				
			} // end of doGet
			
			
			/**
			 * Retrieves user's past stays' info
			 * 
			 * @param request	HttpServletRequest
			 * @param response	HttpServletResponse
			 * @throws CCException
			 */
			private void viewPastStays(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				// Create DefaultObjectWrapperBuiler and SimpleHash
				
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute( "user");
			        root.put("username", user.getUsername());
			        
			    // Create Group List
			        
			        List<Group> groups = new LinkedList<Group>();
					
				// Call logic layer
					
					groups = LogicLayerImpl.pastStays( user );
				
				// Place groups + user into root
					
					root.put("Group", groups);
					root.put("User", user);
					
				// Set and process template
			
					String templateName = "PastStays.ftl";
					processor.processTemplate(templateName, root, request, response);
				
			} // end of viewPastStays
			
			
			/**
			 * Takes user to page where they can add a review to a cabin they've stayed at
			 * 
			 * @param request	HttpServletRequest
			 * @param response	HttpServletResponse
			 * @throws CCException
			 */
			private void goToReview(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				// Create DefaultObjectWrapperBuilder and SimpleHash
				
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute( "user");
			        root.put("username", user.getUsername());
				
				// Retrieve the rent record id
				
					RentRecord modelRR = new RentRecord();
					modelRR.setId(Integer.parseInt(request.getParameter("rentRecordId")));
					
				// Create group object
					
					Group group = new Group();
					
				// Call the logic layer
					
					group = LogicLayerImpl.goToReview( modelRR );
					
				// Place group into root
					
					root.put("Group", group);
					
				// Set and process template
				
					String templateName = "AddReview.ftl";
					processor.processTemplate(templateName, root, request, response);
					
			} // end of goToReview
			
			
			/**
			 * Adds a review to the specified rent record
			 * 
			 * @param request HttpSerlvetRequest
			 * @param response HttpServletResponse
			 * @throws CCException
			 */
			private void addReview(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				// Create DefaultObjectWrapperBuilder and SimpleHash 
				
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
				
				// Session Tracking
				
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute( "user");
			        root.put("username", user.getUsername());
				
				// Get Review values
				
					int intNumStar = 0;
					String numStar = request.getParameter("numStars");
					if(!numStar.equals("") && numStar != null) intNumStar = Integer.parseInt(numStar);
					
					String title = null;
					if(!request.getParameter("title").equals("")) title = request.getParameter("title");
					
					String review = null;
					if(!request.getParameter("review").equals("")) review = request.getParameter("review");
					
				// Create model review object
				
					Review modelReview = new Review(intNumStar, title, review);
				
				// Get rent record id
				
					String rrId = request.getParameter("rentRecordId");		
					RentRecord rr = new RentRecord();
					rr.setId(Integer.parseInt(rrId));
					
				// Create group object
					
					Group group = new Group();
				
				// Call logic layer and pass rent record and model review
				
					group = LogicLayerImpl.addReview(rr, modelReview);
					
				// Place group into SimpleHash
					
					root.put("Group", group);
				
				// Set and process template
				
					String templateName = "AddReviewSuccess.ftl";
					processor.processTemplate(templateName, root, request, response);
					
			} // end of addReview
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
