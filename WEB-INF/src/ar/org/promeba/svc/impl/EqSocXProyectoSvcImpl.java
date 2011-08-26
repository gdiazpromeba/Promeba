package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.EqSocXProyecto;
import ar.org.promeba.dao.EqSocXProyectoDao;
import ar.org.promeba.svc.EqSocXProyectoSvc;


public class EqSocXProyectoSvcImpl implements EqSocXProyectoSvc {
	

	@Autowired
	EqSocXProyectoDao eqSocXProyectoDao;
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EqSocXProyectoSvc#inserta(ar.org.promeba.beans.EqSocXProyecto)
	 */
	@Override
	public void inserta(EqSocXProyecto bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		eqSocXProyectoDao.inserta(bean);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EqSocXProyectoSvc#modifica(ar.org.promeba.beans.EqSocXProyecto)
	 */
	@Override
	public void modifica(EqSocXProyecto bean) {
		eqSocXProyectoDao.modifica(bean);
	}	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EqSocXProyectoSvc#seleccionaEqSocXProyecto(int, int, java.lang.String)
	 */
	@Override
	public List<EqSocXProyecto> seleccionaEqSocXProyecto(int offset, int limit, String proyectoId) {
		return eqSocXProyectoDao.seleccionaEqSocXProyecto(offset, limit, proyectoId);
	}	
	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EqSocXProyectoSvc#cuentaEqSocXProyecto(java.lang.String)
	 */
	@Override
	public int cuentaEqSocXProyecto(String proyectoId) {
		return eqSocXProyectoDao.cuentaEqSocXProyecto(proyectoId);
	}
	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.impl.EqSocXProyectoSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		eqSocXProyectoDao.borra(id);
    }	
	
	

}
