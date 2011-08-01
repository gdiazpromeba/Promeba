package ar.org.promeba.mvc.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.util.json.JSONObject;

/**
 * controlador que no hace nada más que devolver un fallo.
 * usado las las forms pop-up, que necesitan ser remitidas a algún lado
 * @author gonzalo
 *
 */
public class ControladorVacio extends AbstractController {
	

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject job=new JSONObject();
		job.put("success", false);
		ModelAndView mav=new ModelAndView(new JSONView(job));
			
		return mav;
			
	}
}
