package ar.org.promeba.mvc.controladores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.AreaGeografica;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.AreaGeograficaSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorAreasGeograficas extends AbstractController {
	
	private AreaGeograficaSvc svc;


	public void setSvc(AreaGeograficaSvc svc) {
		this.svc= svc;
	}
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfLargo=new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		
		
		if (uri.endsWith("selecciona")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String nombre=request.getParameter("nombre");
			Date fechaDesde=null;
			if (StringUtils.isNotEmpty(request.getParameter("fechaDesde"))){
				fechaDesde=sdfLargo.parse(request.getParameter("fechaDesde"));
			}
			Date fechaHasta=null;
			if (StringUtils.isNotEmpty(request.getParameter("fechaHasta"))){
				fechaHasta=sdfLargo.parse(request.getParameter("fechaHasta"));
			}
			
			JSONArray datos=new JSONArray();
			List<AreaGeografica> beans=svc.selecciona(start, limit, nombre, fechaDesde, fechaHasta);
			int cuenta=svc.cuenta(nombre, fechaDesde, fechaHasta);
			for (AreaGeografica bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				if (bean.getFechaDesde()!=null){
				  fila.put("fechaDesde", sdfLargo.format(bean.getFechaDesde()));
				}
				if (bean.getFechaHasta()!=null){
				  fila.put("fechaHasta", sdfLargo.format(bean.getFechaHasta()));
				}
				fila.put("archivoGE", bean.getArchivoGE());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		}else if (uri.endsWith("inserta")){
			
			String nombre=request.getParameter("nombre");
			Date fechaDesde=null;
			if (StringUtils.isNotEmpty(request.getParameter("fechaDesde"))){
				fechaDesde=sdf.parse(request.getParameter("fechaDesde"));
			}
			Date fechaHasta=null;
			if (StringUtils.isNotEmpty(request.getParameter("fechaHasta"))){
				fechaHasta=sdf.parse(request.getParameter("fechaHasta"));
			};
			String archivoGE= request.getParameter("nombreArchivoSubido");
			
			AreaGeografica bean=new AreaGeografica();
			bean.setNombre(nombre);
			bean.setFechaDesde(fechaDesde);
			bean.setFechaHasta(fechaHasta);
			bean.setArchivoGE(archivoGE);
			svc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("areaGeograficaId");
			String nombre=request.getParameter("nombre");
			Date fechaDesde=null;
			if (StringUtils.isNotEmpty(request.getParameter("fechaDesde"))){
				fechaDesde=sdf.parse(request.getParameter("fechaDesde"));
			}
			Date fechaHasta=null;
			if (StringUtils.isNotEmpty(request.getParameter("fechaHasta"))){
				fechaHasta=sdf.parse(request.getParameter("fechaHasta"));
			}
			String archivoGE= request.getParameter("nombreArchivoSubido");

			AreaGeografica bean=new AreaGeografica();
			bean.setId(id);
			bean.setNombre(nombre);
			bean.setFechaDesde(fechaDesde);
			bean.setFechaHasta(fechaHasta);
			bean.setArchivoGE(archivoGE);
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
		}
		
		else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
		}
	}

}
