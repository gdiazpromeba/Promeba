package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.EquipamientoSocial;

public interface EquipamientoSocialSvc  {
	
	public void borra(String id);
	public void inserta(EquipamientoSocial bean);
	public void actualiza(EquipamientoSocial bean) ;
	public List<EquipamientoSocial> selecciona(int start, int limit, String nombre); 
	public int cuenta(String nombre); 
	public EquipamientoSocial obtiene(String id);
	


	

	

}
