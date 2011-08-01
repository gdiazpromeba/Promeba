package ar.org.promeba.mvc.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.Persona;
import ar.org.promeba.beans.RolXUsuario;
import ar.org.promeba.beans.Usuario;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.UsuarioSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorUsers extends AbstractController {
	
	private UsuarioSvc usuarioSvc;

	public void setUsuarioSvc(UsuarioSvc usuarioSvc) {
		this.usuarioSvc = usuarioSvc;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		if (uri.endsWith("verifica")){
			
			String login=request.getParameter("login");
			String clave=request.getParameter("clave");
			
			Usuario bean=usuarioSvc.verifica(login, clave);
			JSONObject job=new JSONObject();
			
			if (bean!=null){
				job.put("success", true);
				job.put("usuarioId", bean.getId());
			}else{
				job.put("success", false);
			}

			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
		
		} else if (uri.endsWith("selecciona")){
			
			int start=Integer.parseInt(request.getParameter("start"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			String email=request.getParameter("email");
			String rolId=request.getParameter("rolId");
			
			JSONArray datos=new JSONArray();
			List<Usuario> beans=usuarioSvc.selecciona(start, limit, email, rolId);
			for (Usuario bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("login", bean.getLogin());
				fila.put("email", bean.getEmail());
				fila.put("clave", bean.getClave());
				fila.put("personaId", bean.getPersona().getId());
				fila.put("personaDenominacion", bean.getPersona().getNombre());
				fila.put("habilitado", true);
				datos.put(fila);
			}
			int cuenta=usuarioSvc.cuenta(email, rolId);
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("seleccionaRoles")){
				int start=Integer.parseInt(request.getParameter("start"));
				int limit=Integer.parseInt(request.getParameter("limit"));
				String usuarioId=request.getParameter("valorIdPadre");
				
				JSONArray datos=new JSONArray();
				List<RolXUsuario> beans=usuarioSvc.rolesAsignados(start, limit, usuarioId);
				for (RolXUsuario bean : beans) {
					Map<String, Object> fila=new HashMap<String, Object>();
					fila.put("id", bean.getId());
					fila.put("rolId", bean.getRolId());
					fila.put("rolNombre", bean.getRolNombre());
					fila.put("usuarioId", bean.getUsuarioId());
					datos.put(fila);
				}
				int cuenta=usuarioSvc.cuentaRolesAsignados(usuarioId);
				JSONObject job=new JSONObject();
				job.put("total", cuenta);
				job.put("data", datos);
				
				ModelAndView mav=new ModelAndView(new JSONView(job));
				return mav;
			
			
		}else if (uri.endsWith("inserta")){
			
			String login=request.getParameter("login");
			String email=request.getParameter("email");
			String clave=request.getParameter("clave");
			String personaId=request.getParameter("personaId");
			String personaDenominacion=request.getParameter("personaDenominacion");
			
			Usuario usuario=new Usuario();
			usuario.setLogin(login);
			usuario.setEmail(email);
			usuario.setClave(clave);
			usuario.setHabilitado(true);
			Persona per=new Persona();
			per.setId(personaId);
			per.setNombre(personaDenominacion);
			usuario.setPersona(per);
			

			
			usuarioSvc.inserta(usuario);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", usuario.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("insertaRol")){
			
			String rolId=request.getParameter("rolAsignadoId");
			String usuarioId=request.getParameter("valorIdPadre");
			RolXUsuario rxu=new RolXUsuario();
			rxu.setRolId(rolId);
			rxu.setUsuarioId(usuarioId);
			usuarioSvc.agregaUsuarioRol(rxu);
			
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", rxu.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;		
			
		}else if (uri.endsWith("borraRol")){
			
			String id=request.getParameter("id");
			usuarioSvc.borraUsuarioRol(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;				
			
		}else if (uri.endsWith("actualiza")){
			String id=request.getParameter("usuarioId");
			String login=request.getParameter("login");
			String email=request.getParameter("email");
			String clave=request.getParameter("clave");
			String personaId=request.getParameter("personaId");
			String personaDenominacion=request.getParameter("personaDenominacion");
			
			
			Usuario usuario=new Usuario();
			usuario.setId(id);
			usuario.setLogin(login);
			usuario.setEmail(email);
			usuario.setClave(clave);
			usuario.setHabilitado(true);
			Persona per=new Persona();
			per.setId(personaId);
			per.setNombre(personaDenominacion);
			usuario.setPersona(per);

			
			usuarioSvc.actualiza(usuario);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("borra")){
			
			String id=request.getParameter("id");
			usuarioSvc.borra(id);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
		}else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
		}
	}

}
