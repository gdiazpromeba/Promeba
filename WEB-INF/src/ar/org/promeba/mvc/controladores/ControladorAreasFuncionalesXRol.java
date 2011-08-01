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
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.AreasFuncionalesXRolSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorAreasFuncionalesXRol extends AbstractController {
	
	private AreasFuncionalesXRolSvc svc;

	public void setSvc(AreasFuncionalesXRolSvc svc) {
		this.svc = svc;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		
		
        if (uri.endsWith("selecciona")){
			
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String rolId=request.getParameter("rolId");
			
			
			JSONArray datos=new JSONArray();
			List<AreaFuncionalXRol> beans=svc.seleccionaAreasFuncionalesXRol(start, limit, rolId);
			int cuenta=svc.cuentaAreasFuncionalesXRol(rolId);
			for (AreaFuncionalXRol bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("rolId", bean.getRolId());
				fila.put("rolNombre", bean.getRolNombre());
				fila.put("areaFuncionalId", bean.getAreaId());
				fila.put("areaFuncionalNombre", bean.getAreaNombre());
				fila.put("vision", bean.isVision());
				fila.put("escritura", bean.isEscritura());
				fila.put("lectura", bean.isLectura());
				fila.put("ejecucion", bean.isEjecucion());
				fila.put("impresion", bean.isImpresion());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
		
			
		}else if (uri.endsWith("inserta")){
			
			String areaFuncionalId=request.getParameter("areaFuncionalId");
			String rolId=request.getParameter("rolId");
			boolean vision=ControladorUtils.extraeParametroBoolean(request, "vision");
			boolean escritura=ControladorUtils.extraeParametroBoolean(request, "escritura");
			boolean lectura=ControladorUtils.extraeParametroBoolean(request, "lectura");
			boolean ejecucion=ControladorUtils.extraeParametroBoolean(request, "ejecucion");
			boolean impresion=ControladorUtils.extraeParametroBoolean(request, "impresion");
			
			
			AreaFuncionalXRol bean=new AreaFuncionalXRol();
			bean.setAreaId(areaFuncionalId);
			bean.setRolId(rolId);
			bean.setVision(vision);
			bean.setEscritura(escritura);
			bean.setLectura(lectura);
			bean.setImpresion(impresion);
			bean.setEjecucion(ejecucion);
			
			svc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("areaFuncionalXRolId");
			String areaFuncionalId=request.getParameter("areaFuncionalId");
			String rolId=request.getParameter("rolId");
			boolean vision=ControladorUtils.extraeParametroBoolean(request, "vision");
			boolean escritura=ControladorUtils.extraeParametroBoolean(request, "escritura");
			boolean lectura=ControladorUtils.extraeParametroBoolean(request, "lectura");
			boolean ejecucion=ControladorUtils.extraeParametroBoolean(request, "ejecucion");
			boolean impresion=ControladorUtils.extraeParametroBoolean(request, "impresion");

			AreaFuncionalXRol bean=new AreaFuncionalXRol();
			bean.setId(id);
			bean.setAreaId(areaFuncionalId);
			bean.setRolId(rolId);
			bean.setVision(vision);
			bean.setEscritura(escritura);
			bean.setLectura(lectura);
			bean.setImpresion(impresion);
			bean.setEjecucion(ejecucion);
			
			svc.modifica(bean);
			
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
