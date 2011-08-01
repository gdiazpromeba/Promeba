package ar.org.promeba.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.EquipamientoSocial;
import ar.org.promeba.dao.EquipamientoSocialDao;

public class EquipamientoSocialSvcImpl implements EquipamientoSocialSvc    {
	
	@Autowired
	private EquipamientoSocialDao equipamientoSocialDao;
	
	public void borra(String id) {
		equipamientoSocialDao.borra(id);
	}	


	public void inserta(EquipamientoSocial bean) {
	  equipamientoSocialDao.inserta(bean);
	}	

	public void actualiza(EquipamientoSocial bean) {
		equipamientoSocialDao.actualiza(bean);
	}	
	
	
	public List<EquipamientoSocial> selecciona(int start, int limit, String nombre){
		return equipamientoSocialDao.selecciona(start, limit, nombre);
	}


	public int cuenta(String nombre){ 
		return equipamientoSocialDao.cuenta(nombre);
	}



	public EquipamientoSocial obtiene(String id) {
		return equipamientoSocialDao.obtiene(id);
	}	
	


	

	

}
