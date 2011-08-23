package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.TipoRiesgo;
import ar.org.promeba.dao.TipoRiesgoDao;
import ar.org.promeba.svc.TipoRiesgoSvc;

public class TipoRiesgoSvcImpl implements TipoRiesgoSvc   {

	@Autowired
	private TipoRiesgoDao tipoRiesgoDao;

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoRiesgoSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<TipoRiesgo> selecciona(int desde, int hasta, String nombre){
		return this.tipoRiesgoDao.selecciona(desde, hasta, nombre);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoRiesgoSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.tipoRiesgoDao.cuenta(nombre);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoRiesgoSvc#obtiene(java.lang.String)
	 */
	@Override
	public TipoRiesgo obtiene(String id){
		return this.tipoRiesgoDao.obtiene(id);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoRiesgoSvc#inserta(ar.org.promeba.beans.TipoRiesgo)
	 */
	@Override
	public void inserta(TipoRiesgo bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.tipoRiesgoDao.inserta(bean);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoRiesgoSvc#actualiza(ar.org.promeba.beans.TipoRiesgo)
	 */
	@Override
	public void actualiza(TipoRiesgo bean) {
		this.tipoRiesgoDao.actualiza(bean);
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoRiesgoSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.tipoRiesgoDao.borra(id);
	}
	
	
}
