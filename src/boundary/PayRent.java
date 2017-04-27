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

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.LogicLayerImplAlt;
import object.Availability;
import object.User;

/**
 * Servlet implementation class PayRent
 */
@WebServlet("/PayRent")
public class PayRent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayRent() {
        super();
        // TODO Auto-generated constructor stub
    }
	//@see Servlet#init(ServletConfig) 
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
		processor = new TemplateProcessor(templateDir, getServletContext());
	} //end of init

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		HttpSession session;
		String templateName = "PayRent.ftl";
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String cabinId = request.getParameter("hiddenId");
		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");



		
		if (user == null)
		{
			templateName = "NeedToLogin.ftl";
			processor.processTemplate(templateName, root, request, response);
		} else {
			root.put("cabinId", cabinId);
			root.put("start", startDateString);
			root.put("end", endDateString);
			processor.processTemplate(templateName, root, request, response);
		}
		

		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
