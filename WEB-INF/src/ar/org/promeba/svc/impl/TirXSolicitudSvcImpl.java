package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.TirXSolicitud;
import ar.org.promeba.dao.TirXSolicitudDao;
import ar.org.promeba.svc.TirXSolicitudSvc;

public class TirXSolicitudSvcImpl implements TirXSolicitudSvc   {
	

	@Autowired
	TirXSolicitudDao tirXSolicitudDao;
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXSolicitudSvc#inserta(ar.org.promeba.beans.TirXSolicitud)
	 */
	@Override
	public void inserta(TirXSolicitud bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		tirXSolicitudDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXSolicitudSvc#modifica(ar.org.promeba.beans.TirXSolicitud)
	 */
	@Override
	public void modifica(TirXSolicitud bean) {
		tirXSolicitudDao.modifica(bean);
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXSolicitudSvc#seleccionaTirXSolicitud(int, int, java.lang.String)
	 */
	@Override
	public List<TirXSolicitud> seleccionaTirXSolicitud(int offset, int limit, String solicitudId) {
		return tirXSolicitudDao.seleccionaTirXSolicitud(offset, limit, solicitudId);
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXSolicitudSvc#cuentaTirXSolicitud(java.lang.String)
	 */
	@Override
	public int cuentaTirXSolicitud(String solicitudId) {
		return tirXSolicitudDao.cuentaTirXSolicitud(solicitudId);
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXSolicitudSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		tirXSolicitudDao.borra(id);
    }	
	
	

}
