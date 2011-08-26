package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.SpdXProyecto;
import ar.org.promeba.dao.SpdXProyectoDao;
import ar.org.promeba.svc.SpdXProyectoSvc;


public class SpdXProyectoSvcImpl implements SpdXProyectoSvc {
	

	@Autowired
	SpdXProyectoDao epdXProyectoDao;
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.SpdXProyectoSvc#inserta(ar.org.promeba.beans.SpdXProyecto)
	 */
	@Override
	public void inserta(SpdXProyecto bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		epdXProyectoDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.SpdXProyectoSvc#modifica(ar.org.promeba.beans.SpdXProyecto)
	 */
	@Override
	public void modifica(SpdXProyecto bean) {
	    epdXProyectoDao.modifica(bean);
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.SpdXProyectoSvc#seleccionaSpdXProyecto(int, int, java.lang.String)
	 */
	@Override
	public List<SpdXProyecto> seleccionaSpdXProyecto(int offset, int limit, String proyectoId) {
		return epdXProyectoDao.seleccionaSpdXProyecto(offset, limit, proyectoId);
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.SpdXProyectoSvc#cuentaSpdXProyecto(java.lang.String)
	 */
	@Override
	public int cuentaSpdXProyecto(String proyectoId) {
		return epdXProyectoDao.cuentaSpdXProyecto(proyectoId);
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.SpdXProyectoSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
	    epdXProyectoDao.borra(id);
    }	
	
	

}
