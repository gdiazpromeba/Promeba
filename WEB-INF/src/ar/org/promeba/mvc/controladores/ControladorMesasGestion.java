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

import ar.org.promeba.beans.MesaGestion;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.MesaGestionSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorMesasGestion extends AbstractController {
	
	private MesaGestionSvc svc;

	public void setSvc(MesaGestionSvc svc) {
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
			Date fechaActaAcuerdo=ControladorUtils.extraeParametroFecha(request, "fechaActaAcuerdo");
			Date fechaMesaGestion=ControladorUtils.extraeParametroFecha(request, "fechaMesaGestion");
			String estado = request.getParameter("estado");
			
			JSONArray datos=new JSONArray();
			List<MesaGestion> beans=svc.selecciona(start, limit, fechaActaAcuerdo, fechaMesaGestion,  estado);
			int cuenta=svc.cuenta(fechaActaAcuerdo, fechaMesaGestion,  estado);
			for (MesaGestion bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				if (bean.getFechaActaAcuerdo()!=null){
					fila.put("fechaActaAcuerdo", sdfLargo.format(bean.getFechaActaAcuerdo()));
				}
				if (bean.getFechaMesaGestion()!=null){
					fila.put("fechaMesaGestion", sdfLargo.format(bean.getFechaMesaGestion()));
				}
				fila.put("estado", bean.getEstado());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		}else if (uri.endsWith("inserta")){
			
			Date fechaActaAcuerdo=ControladorUtils.extraeParametroFecha(request, "fechaActaAcuerdo");
			Date fechaMesaGestion=ControladorUtils.extraeParametroFecha(request, "fechaMesaGestion");
			String estado = request.getParameter("estado");
			
			MesaGestion bean=new MesaGestion();
			bean.setFechaActaAcuerdo(fechaActaAcuerdo);
			bean.setFechaMesaGestion(fechaMesaGestion);
			bean.setEstado(estado);
			svc.inserta(bean);
			
			JSONObject job=new JSONObject();
			job.put("success", true);
			job.put("nuevoId", bean.getId());
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		}else if (uri.endsWith("actualiza")){
			
			String id=request.getParameter("mesaGestionId");
			Date fechaActaAcuerdo=ControladorUtils.extraeParametroFecha(request, "fechaActaAcuerdo");
			Date fechaMesaGestion=ControladorUtils.extraeParametroFecha(request, "fechaMesaGestion");
			String estado = request.getParameter("estado");

			MesaGestion bean=new MesaGestion();
			bean.setId(id);
			bean.setFechaActaAcuerdo(fechaActaAcuerdo);
			bean.setFechaMesaGestion(fechaMesaGestion);
			bean.setEstado(estado);

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
		
	  } else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
	  }
	}

}
