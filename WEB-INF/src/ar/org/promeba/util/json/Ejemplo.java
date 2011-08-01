package ar.org.promeba.util.json;

import java.util.HashMap;
import java.util.Map;

public class Ejemplo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws JSONException{
		JSONObject job=new JSONObject();
		job.put("clave1", "valor1");
		
		
		
		Map map=new HashMap();
		map.put("A", "a");
		map.put("V", "b");
		map.put("C", "c");
		
//		JSONArray jar=new JSONArray(list);
//		job.put("otro", jar);
		job.put("mapa", map);
		System.out.println(job.toString(2));

	}

}
