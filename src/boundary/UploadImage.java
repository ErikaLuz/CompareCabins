package boundary;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import exception.CCException;
import object.CabinPicture;
import persistence.CabinPictureManager;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
@MultipartConfig
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Part part = request.getPart("picture");
		//String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		File uploads = new File( System.getProperty("jboss.server.data.dir"), "uploads");
		
		//String path = getRealPath("/pics");
		//File dir = new File(path);
				
		File file = new File( uploads, "cutePuppy.jpeg");

		try (InputStream input = part.getInputStream()) {
		    Files.copy(input, file.toPath() );
		}
		CabinPicture cabinPicture = new CabinPicture();
		cabinPicture.setFilePath( file.getAbsolutePath() );
		try {
			CabinPictureManager.store( cabinPicture );
		} catch (CCException e) {
			e.printStackTrace();
		}
	}

}
