package ar.org.promeba.mvc.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class ControladorUtils {
	
	private static SimpleDateFormat  sdf=new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * intenta tomar un parámetro de la request, que viene como fecha 'dd/MM/yyyy', y 
	 * devuelve o bien un objeto util.Date, o bien null si el parámetro no está
	 * @param request
	 * @param param
	 * @return
	 */
	public static Date extraeParametroFecha(HttpServletRequest request, String param) throws ParseException{
		String sFecha=request.getParameter(param);
		Date fecha=null;
		if (StringUtils.isNotEmpty(sFecha)){
			fecha=sdf.parse(sFecha);
		}
		return fecha;
	}
	
	/**
	 * devuelve true si el parámetro existe, false si no
	 * @param request
	 * @param param
	 * @return
	 * @throws ParseException
	 */
	public static boolean extraeParametroBoolean(HttpServletRequest request, String param) throws ParseException{
		String sBoolean=request.getParameter(param);
		return  StringUtils.isNotEmpty(sBoolean);
	}

}
