package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Coordenada;
import ar.org.promeba.beans.Region;
import ar.org.promeba.dao.PoligonoProvinciaDao;
import ar.org.promeba.dao.PoligonoRegionDao;
import ar.org.promeba.dao.RegionDao;

public class RegionSvcImpl implements RegionSvc {
	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private PoligonoRegionDao poligonoRegionDao;	
	
	@Override
	public void borra(String id) {
		regionDao.borra(id);

	}

	@Override
	public void inserta(Region bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		regionDao.inserta(bean);

	}

	@Override
	public void actualiza(Region bean) {
		regionDao.actualiza(bean);
	}

	@Override
	public List<Region> selecciona() {
		return regionDao.selecciona();
	}

	@Override
	public int cuenta() {
		return regionDao.cuenta();
	}


	@Override
	public Region obtiene(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Coordenada> getPoligono(String regionId){
		return poligonoRegionDao.selecciona(regionId);
	}
	
	@Override
	public int getPoligonoCuenta(String regionId){
		return poligonoRegionDao.cuenta(regionId);
	}	

}
