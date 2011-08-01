package ar.org.promeba.util.init;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;

import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.util.json.JSONException;
import ar.org.promeba.util.json.JSONObject;
 
 
public class FileUploadServlet extends HttpServlet {
	private static final String TMP_DIR_PATH = "c:\\tmp";
	private File tmpDir;
	private static String DESTINATION_DIR_PATH;
	private File destinationDir;
 
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		DESTINATION_DIR_PATH=config.getServletContext().getRealPath("/archivos");
		tmpDir = new File(TMP_DIR_PATH);
		
		if(!tmpDir.isDirectory()) {
			throw new ServletException(TMP_DIR_PATH + " is not a directory");
		}
		
		destinationDir = new File(DESTINATION_DIR_PATH);
		if(!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
		}
 
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		/*
		 *Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
		/*
		 * Set the temporary directory to store the uploaded files of size above threshold.
		 */
		fileItemFactory.setRepository(tmpDir);
 
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/javascript");
	    File file = null; 
	    JSONObject job = new JSONObject();
		
		try {
			/*
			 * Parse the request
			 */
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				/*
				 * Handle Form Fields.
				 */
				if(item.isFormField()) {
//					out.println("File Name = "+item.getFieldName()+", Value = "+item.getString());
				} else {
					//Handle Uploaded files.
////					out.println("Field Name = "+item.getFieldName()+
//						", File Name = "+item.getName()+
//						", Content type = "+item.getContentType()+
//						", File Size = "+item.getSize());
//					/*
//					 * Write file to the ultimate location.
//					 */
					file = new File(destinationDir,item.getName());
					item.write(file);
				}
//				out.close();
			}
			
			
			job.put("success", true);
			//job.put("archivo", file.getCanonicalPath());
			job.put("archivo", file.getName());
			
			
			
			
			
		}catch(Exception ex) {
			
			try {

				job.put("success", false);
				job.put("error", ex.getMessage());
			} catch (JSONException e) {
				log(e.getMessage());
				e.printStackTrace();
			}
			
		}finally{
			String respuesta=job.toString();
			respuesta = respuesta.replace("{", "&#123;");
			respuesta = respuesta.replace("}", "&#125;");
			out.write(respuesta);
			out.close();
		}
 
	}
 
}