package ar.org.promeba.svc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.AreaGeografica;
import ar.org.promeba.dao.AreaGeograficaDao;

public class AreaGeograficaSvcImpl implements AreaGeograficaSvc {

	@Autowired
	private AreaGeograficaDao areaGeograficaDao;

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaGeograficaSvc#selecciona(int, int, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<AreaGeografica> selecciona(int desde, int hasta, String nombre, Date fechaDesde, Date fechaHasta){
		return this.areaGeograficaDao.selecciona(desde, hasta, nombre, fechaDesde, fechaHasta);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaGeograficaSvc#cuenta(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public int cuenta(String nombre, Date fechaDesde, Date fechaHasta){
		return this.areaGeograficaDao.cuenta(nombre, fechaDesde, fechaHasta);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaGeograficaSvc#obtiene(java.lang.String)
	 */
	@Override
	public AreaGeografica obtiene(String id){
		return this.areaGeograficaDao.obtiene(id);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaGeograficaSvc#inserta(ar.org.promeba.beans.AreaGeografica)
	 */
	@Override
	public void inserta(AreaGeografica bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.areaGeograficaDao.inserta(bean);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaGeograficaSvc#actualiza(ar.org.promeba.beans.AreaGeografica)
	 */
	@Override
	public void actualiza(AreaGeografica bean) {
		this.areaGeograficaDao.actualiza(bean);
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaGeograficaSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.areaGeograficaDao.borra(id);
	}
	
}
