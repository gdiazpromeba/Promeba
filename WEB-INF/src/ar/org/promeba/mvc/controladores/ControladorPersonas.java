package ar.org.promeba.mvc.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.Persona;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.PersonaSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorPersonas extends AbstractController {
	
	private PersonaSvc svc;

	public void setSvc(PersonaSvc svc) {
		this.svc = svc;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		if (uri.endsWith("selecciona")){
			
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			boolean fisica=Boolean.parseBoolean(request.getParameter("fisica"));
			boolean juridica=Boolean.parseBoolean(request.getParameter("juridica"));
			
			String denominacion=request.getParameter("denominacion");
			
			JSONArray datos=new JSONArray();
			List<Persona> beans=svc.selecciona(start, limit, denominacion, fisica, juridica);
			for (Persona bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("denominacion", bean.getNombre());
				fila.put("tipo", bean.getTipo());
				datos.put(fila);
			}
			int cuenta=svc.cuenta(denominacion, fisica, juridica);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
		}
	}

}
