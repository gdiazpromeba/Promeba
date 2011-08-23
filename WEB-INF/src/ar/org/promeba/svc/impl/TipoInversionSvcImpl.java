package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.TipoInversion;
import ar.org.promeba.dao.TipoInversionDao;
import ar.org.promeba.svc.TipoInversionSvc;

public class TipoInversionSvcImpl implements TipoInversionSvc    {

	@Autowired
	private TipoInversionDao tipoInversionDao;

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoInversionSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<TipoInversion> selecciona(int desde, int hasta, String nombre){
		return this.tipoInversionDao.selecciona(desde, hasta, nombre);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoInversionSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.tipoInversionDao.cuenta(nombre);
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoInversionSvc#obtiene(java.lang.String)
	 */
	@Override
	public TipoInversion obtiene(String id){
		return this.tipoInversionDao.obtiene(id);
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoInversionSvc#inserta(ar.org.promeba.beans.TipoInversion)
	 */
	@Override
	public void inserta(TipoInversion bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.tipoInversionDao.inserta(bean);
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoInversionSvc#actualiza(ar.org.promeba.beans.TipoInversion)
	 */
	@Override
	public void actualiza(TipoInversion bean) {
		this.tipoInversionDao.actualiza(bean);
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoInversionSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.tipoInversionDao.borra(id);
	}
	
	
}
