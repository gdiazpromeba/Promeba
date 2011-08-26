package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.EsmXProyecto;
import ar.org.promeba.dao.EsmXProyectoDao;
import ar.org.promeba.svc.EsmXProyectoSvc;


public class EsmXProyectoSvcImpl implements EsmXProyectoSvc  {
	

	@Autowired
	EsmXProyectoDao esmXProyectoDao;
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EsmXProyectoSvc#inserta(ar.org.promeba.beans.EsmXProyecto)
	 */
	@Override
	public void inserta(EsmXProyecto bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		esmXProyectoDao.inserta(bean);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EsmXProyectoSvc#modifica(ar.org.promeba.beans.EsmXProyecto)
	 */
	@Override
	public void modifica(EsmXProyecto bean) {
		esmXProyectoDao.modifica(bean);
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EsmXProyectoSvc#seleccionaEsmXProyecto(int, int, java.lang.String)
	 */
	@Override
	public List<EsmXProyecto> seleccionaEsmXProyecto(int offset, int limit, String proyectoId) {
		return esmXProyectoDao.seleccionaEsmXProyecto(offset, limit, proyectoId);
	}	
	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EsmXProyectoSvc#cuentaEsmXProyecto(java.lang.String)
	 */
	@Override
	public int cuentaEsmXProyecto(String proyectoId) {
		return esmXProyectoDao.cuentaEsmXProyecto(proyectoId);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EsmXProyectoSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		esmXProyectoDao.borra(id);
    }	
	
	

}
