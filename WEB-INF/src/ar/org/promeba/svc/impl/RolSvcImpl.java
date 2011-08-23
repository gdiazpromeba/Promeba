package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.AreaFuncionalXRol;
import ar.org.promeba.beans.Rol;
import ar.org.promeba.dao.AreasFuncionalesXRolDao;
import ar.org.promeba.dao.RolDao;
import ar.org.promeba.svc.RolSvc;

public class RolSvcImpl implements RolSvc   {

	@Autowired
	private RolDao rolDao;
	private AreasFuncionalesXRolDao  areasFuncionalesXRolDao;

	public void setAreasFuncionalesXRolDao(AreasFuncionalesXRolDao areasFuncionalesXRolDao) {
		this.areasFuncionalesXRolDao = areasFuncionalesXRolDao;
	}

	public List<Rol> selecciona(int desde, int hasta, String nombre, String usuarioId){
		return this.rolDao.selecciona(desde, hasta, nombre, usuarioId);
	}
	
	public int cuenta(String nombre, String usuarioId){
		return this.rolDao.cuenta(nombre, usuarioId);
	}

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.RolSvc#obtiene(java.lang.String)
	 */
	@Override
	public Rol obtiene(String id){
		return this.rolDao.obtiene(id);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.RolSvc#inserta(ar.org.promeba.beans.Rol)
	 */
	@Override
	public void inserta(Rol rol) {
		UUID uuid=UUID.randomUUID();
		rol.setId(uuid.toString().substring(0, 32));
		this.rolDao.inserta(rol);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.RolSvc#actualiza(ar.org.promeba.beans.Rol)
	 */
	@Override
	public void actualiza(Rol rol) {
		this.rolDao.actualiza(rol);
	}

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.RolSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.rolDao.borra(id);
	}
	
	public List<AreaFuncionalXRol> seleccionaAreasFuncionalesXRol(int offset, int limit, String rolId) {
		return this.areasFuncionalesXRolDao.seleccionaAreasFuncionalesXRol(offset, limit, rolId);
	}
	
	public int cuentaAreasFuncionalesXRol(String rolId) {
		return this.areasFuncionalesXRolDao.cuentaAreasFuncionalesXRol(rolId);
	}

	
}
