package boundary;


import java.io.IOException;
import java.io.PrintWriter;
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
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import logic.LogicLayerImpl;
import logic.LogicLayerImplShep;
import object.Cabin;
import object.Amenities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Configuration   cfg = null;
    static  String          templateDir = "/WEB-INF/templates";
    static  String          resultTemplateName = "Search-Result.ftl";
  
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
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template    resultTemplate = null;
        List<Cabin> cabins = null;
        // load the template
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        } 
        catch (IOException e) {
            throw new ServletException( "Login.doGet: Can't load template in: " + templateDir + ": " + e.toString());
        }
                
        // set up the response writer
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter toClient = response.getWriter();
        
        // load html parameter values
        /*String[] stringAmenities = request.getParameterValues("amenities");
        for (int i = 0; i < stringAmenities.length; i++) {
            System.out.println(stringAmenities[i]); 
        }*/
        String hasLake = request.getParameter("hasLake");
        System.out.println(hasLake);
		String hasRiver = request.getParameter("hasRiver");
		String hasPool = request.getParameter("hasPool");
		String hasHotTub = request.getParameter("hasHotTub");
		String hasWifi = request.getParameter("hasWifi");
		String hasAirConditioning = request.getParameter("hasAirConditioning");
		String hasWasherDryer = request.getParameter("hasWasherDryer");
		String allowsPets = request.getParameter("allowsPets");
		String allowsSmoking = request.getParameter("allowsSmoking");
		System.out.println("test1335");
		System.out.println(hasRiver);
		Amenities amenities = new Amenities();
		if(hasLake != null)
			amenities.setHasLake(true);
		
		
        // business logic
        //try {
		
		System.out.println("test1");
			//cabins = LogicLayerImplShep.search(hasLake, hasRiver, hasPool, hasHotTub, hasWifi, hasAirConditioning, 
				//	hasWasherDryer, allowsPets, allowsSmoking);
			System.out.println("test2");
		/*} catch (CCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
        // create the data model
        Map<String, Object> root = new HashMap<String, Object>();
        
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
