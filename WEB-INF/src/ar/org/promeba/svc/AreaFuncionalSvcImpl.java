package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.AreaFuncional;
import ar.org.promeba.dao.AreaFuncionalDao;

public class AreaFuncionalSvcImpl implements AreaFuncionalSvc {

	@Autowired
	private AreaFuncionalDao areaFuncionalDao;

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaFuncionalSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<AreaFuncional> selecciona(int desde, int hasta, String nombre){
		return this.areaFuncionalDao.selecciona(desde, hasta, nombre);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaFuncionalSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.areaFuncionalDao.cuenta(nombre);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaFuncionalSvc#obtiene(java.lang.String)
	 */
	@Override
	public AreaFuncional obtiene(String id){
		return this.areaFuncionalDao.obtiene(id);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaFuncionalSvc#inserta(ar.org.promeba.beans.AreaFuncional)
	 */
	@Override
	public void inserta(AreaFuncional bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.areaFuncionalDao.inserta(bean);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaFuncionalSvc#actualiza(ar.org.promeba.beans.AreaFuncional)
	 */
	@Override
	public void actualiza(AreaFuncional bean) {
		this.areaFuncionalDao.actualiza(bean);
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreaFuncionalSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.areaFuncionalDao.borra(id);
	}
	
}
