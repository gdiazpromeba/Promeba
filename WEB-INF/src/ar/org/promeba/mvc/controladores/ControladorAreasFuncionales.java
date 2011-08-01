package ar.org.promeba.mvc.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.AreaFuncional;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.AreaFuncionalSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorAreasFuncionales extends AbstractController {
	
	private AreaFuncionalSvc areaFuncionalSvc;


	public void setAreaFuncionalSvc(AreaFuncionalSvc areaFuncionalSvc) {
		this.areaFuncionalSvc = areaFuncionalSvc;
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
			List<AreaFuncional> beans=areaFuncionalSvc.selecciona(start, limit, nombre);
			int cuenta=areaFuncionalSvc.cuenta(nombre);
			for (AreaFuncional bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				fila.put("orden", bean.getOrden());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		}else if (uri.endsWith("inserta")){
			
			String nombre=request.getParameter("nombre");
			int orden=Integer.parseInt(request.getParameter("orden"));
			
			AreaFuncional bean=new AreaFuncional();
			bean.setNombre(nombre);
			bean.setOrden(orden);
			areaFuncionalSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("areaFuncionalId");
			String nombre=request.getParameter("nombre");
			int orden=Integer.parseInt(request.getParameter("orden"));

			AreaFuncional bean=new AreaFuncional();
			bean.setId(id);
			bean.setNombre(nombre);
			bean.setOrden(orden);
			
			areaFuncionalSvc.actualiza(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("borra")){
			
			String id=request.getParameter("id");
			areaFuncionalSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
		}
		
		else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
		}
	}

}
