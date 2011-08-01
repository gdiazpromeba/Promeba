package ar.org.promeba.mvc.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ar.org.promeba.beans.Coordenada;
import ar.org.promeba.beans.Departamento;
import ar.org.promeba.beans.Localidad;
import ar.org.promeba.beans.Provincia;
import ar.org.promeba.beans.Region;
import ar.org.promeba.mvc.view.JSONView;
import ar.org.promeba.svc.DepartamentoSvc;
import ar.org.promeba.svc.LocalidadSvc;
import ar.org.promeba.svc.ProvinciaSvc;
import ar.org.promeba.svc.RegionSvc;
import ar.org.promeba.util.json.JSONArray;
import ar.org.promeba.util.json.JSONObject;

public class ControladorGeografia extends AbstractController {
	
	private RegionSvc regionSvc;
	private ProvinciaSvc provinciaSvc;
	private DepartamentoSvc departamentoSvc;
	private LocalidadSvc localidadSvc;


	public void setRegionSvc(RegionSvc regionSvc) {
		this.regionSvc = regionSvc;
	}
	
	public void setProvinciaSvc(ProvinciaSvc provinciaSvc) {
		this.provinciaSvc = provinciaSvc;
	}
	
	public void setDepartamentoSvc(DepartamentoSvc departamentoSvc) {
		this.departamentoSvc = departamentoSvc;
	}
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uri=request.getRequestURI();
		response.setContentType("application/x-json;charset=UTF-8");
		
		
		if (uri.endsWith("seleccionaRegiones")){
			
			JSONArray datos=new JSONArray();
			List<Region> beans=regionSvc.selecciona();
			int cuenta=regionSvc.cuenta();
			for (Region bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				fila.put("color", bean.getColor());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;
			
		} else if (uri.endsWith("seleccionaProvincias")){
				
				String regionId=request.getParameter("regionId");
				
				JSONArray datos=new JSONArray();
				List<Provincia> beans=provinciaSvc.selecciona(regionId);
				int cuenta=regionSvc.cuenta();
				for (Provincia bean : beans) {
					Map<String, Object> fila=new HashMap<String, Object>();
					fila.put("id", bean.getId());
					fila.put("nombre", bean.getNombre());
					fila.put("regionId", bean.getRegionId());
					datos.put(fila);
				}
				JSONObject job=new JSONObject();
				job.put("total", cuenta);
				job.put("data", datos);
				
				ModelAndView mav=new ModelAndView(new JSONView(job));
				
				return mav;
				
		} else if (uri.endsWith("poligonoProvincia")){
			
			String provinciaId=request.getParameter("provinciaId");
			
			JSONArray datos=new JSONArray();
			List<Coordenada> beans=provinciaSvc.getPoligono(provinciaId);
			int cuenta=provinciaSvc.getPoligonoCuenta(provinciaId);
			for (Coordenada bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("latitud", bean.getLatitud());
				fila.put("longitud", bean.getLongitud());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			
			return mav;

		} else if (uri.endsWith("poligonosProvincias")){
			List<Provincia> provincias=provinciaSvc.selecciona(null);
			JSONObject res=new JSONObject();
			res.put("total", provincias.size());
			JSONArray datos=new JSONArray();
			
			for (Provincia provincia : provincias) {
				JSONObject reg=new JSONObject();
				reg.put("id", provincia.getId());
				reg.put("nombre", provincia.getNombre());
				reg.put("color", provincia.getColor());
				List<Coordenada> beans=provinciaSvc.getPoligono(provincia.getId());
				int cuentaCoordenadas=provinciaSvc.getPoligonoCuenta(provincia.getId());
				JSONArray datosCoordenadas=new JSONArray();
				for (Coordenada bean : beans) {
					Map<String, Object> fila=new HashMap<String, Object>();
					fila.put("latitud", bean.getLatitud());
					fila.put("longitud", bean.getLongitud());
					datosCoordenadas.put(fila);
				}
				JSONObject coordenadas=new JSONObject();
				coordenadas.put("total", cuentaCoordenadas);
				coordenadas.put("data", datosCoordenadas);
				reg.put("coordenadas", coordenadas);
				datos.put(reg);
			}
			res.put("data", datos);
			
			
			ModelAndView mav=new ModelAndView(new JSONView(res));
			
			return mav;		
			
		} else if (uri.endsWith("poligonosRegiones")){
			List<Region> regiones=regionSvc.selecciona();
			JSONObject res=new JSONObject();
			res.put("total", regiones.size());
			JSONArray datos=new JSONArray();
			
			for (Region region : regiones) {
				JSONObject reg=new JSONObject();
				reg.put("id", region.getId());
				reg.put("nombre", region.getNombre());
				reg.put("color", region.getColor());
				List<Coordenada> beans=regionSvc.getPoligono(region.getId());
				int cuentaCoordenadas=regionSvc.getPoligonoCuenta(region.getId());
				JSONArray datosCoordenadas=new JSONArray();
				for (Coordenada bean : beans) {
					Map<String, Object> fila=new HashMap<String, Object>();
					fila.put("latitud", bean.getLatitud());
					fila.put("longitud", bean.getLongitud());
					datosCoordenadas.put(fila);
				}
				JSONObject coordenadas=new JSONObject();
				coordenadas.put("total", cuentaCoordenadas);
				coordenadas.put("data", datosCoordenadas);
				reg.put("coordenadas", coordenadas);
				datos.put(reg);
			}
			res.put("data", datos);
			
			
			ModelAndView mav=new ModelAndView(new JSONView(res));
			
			return mav;					
			
				
		} else if (uri.endsWith("seleccionaDepartamentos")){
			
			String provinciaId=request.getParameter("provinciaId");
			
			JSONArray datos=new JSONArray();
			List<Departamento> beans=departamentoSvc.selecciona(provinciaId);
			int cuenta=departamentoSvc.cuenta(provinciaId);
			for (Departamento bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				fila.put("provinciaId", bean.getProvinciaId());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;
			
		} else if (uri.endsWith("seleccionaLocalidades")){
			
			String departamentoId=request.getParameter("departamentoId");
			
			JSONArray datos=new JSONArray();
			List<Localidad> beans=localidadSvc.selecciona(departamentoId);
			int cuenta=localidadSvc.cuenta(departamentoId);
			for (Localidad bean : beans) {
				Map<String, Object> fila=new HashMap<String, Object>();
				fila.put("id", bean.getId());
				fila.put("nombre", bean.getNombre());
				fila.put("departamentoId", bean.getDepartamentoId());
				datos.put(fila);
			}
			JSONObject job=new JSONObject();
			job.put("total", cuenta);
			job.put("data", datos);
			
			ModelAndView mav=new ModelAndView(new JSONView(job));
			return mav;			
			
		}else{
			throw new OperationNotSupportedException("La url " + uri + " no se puede procesar");
		}
	}

	public void setLocalidadSvc(LocalidadSvc localidadSvc) {
		this.localidadSvc = localidadSvc;
	}

}
