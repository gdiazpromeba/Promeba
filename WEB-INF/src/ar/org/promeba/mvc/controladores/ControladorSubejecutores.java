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

import ar.org.promeba.beans.Persona;
import ar.org.promeba.beans.Subejecutor;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.SubejecutorSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorSubejecutores extends AbstractController {

    @Autowired
    private SubejecutorSvc subejecutorSvc;

    private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	String uri = request.getRequestURI();
	response.setContentType("application/x-json;charset=UTF-8");

	if (uri.endsWith("selecciona")) {

	    int start = Integer.parseInt(request.getParameter("start"));
	    int limit = Integer.parseInt(request.getParameter("limit"));
	    String nombre = request.getParameter("nombre");
	    String regionId = request.getParameter("regionId");

	    JSONArray datos = new JSONArray();
	    List<Subejecutor> beans = subejecutorSvc.selecciona(start, limit, nombre, regionId);
	    int cuenta = subejecutorSvc.cuenta(nombre, regionId);
	    for (Subejecutor bean : beans) {
		Map<String, Object> fila = new HashMap<String, Object>();
		fila.put("id", bean.getId());
		fila.put("nombre", bean.getNombre());
		fila.put("caracter", bean.getCaracter());
		fila.put("email", bean.getEmail());
		fila.put("contacto", bean.getContacto());
		fila.put("legitimacion", df.format(bean.getFechaLegitimacion()));
		fila.put("convenio", bean.getConvenio());
		fila.put("personaId", bean.getPersona().getId());
		fila.put("personaDenominacion", bean.getPersona().getNombre());
		datos.put(fila);
	    }
	    JSONObject job = new JSONObject();
	    job.put("total", cuenta);
	    job.put("data", datos);

	    ModelAndView mav = new ModelAndView(new JSONView(job));

	    return mav;

	} else if (uri.endsWith("inserta")) {

	    String nombre = request.getParameter("nombre");
	    String personaId = request.getParameter("personaId");
	    String email = request.getParameter("email");
	    String contacto = request.getParameter("contacto");
	    String caracter = request.getParameter("comboCaracter");
	    String legitimacion = request.getParameter("legitimacion");
	    String convenio = request.getParameter("nombreArchivoSubido");

	    Subejecutor bean = new Subejecutor();
	    bean.setNombre(nombre);
	    bean.setCaracter(caracter);
	    Persona persona = new Persona();
	    persona.setId(personaId);
	    bean.setPersona(persona);
	    bean.setEmail(email);
	    bean.setContacto(contacto);
	    bean.setFechaLegitimacion(df.parse(legitimacion));
	    bean.setConvenio(convenio);

	    subejecutorSvc.inserta(bean);

	    JSONObject job = new JSONObject();
	    job.put("success", true);
	    job.put("nuevoId", bean.getId());

	    ModelAndView mav = new ModelAndView(new JSONView(job));
	    return mav;

	} else if (uri.endsWith("actualiza")) {

	    String id = request.getParameter("subejecutorId");
	    String nombre = request.getParameter("nombre");
	    String personaId = request.getParameter("personaId");
	    String caracter = request.getParameter("comboCaracter");
	    String email = request.getParameter("email");
	    String contacto = request.getParameter("contacto");
	    String legitimacion = request.getParameter("legitimacion");
	    String convenio = request.getParameter("nombreArchivoSubido");

	    Subejecutor bean = new Subejecutor();
	    bean.setId(id);
	    bean.setNombre(nombre);
	    bean.setCaracter(caracter);
	    bean.setEmail(email);
	    bean.setContacto(contacto);
	    bean.setFechaLegitimacion(df.parse(legitimacion));
	    bean.setConvenio(convenio);
	    Persona persona = new Persona();
	    persona.setId(personaId);
	    bean.setPersona(persona);

	    subejecutorSvc.actualiza(bean);

	    JSONObject job = new JSONObject();
	    job.put("success", true);

	    ModelAndView mav = new ModelAndView(new JSONView(job));
	    return mav;

	} else if (uri.endsWith("borra")) {

	    String id = request.getParameter("id");
	    subejecutorSvc.borra(id);

	    JSONObject job = new JSONObject();
	    job.put("success", true);

	    ModelAndView mav = new ModelAndView(new JSONView(job));
	    return mav;

	} else {
	    throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
	}
    }

}
