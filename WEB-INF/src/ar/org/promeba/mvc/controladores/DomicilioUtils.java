package ar.org.promeba.mvc.controladores;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import ar.org.promeba.beans.Domicilio;

public class DomicilioUtils {

	/**
	 * puebla el mapa de una respuesta de controlado con un bean Domicilio
	 * @param fila
	 * @param bean
	 */
	public static void pueblaMap(Map<String, Object> fila, Domicilio bean){
		fila.put("domicilioId", bean.getId());
		fila.put("calle", bean.getCalle());
		fila.put("numero", bean.getNumero());
		fila.put("piso", bean.getPiso());
		fila.put("departamento", bean.getDepartamento());
		fila.put("tipoDomicilio", bean.getTipo());
		fila.put("regionId", bean.getRegionId());
		fila.put("regionNombre", bean.getRegionNombre());
		fila.put("provinciaId", bean.getProvinciaId());
		fila.put("provinciaNombre", bean.getProvinciaNombre());
		fila.put("departamentoId", bean.getDepartamentoId());
		fila.put("departamentoNombre", bean.getDepartamentoNombre());
		fila.put("localidadId", bean.getLocalidadId());
		fila.put("localidadNombre", bean.getLocalidadNombre());
		fila.put("codigoPostal", bean.getCodigoPostal());
		fila.put("barrio", bean.getBarrio());
		fila.put("manzana", bean.getManzana());
		fila.put("sector", bean.getSector());
  }
	
  public static void pueblaBeanConRequest(HttpServletRequest request, String prefijo, Domicilio bean){
	    String domicilioId=request.getParameter(prefijo + "domicilioId");
	    String calle=request.getParameter(prefijo + "calle");
		String numero=request.getParameter(prefijo + "numero");
		String piso=request.getParameter(prefijo + "piso");
		String departamento=request.getParameter(prefijo + "departamento");
		String tipoDomicilio=request.getParameter(prefijo + "comboTipoDomicilio");
		String regionId=request.getParameter(prefijo + "regionId");
		String provinciaId=request.getParameter(prefijo + "provinciaId");
		String departamentoId=request.getParameter(prefijo + "departamentoId");
		if (StringUtils.isBlank(departamentoId)){
			departamentoId=null;
		}
		String localidadId=request.getParameter(prefijo + "localidadId");
		if (StringUtils.isBlank(localidadId)){
			localidadId=null;
		}
		String codigoPostal=request.getParameter(prefijo + "codigoPostal");
		String barrio=request.getParameter(prefijo + "barrio");
		String manzana=request.getParameter(prefijo + "manzana");
		String sector=request.getParameter(prefijo + "sector");
		
		if (!StringUtils.isEmpty(domicilioId)){
			bean.setId(domicilioId);
		}
		bean.setCalle(calle);
		if (!StringUtils.isEmpty(numero) && StringUtils.isNumeric(numero)){
			bean.setNumero(Integer.parseInt(numero));
		}
		bean.setPiso(piso);
		bean.setDepartamento(departamento);
		bean.setTipo(tipoDomicilio);
		bean.setRegionId(regionId);
		bean.setProvinciaId(provinciaId);
		bean.setDepartamentoId(departamentoId);
		bean.setLocalidadId(localidadId);
		bean.setBarrio(barrio);
		bean.setCodigoPostal(codigoPostal);
		bean.setManzana(manzana);
		bean.setSector(sector);
	  
  }
	
}
