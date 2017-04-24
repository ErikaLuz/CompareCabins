package boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boundary.TemplateProcessor;

import test.TestUserManager;
import test.TestAmenitiesManager;
import test.TestCabinManager;
import test.TestFeatureManager;
import exception.CCException;

//import freemarker.template.Configuration;
//import freemarker.template.DefaultObjectWrapperBuilder;
//import freemarker.template.SimpleHash;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet 
{
	//VARIABLES
		private static final long serialVersionUID = 1L;	
		private String templateDir = "/WEB-INF/templates";
		private TemplateProcessor processor;
		
	    //@see HttpServlet#HttpServlet() 
			public TestServlet() 
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
				String userTest = request.getParameter("TestUser");
				
				if(userTest != null)
				{
					try {
						TestUserManager.main();
						TestCabinManager.main();
						TestFeatureManager.main();
						TestAmenitiesManager.main();
					} catch (CCException e) {
						e.printStackTrace();
					}
				}
			} // end of doGet
	
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
