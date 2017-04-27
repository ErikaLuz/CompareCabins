package boundary;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadFile")
@MultipartConfig(maxFileSize = 16177216)
public class Upload extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploadPath = "E:/data";

		for(Part p : req.getParts()){
			p.write(uploadPath + File.separator + getFileName(p));	
		}
		
		resp.sendRedirect("success.html");
	}

	//extract file name from header
	private String getFileName(final Part part) {
	    String header = part.getHeader("content-disposition");

	    for (String content : header.split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    
	    return null;
	}
}