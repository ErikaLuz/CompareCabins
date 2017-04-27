package boundary;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boundary.TemplateProcessor;
import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.LogicLayerImpl;

import object.Cabin;

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
				String prepareEdit = request.getParameter("prepareEdit");
				String submitEdit = request.getParameter("submitEdit");
				
				if(prepareEdit != null)
					try {
						prepareEdit(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
				if(submitEdit != null)	
					try {
						submitEdit(request, response);
					} catch (CCException e) {
						
						e.printStackTrace();
					}	
				
			} // end of doGet
			
			private void prepareEdit(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				// Get cabin 
				
					Cabin modelCabin = new Cabin();
					modelCabin.setId(Integer.parseInt(request.getParameter("cabinId")));
				
				// Call logic layer to prepare cabin editing
				
					try {
						LogicLayerImpl.prepareEditCabin(root, modelCabin);
					} catch (CCException e) {
						
						e.printStackTrace();
					}
					
				// Set and process template
				
				String templateName = "EditCabin.ftl";
				processor.processTemplate(templateName, root, request, response);
			}
			
			private void submitEdit(HttpServletRequest request, HttpServletResponse response) throws CCException
			{
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				
				String templateName = "EditCabin.ftl";
				processor.processTemplate(templateName, root, request, response);
			}
	
		
		//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				doGet(request, response);
			} //end of doPost
}
