package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.ServicioPublicoDisponible;
import ar.org.promeba.dao.ServicioPublicoDisponibleDao;
import ar.org.promeba.svc.ServicioPublicoDisponibleSvc;

public class ServicioPublicoDisponibleSvcImpl implements ServicioPublicoDisponibleSvc   {

	@Autowired
	private ServicioPublicoDisponibleDao servicioPublicoDisponibleDao;


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ServicioPublicoDisponibleSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<ServicioPublicoDisponible> selecciona(int desde, int hasta, String nombre){
		return this.servicioPublicoDisponibleDao.selecciona(desde, hasta, nombre);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ServicioPublicoDisponibleSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.servicioPublicoDisponibleDao.cuenta(nombre);
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ServicioPublicoDisponibleSvc#obtiene(java.lang.String)
	 */
	@Override
	public ServicioPublicoDisponible obtiene(String id){
		return this.servicioPublicoDisponibleDao.obtiene(id);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ServicioPublicoDisponibleSvc#inserta(ar.org.promeba.beans.ServicioPublicoDisponible)
	 */
	@Override
	public void inserta(ServicioPublicoDisponible bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.servicioPublicoDisponibleDao.inserta(bean);
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ServicioPublicoDisponibleSvc#actualiza(ar.org.promeba.beans.ServicioPublicoDisponible)
	 */
	@Override
	public void actualiza(ServicioPublicoDisponible bean) {
		this.servicioPublicoDisponibleDao.actualiza(bean);
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.ServicioPublicoDisponibleSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.servicioPublicoDisponibleDao.borra(id);
	}
	
	
}
