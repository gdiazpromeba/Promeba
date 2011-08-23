package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Proyecto;
import ar.org.promeba.dao.ProyectoDao;
import ar.org.promeba.svc.ProyectoSvc;

public class ProyectoSvcImpl implements ProyectoSvc {

	@Autowired
	private ProyectoDao proyectoDao;


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ProyectoSvc#selecciona(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Proyecto> selecciona(int desde, int hasta, String subejecutorId, String estado, String provinciaId, String regionId){
		return this.proyectoDao.selecciona(desde, hasta, subejecutorId, estado, provinciaId, regionId);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ProyectoSvc#cuenta(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int cuenta(String subejecutorId, String usuarioId, String provinciaId, String regionId){
		return this.proyectoDao.cuenta(subejecutorId, usuarioId, provinciaId, regionId);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ProyectoSvc#obtiene(java.lang.String)
	 */
	@Override
	public Proyecto obtiene(String id){
		return this.proyectoDao.obtiene(id);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ProyectoSvc#inserta(ar.org.promeba.beans.Proyecto)
	 */
	@Override
	public void inserta(Proyecto bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.proyectoDao.inserta(bean);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ProyectoSvc#actualiza(ar.org.promeba.beans.Proyecto)
	 */
	@Override
	public void actualiza(Proyecto bean) {
		this.proyectoDao.actualiza(bean);
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ProyectoSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.proyectoDao.borra(id);
	}
	
}
