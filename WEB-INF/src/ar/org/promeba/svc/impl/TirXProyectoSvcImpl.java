package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.TirXProyecto;
import ar.org.promeba.dao.TirXProyectoDao;
import ar.org.promeba.svc.TirXProyectoSvc;


public class TirXProyectoSvcImpl implements TirXProyectoSvc {
	

	@Autowired
	TirXProyectoDao tirXProyectoDao;
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.TirXProyectoSvc#inserta(ar.org.promeba.beans.TirXProyecto)
	 */
	@Override
	public void inserta(TirXProyecto bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		tirXProyectoDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXProyectoSvc#modifica(ar.org.promeba.beans.TirXProyecto)
	 */
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.TirXProyectoSvc#modifica(ar.org.promeba.beans.TirXProyecto)
	 */
	@Override
	@Override
	public void modifica(TirXProyecto bean) {
		tirXProyectoDao.modifica(bean);
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXProyectoSvc#seleccionaTirXProyecto(int, int, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.TirXProyectoSvc#seleccionaTirXProyecto(int, int, java.lang.String)
	 */
	@Override
	@Override
	public List<TirXProyecto> seleccionaTirXProyecto(int offset, int limit, String proyectoId) {
		return tirXProyectoDao.seleccionaTirXProyecto(offset, limit, proyectoId);
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXProyectoSvc#cuentaTirXProyecto(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.TirXProyectoSvc#cuentaTirXProyecto(java.lang.String)
	 */
	@Override
	@Override
	public int cuentaTirXProyecto(String proyectoId) {
		return tirXProyectoDao.cuentaTirXProyecto(proyectoId);
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TirXProyectoSvc#borra(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.TirXProyectoSvc#borra(java.lang.String)
	 */
	@Override
	@Override
	public void borra(String id) {
		tirXProyectoDao.borra(id);
    }	
	
	

}
