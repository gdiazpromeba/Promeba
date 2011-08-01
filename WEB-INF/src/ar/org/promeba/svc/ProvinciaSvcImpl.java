package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Coordenada;
import ar.org.promeba.beans.Provincia;
import ar.org.promeba.dao.PoligonoProvinciaDao;
import ar.org.promeba.dao.ProvinciaDao;

public class ProvinciaSvcImpl implements ProvinciaSvc {

	@Autowired
	private ProvinciaDao provinciaDao;
	
	@Autowired
	private PoligonoProvinciaDao poligonoProvinciaDao;
	
	
	@Override
	public void borra(String id) {
		provinciaDao.borra(id);

	}

	@Override
	public void inserta(Provincia bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		provinciaDao.inserta(bean);
	}

	@Override
	public void actualiza(Provincia bean) {
		provinciaDao.actualiza(bean);

	}

	@Override
	public List<Provincia> selecciona(String regionId) {
		return provinciaDao.selecciona(regionId);
	}

	@Override
	public int cuenta(String regionId) {
		return provinciaDao.cuenta(regionId);
	}

	@Override
	public Provincia obtiene(String id) {
		return provinciaDao.obtiene(id);
	}
	
	@Override
	public List<Coordenada> getPoligono(String provinciaId){
		return poligonoProvinciaDao.selecciona(provinciaId);
	}
	
	@Override
	public int getPoligonoCuenta(String provinciaId){
		return poligonoProvinciaDao.cuenta(provinciaId);
	}

}
