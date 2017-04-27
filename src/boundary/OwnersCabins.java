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

import logic.LogicLayerImpl;

import object.User;
import object.Group;

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
			// Create DefaultObjectWrapperBuilder and SimpleHash
			
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
			// Session Tracking
			
				HttpSession  session = request.getSession();
				User user = (User) session.getAttribute("user");
				root.put("username", user.getUsername());
			
			// Create group object
				
				List<Group> groups = new LinkedList<Group>();
			
			// Call logic layer to get owner's cabins	
			
				try{
					groups = LogicLayerImpl.ownersCabins( user );
				}catch (CCException e)
				{
					e.printStackTrace();
				}
				
			// Place group list into root
				
				root.put("Groups", groups);
			
			// Set and process template
			
				String templateName = "OwnersCabins.ftl";
				processor.processTemplate(templateName, root, request, response);
	
			}
	
	//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doGet(request, response);
		} //end of doPost
}