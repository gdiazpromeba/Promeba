package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.EqSocXSolicitud;
import ar.org.promeba.dao.EqSocXSolicitudDao;
import ar.org.promeba.svc.EqSocXSolicitudSvc;

public class EqSocXSolicitudSvcImpl implements EqSocXSolicitudSvc  {
	

	@Autowired
	EqSocXSolicitudDao eqSocXSolicitudDao;
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EqSocXSolicitudSvc#inserta(ar.org.promeba.beans.EqSocXSolicitud)
	 */
	@Override
	public void inserta(EqSocXSolicitud bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		eqSocXSolicitudDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EqSocXSolicitudSvc#modifica(ar.org.promeba.beans.EqSocXSolicitud)
	 */
	@Override
	public void modifica(EqSocXSolicitud bean) {
		eqSocXSolicitudDao.modifica(bean);
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EqSocXSolicitudSvc#seleccionaEqSocXSolicitud(int, int, java.lang.String)
	 */
	@Override
	public List<EqSocXSolicitud> seleccionaEqSocXSolicitud(int offset, int limit, String solicitudId) {
		return eqSocXSolicitudDao.seleccionaEqSocXSolicitud(offset, limit, solicitudId);
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EqSocXSolicitudSvc#cuentaEqSocXSolicitud(java.lang.String)
	 */
	@Override
	public int cuentaEqSocXSolicitud(String solicitudId) {
		return eqSocXSolicitudDao.cuentaEqSocXSolicitud(solicitudId);
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EqSocXSolicitudSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		eqSocXSolicitudDao.borra(id);
    }	
	
	

}
