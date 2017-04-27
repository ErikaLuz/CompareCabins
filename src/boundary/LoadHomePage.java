package boundary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import object.User;

/**
 * Servlet implementation class LoadHomePage
 */
@WebServlet("/LoadHomePage")
public class LoadHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Configuration   cfg = null;
    static  String          templateDir = "/WEB-INF/templates";
    static  String          resultTemplateName = "index.ftl";

    @Override
    public void init() { 
        
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        // Specify the source where the template files come from.
        cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadHomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template    resultTemplate = null;
        // load the template
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        } 
        catch (IOException e) {
            throw new ServletException( "LoadHomePage.doGet: Can't load template in: " + templateDir + ": " + e.toString());
        }
                
        
        
        // set up the response writer
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter toClient = response.getWriter();

   


        // create the data model
        Map<String, Object> root = new HashMap<String, Object>();
        
        // Session Tracking
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
		User user = (User) session.getAttribute( "user");
	    root.put("username", user.getUsername());
		}
        // connect template with data model
        try {
            resultTemplate.process( root, toClient );
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
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