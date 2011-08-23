package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Solicitud;
import ar.org.promeba.dao.SolicitudDao;
import ar.org.promeba.svc.SolicitudSvc;

public class SolicitudSvcImpl implements SolicitudSvc    {

	@Autowired
	private SolicitudDao solicitudDao;

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SolicitudSvc#selecciona(int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Solicitud> selecciona(int desde, int hasta, String subejecutorId, String estado, String provinciaId, String regionId){
		return this.solicitudDao.selecciona(desde, hasta, subejecutorId, estado, provinciaId, regionId);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SolicitudSvc#cuenta(java.lang.String, java.lang.String)
	 */
	@Override
	public int cuenta(String subejecutorId, String usuarioId, String provinciaId, String regionId){
		return this.solicitudDao.cuenta(subejecutorId, usuarioId, provinciaId, regionId);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SolicitudSvc#obtiene(java.lang.String)
	 */
	@Override
	public Solicitud obtiene(String id){
		return this.solicitudDao.obtiene(id);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SolicitudSvc#inserta(ar.org.promeba.beans.Solicitud)
	 */
	@Override
	public void inserta(Solicitud bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.solicitudDao.inserta(bean);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SolicitudSvc#actualiza(ar.org.promeba.beans.Solicitud)
	 */
	@Override
	public void actualiza(Solicitud bean) {
		this.solicitudDao.actualiza(bean);
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SolicitudSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.solicitudDao.borra(id);
	}
	
}
