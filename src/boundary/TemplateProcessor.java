package boundary;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TemplateProcessor {
	
	private String templateDir;
	private String templateName;
	private Configuration configuration;
	
	public TemplateProcessor(String templateDir, ServletContext servletContext) 
	{
		this.templateDir = templateDir;
		configuration = new Configuration(Configuration.VERSION_2_3_25);
		configuration.setServletContextForTemplateLoading(servletContext, templateDir);
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}
	
	public void processTemplate(String templateName, SimpleHash root, HttpServletRequest request, HttpServletResponse response) 
	{
		this.templateName  = templateName;
		Template template = null;
		try 
		{
			template = configuration.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try 
		{
			Writer out = response.getWriter();
			response.setContentType("text/html");
			template.process(root, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // end of processTemplate	
		
	/**
	 * @return the templateDir
	 */
	public String getTemplateDir() 
	{
		return templateDir;
	}

	/**
	 * @param templateDir the templateDir to set
	 */
	public void setTemplateDir(String templateDir) 
	{
		this.templateDir = templateDir;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() 
	{
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration()
	{
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
	}
	
}