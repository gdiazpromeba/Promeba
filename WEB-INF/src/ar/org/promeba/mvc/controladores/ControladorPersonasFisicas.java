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
import ar.org.promeba.beans.PersonaFisica;
import ar.org.promeba.beans.TipoDocumento;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.PersonaFisicaSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorPersonasFisicas extends AbstractController {
	
	private PersonaFisicaSvc svc;

	public void setSvc(PersonaFisicaSvc svc) {
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
			String apellido=request.getParameter("apellido");
			
			JSONArray datos=new JSONArray();
			List<PersonaFisica> beans=svc.selecciona(start, limit, apellido);
			for (PersonaFisica bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				fila.put("apellido", bean.getApellido());
       			fila.put("tipoDocumentoId", bean.getTipoDocumento().getId());
				fila.put("tipoDocumentoNombre", bean.getTipoDocumento().getNombre());
				fila.put("documentoNumero", bean.getDocumentoNumero());
				fila.put("ocupacion", bean.getOcupacion());
				fila.put("sexo", bean.getSexo());
				if (bean.getNacimiento()!=null){
					fila.put("nacimiento", sdfLargo.format(bean.getNacimiento()));
				}
				DomicilioUtils.pueblaMap(fila, bean.getDomicilio());
				datos.put(fila);
			}
			int cuenta=svc.cuenta(apellido);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("inserta")){
			
			String apellido=request.getParameter("apellido");
			String nombre=request.getParameter("nombre");
			long numeroDocumento = Long.parseLong(request.getParameter("documentoNumero"));
			String tipoDocumentoId=request.getParameter("tipoDocumentoId");
			Date fechaNacimiento=ControladorUtils.extraeParametroFecha(request, "fechaNacimiento");
			String sexo=request.getParameter("sexo");
			String ocupacion=request.getParameter("ocupacion");
			
			PersonaFisica bean=new PersonaFisica();
			bean.setApellido(apellido);
			bean.setNombre(nombre);
			TipoDocumento tid=new TipoDocumento(); tid.setId(tipoDocumentoId);
			bean.setTipoDocumento(tid);
			bean.setDocumentoNumero(numeroDocumento);
			bean.setOcupacion(ocupacion);
			bean.setSexo(sexo);
			bean.setNacimiento(fechaNacimiento);
			Domicilio dom=new Domicilio();
			
			DomicilioUtils.pueblaBeanConRequest(request, "personasFisicas", dom);
			bean.setDomicilio(dom);
			
			svc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			String id=request.getParameter("personaFisicaId");
			String apellido=request.getParameter("apellido");
			String nombre=request.getParameter("nombre");
			long numeroDocumento = Long.parseLong(request.getParameter("documentoNumero"));
			String tipoDocumentoId=request.getParameter("tipoDocumentoId");
			Date fechaNacimiento=ControladorUtils.extraeParametroFecha(request, "fechaNacimiento");
			String sexo=request.getParameter("sexo");
			String ocupacion=request.getParameter("ocupacion");

			
			PersonaFisica bean=new PersonaFisica();
			bean.setId(id);
			bean.setApellido(apellido);
			bean.setNombre(nombre);
			TipoDocumento tid=new TipoDocumento(); tid.setId(tipoDocumentoId);
			bean.setTipoDocumento(tid);
			bean.setDocumentoNumero(numeroDocumento);
			Domicilio dom=new Domicilio();
			DomicilioUtils.pueblaBeanConRequest(request, "personasFisicas", dom);
			bean.setDomicilio(dom);
			bean.setOcupacion(ocupacion);
			bean.setSexo(sexo);
			bean.setNacimiento(fechaNacimiento);

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
