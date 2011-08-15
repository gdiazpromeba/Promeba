package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.EsmXSolicitud;
import ar.org.promeba.dao.EsmXSolicitudDao;

public class EsmXSolicitudSvcImpl implements EsmXSolicitudSvc {
	

	@Autowired
	EsmXSolicitudDao esmXSolicitudDao;
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EsmXSolicitudSvc#inserta(ar.org.promeba.beans.EsmXSolicitud)
	 */
	@Override
	public void inserta(EsmXSolicitud bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		esmXSolicitudDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EsmXSolicitudSvc#modifica(ar.org.promeba.beans.EsmXSolicitud)
	 */
	@Override
	public void modifica(EsmXSolicitud bean) {
		esmXSolicitudDao.modifica(bean);
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EsmXSolicitudSvc#seleccionaEsmXSolicitud(int, int, java.lang.String)
	 */
	@Override
	public List<EsmXSolicitud> seleccionaEsmXSolicitud(int offset, int limit, String solicitudId) {
		return esmXSolicitudDao.seleccionaEsmXSolicitud(offset, limit, solicitudId);
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EsmXSolicitudSvc#cuentaEsmXSolicitud(java.lang.String)
	 */
	@Override
	public int cuentaEsmXSolicitud(String solicitudId) {
		return esmXSolicitudDao.cuentaEsmXSolicitud(solicitudId);
	}
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EsmXSolicitudSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		esmXSolicitudDao.borra(id);
    }	
	
	

}
