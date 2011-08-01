package ar.org.promeba.mvc.controladores;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.EqSocXSolicitud;
import ar.org.promeba.beans.RolXUsuario;
import ar.org.promeba.beans.Solicitud;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.EqSocXSolicitudSvc;
import ar.org.promeba.svc.EquipamientoSocialSvc;
import ar.org.promeba.svc.SolicitudSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorSolicitudes extends AbstractController {
	
	@Autowired
	private SolicitudSvc solicitudSvc;
	@Autowired
	private EqSocXSolicitudSvc eqSocXSolicitudSvc;

	
	private static SimpleDateFormat  df=new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		
		if (uri.endsWith("selecciona")){
			
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String subejecutorId=request.getParameter("subejecutorId");
			String estado=request.getParameter("estado");
			String provinciaId=request.getParameter("provinciaId");
			String regionId=request.getParameter("regionId");
			
			
			JSONArray datos=new JSONArray();
			List<Solicitud> beans=solicitudSvc.selecciona(start, limit, subejecutorId, estado, provinciaId, regionId );
			int cuenta=solicitudSvc.cuenta(subejecutorId, estado, provinciaId, regionId );
			for (Solicitud bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("descripcion", bean.getDescripcion());
				fila.put("estado", bean.getEstado());
				fila.put("subejecutorId", bean.getSubejecutorId());
				fila.put("subejecutorNombre", bean.getSubejecutorNombre());
				fila.put("fechaDesde", bean.getFechaDesde());
				fila.put("fechaHasta", bean.getFechaHasta());
				fila.put("presupuestoEstimado", bean.getPresupuestoEstimado());
				fila.put("cantidadLotes", bean.getCantidadLotes());
				fila.put("fechaIngresoPA", bean.getFechaIngresoPA());
				fila.put("fechaIngresoPGEP", bean.getFechaIngresoPGEP());
				fila.put("fechaIngresoPOA", bean.getFechaIngresoPOA());
				fila.put("vinculo", bean.getLink());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		}else if (uri.endsWith("inserta")){
			
			String descripcion=request.getParameter("descripcion");
			String estado=request.getParameter("estado");
			String subejecutorId=request.getParameter("subejecutorId");
			String fechaDesde=request.getParameter("fechaDesde");
			String fechaHasta=request.getParameter("fechaHasta");
			String presupuestoEstimado = request.getParameter("presupuestoEstimado");
			String cantidadLotes = request.getParameter("cantidadLotes");
			String fechaIngresoPA = request.getParameter("fechaIngresoPA");
			String fechaIngresoPOA = request.getParameter("fechaIngresoPOA");
			String fechaIngresoPGEP = request.getParameter("fechaIngresoPGEP");
			String vinculo=request.getParameter("nombreArchivoSubido");
			
			Solicitud bean=new Solicitud();
			bean.setDescripcion(descripcion);
			bean.setEstado(estado);
			bean.setSubejecutorId(subejecutorId);
			bean.setLink(vinculo);
			
			if (!StringUtils.isEmpty(fechaDesde)){
				bean.setFechaDesde(df.parse(fechaDesde));
			}
			if (!StringUtils.isEmpty(fechaHasta)){
				bean.setFechaHasta(df.parse(fechaHasta));
			}
			if (!StringUtils.isEmpty(fechaIngresoPA)){
				bean.setFechaIngresoPA(df.parse(fechaIngresoPA));
			}
			if (!StringUtils.isEmpty(fechaIngresoPOA)){
				bean.setFechaIngresoPOA(df.parse(fechaIngresoPOA));
			}
			if (!StringUtils.isEmpty(fechaIngresoPGEP)){
				bean.setFechaIngresoPGEP(df.parse(fechaIngresoPGEP));
			}
			if (!StringUtils.isEmpty(presupuestoEstimado)){
				bean.setPresupuestoEstimado(BigDecimal.valueOf(Double.parseDouble(presupuestoEstimado)));
			}
			if (!StringUtils.isEmpty(cantidadLotes)){
				bean.setCantidadLotes(Integer.parseInt(cantidadLotes));
			}
			solicitudSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("solicitudId");
			String descripcion=request.getParameter("descripcion");
			String estado=request.getParameter("estado");
			String subejecutorId=request.getParameter("subejecutorId");
			String fechaDesde=request.getParameter("fechaDesde");
			String fechaHasta=request.getParameter("fechaHasta");
			String presupuestoEstimado = request.getParameter("presupuestoEstimado");
			String cantidadLotes = request.getParameter("cantidadLotes");
			String fechaIngresoPA = request.getParameter("fechaIngresoPA");
			String fechaIngresoPOA = request.getParameter("fechaIngresoPOA");
			String fechaIngresoPGEP = request.getParameter("fechaIngresoPGEP");
			String vinculo=request.getParameter("nombreArchivoSubido");
			
			Solicitud bean=new Solicitud();
			bean.setId(id);
			bean.setDescripcion(descripcion);
			bean.setEstado(estado);
			bean.setSubejecutorId(subejecutorId);
			bean.setLink(vinculo);
			
			if (!StringUtils.isEmpty(fechaDesde)){
				bean.setFechaDesde(df.parse(fechaDesde));
			}
			if (!StringUtils.isEmpty(fechaHasta)){
				bean.setFechaHasta(df.parse(fechaHasta));
			}
			if (!StringUtils.isEmpty(fechaIngresoPA)){
				bean.setFechaIngresoPA(df.parse(fechaIngresoPA));
			}
			if (!StringUtils.isEmpty(fechaIngresoPOA)){
				bean.setFechaIngresoPOA(df.parse(fechaIngresoPOA));
			}
			if (!StringUtils.isEmpty(fechaIngresoPGEP)){
				bean.setFechaIngresoPGEP(df.parse(fechaIngresoPGEP));
			}
			if (!StringUtils.isEmpty(presupuestoEstimado)){
				bean.setPresupuestoEstimado(BigDecimal.valueOf(Double.parseDouble(presupuestoEstimado)));
			}
			if (!StringUtils.isEmpty(cantidadLotes)){
				bean.setCantidadLotes(Integer.parseInt(cantidadLotes));
			}			

			solicitudSvc.actualiza(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("borra")){
			
			String id=request.getParameter("id");
			solicitudSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("seleccionaEquipamientosSociales")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String solicitudId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<EqSocXSolicitud> beans=eqSocXSolicitudSvc.seleccionaEqSocXSolicitud(start, limit, solicitudId);
			for (EqSocXSolicitud bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("eqSocId", bean.getEqSocId());
				fila.put("solicitudId", bean.getSolicitudId());
				fila.put("eqSocNombre", bean.getEqSocNombre());
				fila.put("eqSocXSolicitudDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=eqSocXSolicitudSvc.cuentaEqSocXSolicitud(solicitudId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaEquipamientoSocial")){
			
			String eqSocId=request.getParameter("eqSocAsignadoId");
			String solicitudId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("eqSocXSolicitudDescripcion");
			EqSocXSolicitud bean=new EqSocXSolicitud();
			bean.setSolicitudId(solicitudId);
			bean.setEqSocId(eqSocId);
			bean.setDescripcion(descripcion);
			
			eqSocXSolicitudSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraEquipamientoSocial")){
			
			String id=request.getParameter("id");
			eqSocXSolicitudSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
		
			
		
	  } else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
	  }
	}

}
