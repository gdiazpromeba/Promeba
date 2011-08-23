package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.SpdXSolicitud;
import ar.org.promeba.dao.SpdXSolicitudDao;
import ar.org.promeba.svc.SpdXSolicitudSvc;

public class SpdXSolicitudSvcImpl implements SpdXSolicitudSvc {
	

	@Autowired
	SpdXSolicitudDao epdXSolicitudDao;
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SpdXSolicitudSvc#inserta(ar.org.promeba.beans.SpdXSolicitud)
	 */
	@Override
	public void inserta(SpdXSolicitud bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		epdXSolicitudDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SpdXSolicitudSvc#modifica(ar.org.promeba.beans.SpdXSolicitud)
	 */
	@Override
	public void modifica(SpdXSolicitud bean) {
	    epdXSolicitudDao.modifica(bean);
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SpdXSolicitudSvc#seleccionaSpdXSolicitud(int, int, java.lang.String)
	 */
	@Override
	public List<SpdXSolicitud> seleccionaSpdXSolicitud(int offset, int limit, String solicitudId) {
		return epdXSolicitudDao.seleccionaSpdXSolicitud(offset, limit, solicitudId);
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SpdXSolicitudSvc#cuentaSpdXSolicitud(java.lang.String)
	 */
	@Override
	public int cuentaSpdXSolicitud(String solicitudId) {
		return epdXSolicitudDao.cuentaSpdXSolicitud(solicitudId);
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SpdXSolicitudSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
	    epdXSolicitudDao.borra(id);
    }	
	
	

}
