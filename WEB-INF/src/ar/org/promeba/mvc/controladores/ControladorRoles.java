package ar.org.promeba.mvc.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.AreaFuncionalXRol;
import ar.org.promeba.beans.Rol;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.AreasFuncionalesXRolSvc;
import ar.org.promeba.svc.RolSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorRoles extends AbstractController {
	
	private RolSvc rolSvc;
	private AreasFuncionalesXRolSvc areasFuncionalesXRolSvc; 

	public void setRolSvc(RolSvc rolSvc) {
		this.rolSvc = rolSvc;
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
			List<Rol> beans=rolSvc.selecciona(start, limit, nombre, null);
			int cuenta=rolSvc.cuenta(nombre, null);
			for (Rol rol : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", rol.getId());
				fila.put("nombre", rol.getNombre());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		}else if (uri.endsWith("inserta")){
			
			String nombre=request.getParameter("nombre");
			
			Rol rol=new Rol();
			rol.setNombre(nombre);
			rolSvc.inserta(rol);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", rol.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("rolId");
			String nombre=request.getParameter("nombre");

			Rol rol=new Rol();
			rol.setId(id);
			rol.setNombre(nombre);
			rol.setHabilitado(true);
			rolSvc.actualiza(rol);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("borra")){
			
			String id=request.getParameter("id");
			rolSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
		
		}else if (uri.endsWith("areasFuncionalesXRolVisibles")){
				
				int start=Integer.parseInt(request.getParameter("start"));
				int limit=Integer.parseInt(request.getParameter("limit"));
				String rolId=request.getParameter("rolId");
				
				
				JSONArray datos=new JSONArray();
				List<AreaFuncionalXRol> beans=areasFuncionalesXRolSvc.areasFuncionalesXRolVisibles(start, limit, rolId);
				int cuenta=areasFuncionalesXRolSvc.cuentaAreasFuncionalesXRolVisibles(rolId);
				for (AreaFuncionalXRol bean : beans) {
					Map<String, Object> fila=new HashMap<String, Object>();
					fila.put("id", bean.getId());
					fila.put("rolId", bean.getRolId());
					fila.put("rolNombre", bean.getRolNombre());
					fila.put("areaId", bean.getAreaId());
					fila.put("areaNombre", bean.getAreaNombre());
					datos.put(fila);
				}
				JSONObject job=new JSONObject();
				job.put("total", cuenta);
				job.put("data", datos);
				
				ModelAndView mav=new ModelAndView(new JSONView(job));
				
				return mav;
				
	  } else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
	  }
	}
	public void setAreasFuncionalesXRolSvc(
			AreasFuncionalesXRolSvc areasFuncionalesXRolSvc) {
		this.areasFuncionalesXRolSvc = areasFuncionalesXRolSvc;
	}

}
