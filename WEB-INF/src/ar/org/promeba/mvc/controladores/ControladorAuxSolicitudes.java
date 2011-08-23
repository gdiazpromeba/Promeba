package ar.org.promeba.mvc.controladores;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.EquipamientoSocial;
import ar.org.promeba.beans.EstadoMensura;
import ar.org.promeba.beans.EstadoProyecto;
import ar.org.promeba.beans.Fuente;
import ar.org.promeba.beans.ServicioPublicoDisponible;
import ar.org.promeba.beans.SituacionDominial;
import ar.org.promeba.beans.TipoInversion;
import ar.org.promeba.beans.TipoRiesgo;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.EquipamientoSocialSvc;
import ar.org.promeba.svc.EstadoMensuraSvc;
import ar.org.promeba.svc.EstadoProyectoSvc;
import ar.org.promeba.svc.FuenteSvc;
import ar.org.promeba.svc.ServicioPublicoDisponibleSvc;
import ar.org.promeba.svc.SituacionDominialSvc;
import ar.org.promeba.svc.TipoInversionSvc;
import ar.org.promeba.svc.TipoRiesgoSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorAuxSolicitudes extends AbstractController {

	@Autowired
	private EquipamientoSocialSvc equipamientoSocialSvc;
	
	@Autowired
	private EstadoMensuraSvc estadoMensuraSvc;
	
	@Autowired
	private FuenteSvc fuenteSvc;
	
	@Autowired
	private EstadoProyectoSvc estadoProyectoSvc;
	
	@Autowired
	private ServicioPublicoDisponibleSvc servicioPublicoDisponibleSvc;
	
	@Autowired
	private SituacionDominialSvc situacionDominialSvc;
	
	@Autowired
	private TipoInversionSvc tipoInversionSvc;
	
	@Autowired
	private TipoRiesgoSvc tipoRiesgoSvc;
	
	

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfLargo = new SimpleDateFormat(
			"yyyy-MM-dd");

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String uri = request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");

		if (uri.endsWith("seleccionaEquipamientosSociales")) {
//			int start = Integer.parseInt(request.getParameter("start"));
//			int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<EquipamientoSocial> beans = equipamientoSocialSvc.selecciona(
					0, 100, nombre);
			int cuenta = equipamientoSocialSvc.cuenta(nombre);
			for (EquipamientoSocial bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;

		} else if (uri.endsWith("seleccionaEstadosMensura")) {
			//int start = Integer.parseInt(request.getParameter("start"));
			//int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<EstadoMensura> beans = estadoMensuraSvc.selecciona(0, 100, nombre);
			int cuenta = estadoMensuraSvc.cuenta(nombre);
			for (EstadoMensura bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;

		} else if (uri.endsWith("seleccionaFuentes")) {
			int start = Integer.parseInt(request.getParameter("start"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<Fuente> beans = fuenteSvc.selecciona(start,
					limit, nombre);
			int cuenta = fuenteSvc.cuenta(nombre);
			for (Fuente bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;
			
		} else if (uri.endsWith("seleccionaEstadosProyecto")) {
			int start = Integer.parseInt(request.getParameter("start"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<EstadoProyecto> beans = estadoProyectoSvc.selecciona(start, limit, nombre);
			int cuenta = fuenteSvc.cuenta(nombre);
			for (EstadoProyecto bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;
			
		} else if (uri.endsWith("seleccionaServiciosPublicosDisponibles")) {
//			int start = Integer.parseInt(request.getParameter("start"));
//			int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<ServicioPublicoDisponible> beans = servicioPublicoDisponibleSvc.selecciona(0, 100, nombre);
			int cuenta = fuenteSvc.cuenta(nombre);
			for (ServicioPublicoDisponible bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;
			
		} else if (uri.endsWith("seleccionaSituacionesDominiales")) {
			//int start = Integer.parseInt(request.getParameter("start"));
			//int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<SituacionDominial> beans = situacionDominialSvc.selecciona(0, 100, nombre);
			int cuenta = situacionDominialSvc.cuenta(nombre);
			for (SituacionDominial bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;
			
		} else if (uri.endsWith("seleccionaTiposInversion")) {
			//int start = Integer.parseInt(request.getParameter("start"));
			//int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<TipoInversion> beans = tipoInversionSvc.selecciona(0, 100, nombre);
			int cuenta = tipoInversionSvc.cuenta(nombre);
			for (TipoInversion bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;
			
		} else if (uri.endsWith("seleccionaTiposRiesgo")) {
//			int start = Integer.parseInt(request.getParameter("start"));
//			int limit = Integer.parseInt(request.getParameter("limit"));
			String nombre = request.getParameter("nombre");

			JSONArray datos = new JSONArray();
			List<TipoRiesgo> beans = tipoRiesgoSvc.selecciona(0, 100, nombre);
			int cuenta = tipoRiesgoSvc.cuenta(nombre);
			for (TipoRiesgo bean : beans) {
				Map<String, Object> fila = new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job = new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);

			ModelAndView mav = new ModelAndView(new JSONView(job));

			return mav;
			
			
			

		} else {
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
		}
	}

}
