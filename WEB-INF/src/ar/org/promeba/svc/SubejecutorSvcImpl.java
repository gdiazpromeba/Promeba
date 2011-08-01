package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Subejecutor;
import ar.org.promeba.dao.DomicilioDao;
import ar.org.promeba.dao.SubejecutorDao;

public class SubejecutorSvcImpl implements SubejecutorSvc {
	
	@Autowired
	private SubejecutorDao subejecutorDao;

	@Override
	public void borra(String id) {
		this.subejecutorDao.borra(id);
	}

	@Override
	public void inserta(Subejecutor bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.subejecutorDao.inserta(bean);
		
	}

	@Override
	public void actualiza(Subejecutor bean) {
	  this.subejecutorDao.actualiza(bean);
	}

	@Override
	public List<Subejecutor> selecciona(int start, int limit, String nombre,
			String regionId) {
		return subejecutorDao.selecciona(start, limit, nombre, regionId);
	}

	@Override
	public int cuenta(String nombre, String regionId) {
		return subejecutorDao.cuenta(nombre, regionId);
	}



}
