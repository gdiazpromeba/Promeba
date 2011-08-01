package ar.org.promeba.mvc.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import ar.org.promeba.util.json.JSONObject;

public class JSONView implements View {
	
	private JSONObject job;
	
	public JSONView(JSONObject job){
		this.job=job;
	}
 
	public void render(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
        writer.write(job.toString(2));
    }

	@Override
	public String getContentType() {
	    return "application/x-json;charset=UTF-8";
//		return "text/plain";
	}
    
}
