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

import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

/**
 * Servlet implementation class GuestCabinListing
 */
@WebServlet("/RentCabin")
public class RentCabin extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
    //@see HttpServlet#HttpServlet() 
		public RentCabin() 
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
	        
	        // Get CabinId	
	        String cabinIdString = request.getParameter("cabinId");
				
			// Create model cabin and set id 
				
				Cabin modelCabin = new Cabin();
				modelCabin.setId(Integer.parseInt(cabinIdString));
				
				try{
					LogicLayerImpl.cabinListing(root, modelCabin);
				}catch (CCException e)
				{
					e.printStackTrace();
				}
			
			// Set and process template
		
				String templateName = "RentCabin.ftl";
				processor.processTemplate(templateName, root, request, response);

		} // end of doGet

	//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			doGet(request, response);
		} //end of doPost
}
