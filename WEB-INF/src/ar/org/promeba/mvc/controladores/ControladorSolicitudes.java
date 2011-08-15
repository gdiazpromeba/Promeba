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
import ar.org.promeba.beans.EsmXSolicitud;
import ar.org.promeba.beans.Solicitud;
import ar.org.promeba.beans.SpdXSolicitud;
import ar.org.promeba.beans.TirXSolicitud;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.EqSocXSolicitudSvc;
import ar.org.promeba.svc.EsmXSolicitudSvc;
import ar.org.promeba.svc.SolicitudSvc;
import ar.org.promeba.svc.SpdXSolicitudSvc;
import ar.org.promeba.svc.TirXSolicitudSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorSolicitudes extends AbstractController {
	
	@Autowired
	private SolicitudSvc solicitudSvc;

	@Autowired
	private EqSocXSolicitudSvc eqSocXSolicitudSvc;
	
	@Autowired
	private SpdXSolicitudSvc spdXSolicitudSvc;
	
	@Autowired
	private EsmXSolicitudSvc esmXSolicitudSvc;
	
	@Autowired
	private TirXSolicitudSvc tirXSolicitudSvc;
	
	

	
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
			
		}else if (uri.endsWith("seleccionaServiciosPublicosDisponibles")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String solicitudId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<SpdXSolicitud> beans=spdXSolicitudSvc.seleccionaSpdXSolicitud(start, limit, solicitudId);
			for (SpdXSolicitud bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("spdId", bean.getSpdId());
				fila.put("solicitudId", bean.getSolicitudId());
				fila.put("spdNombre", bean.getSpdNombre());
				fila.put("spdXSolicitudDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=spdXSolicitudSvc.cuentaSpdXSolicitud(solicitudId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaServicioPublicoDisponible")){
			
			String spdId=request.getParameter("spdAsignadoId");
			String solicitudId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("spdXSolicitudDescripcion");
			SpdXSolicitud bean=new SpdXSolicitud();
			bean.setSolicitudId(solicitudId);
			bean.setSpdId(spdId);
			bean.setDescripcion(descripcion);
			
			spdXSolicitudSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraServicioPublicoDisponible")){
			
			String id=request.getParameter("id");
			spdXSolicitudSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("seleccionaEstadosMensura")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String solicitudId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<EsmXSolicitud> beans=esmXSolicitudSvc.seleccionaEsmXSolicitud(start, limit, solicitudId);
			for (EsmXSolicitud bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("esmId", bean.getEsmId());
				fila.put("solicitudId", bean.getSolicitudId());
				fila.put("esmNombre", bean.getEsmNombre());
				fila.put("esmXSolicitudDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=esmXSolicitudSvc.cuentaEsmXSolicitud(solicitudId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaEstadoMensura")){
			
			String esmId=request.getParameter("esmAsignadoId");
			String solicitudId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("esmXSolicitudDescripcion");
			EsmXSolicitud bean=new EsmXSolicitud();
			bean.setSolicitudId(solicitudId);
			bean.setEsmId(esmId);
			bean.setDescripcion(descripcion);
			
			esmXSolicitudSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraEstadoMensura")){
			
			String id=request.getParameter("id");
			esmXSolicitudSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("seleccionaTiposRiesgo")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String solicitudId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<TirXSolicitud> beans=tirXSolicitudSvc.seleccionaTirXSolicitud(start, limit, solicitudId);
			for (TirXSolicitud bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("tirId", bean.getTirId());
				fila.put("solicitudId", bean.getSolicitudId());
				fila.put("tirNombre", bean.getTirNombre());
				fila.put("tirXSolicitudDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=tirXSolicitudSvc.cuentaTirXSolicitud(solicitudId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaTipoRiesgo")){
			
			String tirId=request.getParameter("tirAsignadoId");
			String solicitudId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("tirXSolicitudDescripcion");
			TirXSolicitud bean=new TirXSolicitud();
			bean.setSolicitudId(solicitudId);
			bean.setTirId(tirId);
			bean.setDescripcion(descripcion);
			
			tirXSolicitudSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraTipoRiesgo")){
			
			String id=request.getParameter("id");
			tirXSolicitudSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		
			
		
	  } else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
	  }
	}

}
