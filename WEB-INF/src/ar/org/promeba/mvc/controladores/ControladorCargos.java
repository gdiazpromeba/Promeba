package ar.org.promeba.mvc.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.Cargo;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.CargoSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorCargos extends AbstractController {
	
	private CargoSvc svc;

	public void setSvc(CargoSvc svc) {
		this.svc = svc;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		
		if (uri.endsWith("selecciona")){
			
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String nombre=request.getParameter("nombre");
			
			
			JSONArray datos=new JSONArray();
			List<Cargo> beans=svc.selecciona(start, limit, nombre);
			int cuenta=svc.cuenta(nombre);
			for (Cargo bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		}else if (uri.endsWith("inserta")){
			
			String nombre=request.getParameter("nombre");
			
			Cargo bean=new Cargo();
			bean.setNombre(nombre);
			svc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("cargoId");
			String nombre=request.getParameter("nombre");

			Cargo bean=new Cargo();
			bean.setId(id);
			bean.setNombre(nombre);
			svc.actualiza(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("borra")){
			
			String id=request.getParameter("id");
			svc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
		
				
	  } else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
	  }
	}

}
