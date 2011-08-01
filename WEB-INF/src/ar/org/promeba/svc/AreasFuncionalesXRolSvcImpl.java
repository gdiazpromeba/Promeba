package ar.org.promeba.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.AreaFuncionalXRol;
import ar.org.promeba.dao.AreasFuncionalesXRolDao;

public class AreasFuncionalesXRolSvcImpl implements AreasFuncionalesXRolSvc  {
	
	@Autowired
	private AreasFuncionalesXRolDao areasFuncionalesXRolDao;
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreasFuncionalesXRolSvc#inserta(ar.org.promeba.beans.AreaFuncionalXRol)
	 */
	@Override
	public void inserta(AreaFuncionalXRol bean) {
	  areasFuncionalesXRolDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreasFuncionalesXRolSvc#modifica(ar.org.promeba.beans.AreaFuncionalXRol)
	 */
	@Override
	public void modifica(AreaFuncionalXRol bean) {
		areasFuncionalesXRolDao.modifica(bean);
	}	
	

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreasFuncionalesXRolSvc#seleccionaAreasFuncionalesXRol(int, int, java.lang.String)
	 */
	@Override
	public List<AreaFuncionalXRol> seleccionaAreasFuncionalesXRol(int offset, int limit, String rolId) {
		return areasFuncionalesXRolDao.seleccionaAreasFuncionalesXRol(offset, limit, rolId);
	}	
	
	public List<AreaFuncionalXRol> areasFuncionalesXRolVisibles(int offset, int limit, String rolId) {
		return areasFuncionalesXRolDao.areasFuncionalesXRolVisibles(offset, limit, rolId);
	}		
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreasFuncionalesXRolSvc#cuentaAreasFuncionalesXRol(java.lang.String)
	 */
	@Override
	public int cuentaAreasFuncionalesXRol(String rolId) {
		return areasFuncionalesXRolDao.cuentaAreasFuncionalesXRol(rolId);
	}
	
	public int cuentaAreasFuncionalesXRolVisibles(String rolId) {
		return areasFuncionalesXRolDao.cuentaAreasFuncionalesXRolVisibles(rolId);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.AreasFuncionalesXRolSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		 areasFuncionalesXRolDao.borra(id);
    }	

}
