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

import exception.CCException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.LogicLayerImplAlt;
import object.Availability;
import object.User;

/**
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pay() {
        super();
        // TODO Auto-generated constructor stub
    }
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
		String templateName = "Receipt.ftl";
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		int cabinId = Integer.parseInt(request.getParameter("cabinId"));
		String startDateString = request.getParameter("start");
		String endDateString = request.getParameter("end");
		
		Availability start;
		if(startDateString.length() > 0)
			start = parseDateString( startDateString );
		else
			start = null;
		Availability end;
		if(endDateString.length() > 0)
			end = parseDateString( endDateString );
		else
			end = null;
		try{
		LogicLayerImplAlt.rentCabin(start.getDate(), end.getDate(), cabinId, user);
		} catch (CCException e) {
			e.printStackTrace();
		}
		
		root.put("username", user.getUsername() );
		
		processor.processTemplate(templateName, root, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private Availability parseDateString( String dateString )
	{
		String year = dateString.substring(0,4);
		String month = dateString.substring(5,7);
		String day = dateString.substring(8,10);
		
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month);
		monthInt--;
		int dayInt = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yearInt, monthInt, dayInt);
		Availability availability = new Availability();
		availability.setDate( cal );
		
		return availability;
	}

}
