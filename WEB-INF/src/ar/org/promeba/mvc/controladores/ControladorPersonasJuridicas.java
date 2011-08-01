package ar.org.promeba.mvc.controladores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.Domicilio;
import ar.org.promeba.beans.PersonaJuridica;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.PersonaJuridicaSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorPersonasJuridicas extends AbstractController {
	
	private PersonaJuridicaSvc svc;

	public void setSvc(PersonaJuridicaSvc svc) {
		this.svc = svc;
	}
	
	private static SimpleDateFormat  sdfLargo=new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		if (uri.endsWith("selecciona")){
			
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String nombre=request.getParameter("nombre");
			
			JSONArray datos=new JSONArray();
			List<PersonaJuridica> beans=svc.selecciona(start, limit, nombre);
			for (PersonaJuridica bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				if (bean.getFechaInscripcion()!=null){
				  fila.put("inscripcion", sdfLargo.format(bean.getFechaInscripcion()));
				}
				fila.put("personeria", bean.getPersoneria());
				fila.put("cuit", bean.getCuit());
				DomicilioUtils.pueblaMap(fila, bean.getDomicilio());
				datos.put(fila);
			}
			int cuenta=svc.cuenta(nombre);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("inserta")){
			String nombre=request.getParameter("nombre");
			Date fechaInscripcion=ControladorUtils.extraeParametroFecha(request, "fechaInscripcion");
			String personeria=request.getParameter("personeria");
			String cuit=request.getParameter("cuit");
			
			PersonaJuridica bean=new PersonaJuridica();
			bean.setNombre(nombre);
			bean.setFechaInscripcion(fechaInscripcion);
			bean.setPersoneria(personeria);
			bean.setCuit(cuit);
			Domicilio dom=new Domicilio();
			DomicilioUtils.pueblaBeanConRequest(request, "personasJuridicas", dom);
			bean.setDomicilio(dom);
			
			svc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			String id=request.getParameter("personaJuridicaId");
			String nombre=request.getParameter("nombre");
			Date fechaInscripcion=ControladorUtils.extraeParametroFecha(request, "fechaInscripcion");
			String personeria=request.getParameter("personeria");
			String cuit=request.getParameter("cuit");
			
			PersonaJuridica bean=new PersonaJuridica();
			bean.setId(id);
			bean.setNombre(nombre);
			bean.setFechaInscripcion(fechaInscripcion);
			bean.setPersoneria(personeria);
			bean.setCuit(cuit);
			Domicilio dom=new Domicilio();
			DomicilioUtils.pueblaBeanConRequest(request, "personasJuridicas", dom);
			bean.setDomicilio(dom);

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
		}else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
		}
	}

}
