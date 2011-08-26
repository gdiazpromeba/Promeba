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

import ar.org.promeba.beans.EqSocXProyecto;
import ar.org.promeba.beans.EqSocXSolicitud;
import ar.org.promeba.beans.EsmXProyecto;
import ar.org.promeba.beans.EsmXSolicitud;
import ar.org.promeba.beans.Proyecto;
import ar.org.promeba.beans.Solicitud;
import ar.org.promeba.beans.SpdXProyecto;
import ar.org.promeba.beans.SpdXSolicitud;
import ar.org.promeba.beans.TirXProyecto;
import ar.org.promeba.beans.TirXSolicitud;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.EqSocXProyectoSvc;
import ar.org.promeba.svc.EqSocXSolicitudSvc;
import ar.org.promeba.svc.EsmXProyectoSvc;
import ar.org.promeba.svc.EsmXSolicitudSvc;
import ar.org.promeba.svc.ProyectoSvc;
import ar.org.promeba.svc.SolicitudSvc;
import ar.org.promeba.svc.SpdXProyectoSvc;
import ar.org.promeba.svc.SpdXSolicitudSvc;
import ar.org.promeba.svc.TirXProyectoSvc;
import ar.org.promeba.svc.TirXSolicitudSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorProyectos extends AbstractController {
	
	@Autowired
	private ProyectoSvc proyectoSvc;

	@Autowired
	private EqSocXProyectoSvc eqSocXProyectoSvc;
	
	@Autowired
	private SpdXProyectoSvc spdXProyectoSvc;
	
	@Autowired
	private EsmXProyectoSvc esmXProyectoSvc;
	
	@Autowired
	private TirXProyectoSvc tirXProyectoSvc;
	
	

	
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
			List<Proyecto> beans=proyectoSvc.selecciona(start, limit, subejecutorId, estado, provinciaId, regionId );
			int cuenta=proyectoSvc.cuenta(subejecutorId, estado, provinciaId, regionId );
			for (Proyecto bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("descripcion", bean.getDescripcion());
				fila.put("solicitudId", bean.getSolicitudId());
				fila.put("solicitudDescripcion", bean.getSolicitudDescripcion());
				fila.put("estado", bean.getEstado());
				fila.put("subejecutorId", bean.getSubejecutorId());
				fila.put("subejecutorNombre", bean.getSubejecutorNombre());
				fila.put("fechaDesde", bean.getFechaDesde());
				fila.put("fechaHasta", bean.getFechaHasta());
				fila.put("presupuestoEstimado", bean.getPresupuestoEstimado());
				fila.put("cantidadLotes", bean.getCantidadLotes());
				fila.put("situacionDominialId", bean.getSituacionDominialId());
				fila.put("situacionDominialNombre", bean.getSituacionDominialNombre());
				fila.put("tipoInversionId", bean.getTipoInversionId());
				fila.put("tipoInversionNombre", bean.getTipoInversionNombre());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		}else if (uri.endsWith("inserta")){
			String descripcion=request.getParameter("descripcion");
		        String solicitudId=request.getParameter("solicitudId");
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
			String situacionDominialId=request.getParameter("situacionDominialId");
			String tipoInversionId=request.getParameter("tipoInversionId");
			
			Proyecto bean=new Proyecto();
			bean.setDescripcion(descripcion);
			bean.setSolicitudId(solicitudId);
			bean.setEstado(estado);
			bean.setSubejecutorId(subejecutorId);
			bean.setSituacionDominialId(situacionDominialId);
			bean.setTipoInversionId(tipoInversionId);
			
			if (!StringUtils.isEmpty(fechaDesde)){
				bean.setFechaDesde(df.parse(fechaDesde));
			}
			if (!StringUtils.isEmpty(fechaHasta)){
				bean.setFechaHasta(df.parse(fechaHasta));
			}
			if (!StringUtils.isEmpty(presupuestoEstimado)){
				bean.setPresupuestoEstimado(BigDecimal.valueOf(Double.parseDouble(presupuestoEstimado)));
			}
			if (!StringUtils.isEmpty(cantidadLotes)){
				bean.setCantidadLotes(Integer.parseInt(cantidadLotes));
			}
			proyectoSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("solicitudId");
			String descripcion=request.getParameter("descripcion");
			String solicitudId=request.getParameter("solicitudId");
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
			String situacionDominialId=request.getParameter("situacionDominialId");
			String tipoInversionId=request.getParameter("tipoInversionId");
			
			Proyecto bean=new Proyecto();
			bean.setId(id);
			bean.setSolicitudId(solicitudId);
			bean.setDescripcion(descripcion);
			bean.setEstado(estado);
			bean.setSubejecutorId(subejecutorId);
			bean.setSituacionDominialId(situacionDominialId);
			bean.setTipoInversionId(tipoInversionId);
			
			if (!StringUtils.isEmpty(fechaDesde)){
				bean.setFechaDesde(df.parse(fechaDesde));
			}
			if (!StringUtils.isEmpty(fechaHasta)){
				bean.setFechaHasta(df.parse(fechaHasta));
			}
			if (!StringUtils.isEmpty(presupuestoEstimado)){
				bean.setPresupuestoEstimado(BigDecimal.valueOf(Double.parseDouble(presupuestoEstimado)));
			}
			if (!StringUtils.isEmpty(cantidadLotes)){
				bean.setCantidadLotes(Integer.parseInt(cantidadLotes));
			}			

			proyectoSvc.actualiza(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("borra")){
			
			String id=request.getParameter("id");
			proyectoSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("seleccionaEquipamientosSociales")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String proyectoId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<EqSocXProyecto> beans=eqSocXProyectoSvc.seleccionaEqSocXProyecto(start, limit, proyectoId);
			for (EqSocXProyecto bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("eqSocId", bean.getEqSocId());
				fila.put("proyectoId", bean.getProyectoId());
				fila.put("eqSocNombre", bean.getEqSocNombre());
				fila.put("eqSocXSolicitudDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=eqSocXProyectoSvc.cuentaEqSocXProyecto(proyectoId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaEquipamientoSocial")){
			
			String eqSocId=request.getParameter("eqSocAsignadoId");
			String proyectoId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("eqSocXSolicitudDescripcion");
			EqSocXProyecto bean=new EqSocXProyecto();
			bean.setProyectoId(proyectoId);
			bean.setEqSocId(eqSocId);
			bean.setDescripcion(descripcion);
			
			eqSocXProyectoSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraEquipamientoSocial")){
			
			String id=request.getParameter("id");
			eqSocXProyectoSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("seleccionaServiciosPublicosDisponibles")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String proyectoId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<SpdXProyecto> beans=spdXProyectoSvc.seleccionaSpdXProyecto(start, limit, proyectoId);
			for (SpdXProyecto bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("spdId", bean.getSpdId());
				fila.put("proyectoId", bean.getProyectoId());
				fila.put("spdNombre", bean.getSpdNombre());
				fila.put("spdXSolicitudDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=spdXProyectoSvc.cuentaSpdXProyecto(proyectoId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaServicioPublicoDisponible")){
			
			String spdId=request.getParameter("spdAsignadoId");
			String proyectoId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("spdXSolicitudDescripcion");
			SpdXProyecto bean=new SpdXProyecto();
			bean.setProyectoId(proyectoId);
			bean.setSpdId(spdId);
			bean.setDescripcion(descripcion);
			
			spdXProyectoSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraServicioPublicoDisponible")){
			
			String id=request.getParameter("id");
			spdXProyectoSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("seleccionaEstadosMensura")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String solicitudId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<EsmXProyecto> beans=esmXProyectoSvc.seleccionaEsmXProyecto(start, limit, solicitudId);
			for (EsmXProyecto bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("esmId", bean.getEsmId());
				fila.put("proyectoId", bean.getProyectoId());
				fila.put("esmNombre", bean.getEsmNombre());
				fila.put("esmXProyectoDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=esmXProyectoSvc.cuentaEsmXProyecto(solicitudId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaEstadoMensura")){
			
			String esmId=request.getParameter("esmAsignadoId");
			String proyectoId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("esmXSolicitudDescripcion");
			EsmXProyecto bean=new EsmXProyecto();
			bean.setProyectoId(proyectoId);
			bean.setEsmId(esmId);
			bean.setDescripcion(descripcion);
			
			esmXProyectoSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraEstadoMensura")){
			
			String id=request.getParameter("id");
			esmXProyectoSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("seleccionaTiposRiesgo")){
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String proyectoId=request.getParameter("valorIdPadre");
			
			JSONArray datos=new JSONArray();
			List<TirXProyecto> beans=tirXProyectoSvc.seleccionaTirXProyecto(start, limit, proyectoId);
			for (TirXProyecto bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("tirId", bean.getTirId());
				fila.put("proyectoId", bean.getProyectoId());
				fila.put("tirNombre", bean.getTirNombre());
				fila.put("tirXProyectoDescripcion", bean.getDescripcion());
				datos.put(fila);
			}
			int cuenta=tirXProyectoSvc.cuentaTirXProyecto(proyectoId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;	
			
		}else if (uri.endsWith("insertaTipoRiesgo")){
			
			String tirId=request.getParameter("tirAsignadoId");
			String proyectoId=request.getParameter("valorIdPadre");
			String descripcion=request.getParameter("tirXProyectoDescripcion");
			TirXProyecto bean=new TirXProyecto();
			bean.setProyectoId(proyectoId);
			bean.setTirId(tirId);
			bean.setDescripcion(descripcion);
			
			tirXProyectoSvc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else if (uri.endsWith("borraTipoRiesgo")){
			
			String id=request.getParameter("id");
			tirXProyectoSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		
			
		
	  } else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
	  }
	}

}
