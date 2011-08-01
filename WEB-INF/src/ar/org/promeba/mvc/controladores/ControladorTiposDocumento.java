package ar.org.promeba.mvc.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.TipoDocumento;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.TipoDocumentoSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorTiposDocumento extends AbstractController {
	
	private TipoDocumentoSvc tipoDocumentoSvc;


	public void setTipoDocumentoSvc(TipoDocumentoSvc tipoDocumentoSvc) {
		this.tipoDocumentoSvc = tipoDocumentoSvc;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		if (uri.endsWith("selecciona")){
			JSONArray datos=new JSONArray();
			List<TipoDocumento> beans=tipoDocumentoSvc.selecciona(0, 100);
			for (TipoDocumento bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			//job.put("total", beans.size());
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
		}else{
			return super.handleRequest(request, response);
		}
	}

}
