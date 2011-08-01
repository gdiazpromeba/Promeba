package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Cargo;
import ar.org.promeba.dao.CargoDao;

public class CargoSvcImpl implements CargoSvc    {

	@Autowired
	private CargoDao cargoDao;

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.CargoSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<Cargo> selecciona(int desde, int hasta, String nombre){
		return this.cargoDao.selecciona(desde, hasta, nombre);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.CargoSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.cargoDao.cuenta(nombre);
	}

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.CargoSvc#obtiene(java.lang.String)
	 */
	@Override
	public Cargo obtiene(String id){
		return this.cargoDao.obtiene(id);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.CargoSvc#inserta(ar.org.promeba.beans.Cargo)
	 */
	@Override
	public void inserta(Cargo bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.cargoDao.inserta(bean);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.CargoSvc#actualiza(ar.org.promeba.beans.Cargo)
	 */
	@Override
	public void actualiza(Cargo bean) {
		this.cargoDao.actualiza(bean);
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.CargoSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.cargoDao.borra(id);
	}
	
	
}
