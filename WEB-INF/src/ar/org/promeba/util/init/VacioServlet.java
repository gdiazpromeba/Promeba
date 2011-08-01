package ar.org.promeba.util.init;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
@SuppressWarnings("serial")
public class VacioServlet extends HttpServlet {
 
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
		
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/javascript");
//	    File file = null; 
//	    JSONObject job = new JSONObject();
//		
//		job.put("success", false);
//
//		String respuesta=job.toString();
//		respuesta = respuesta.replace("{", "&#123;");
//		respuesta = respuesta.replace("}", "&#125;");
//		out.write(respuesta);
	    //out.write("{success:false}");
		out.close();
 
	}
 
}