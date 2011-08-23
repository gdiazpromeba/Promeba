package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ar.org.promeba.beans.EstadoMensura;
import ar.org.promeba.dao.EstadoMensuraDao;
import ar.org.promeba.svc.EstadoMensuraSvc;

public class EstadoMensuraSvcImpl implements EstadoMensuraSvc   {

    @Autowired
	private EstadoMensuraDao estadoMensuraDao;

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EstadoMensuraSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<EstadoMensura> selecciona(int desde, int hasta, String nombre){
		return this.estadoMensuraDao.selecciona(desde, hasta, nombre);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EstadoMensuraSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.estadoMensuraDao.cuenta(nombre);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EstadoMensuraSvc#obtiene(java.lang.String)
	 */
	@Override
	public EstadoMensura obtiene(String id){
		return this.estadoMensuraDao.obtiene(id);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EstadoMensuraSvc#inserta(ar.org.promeba.beans.EstadoMensura)
	 */
	@Override
	public void inserta(EstadoMensura bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.estadoMensuraDao.inserta(bean);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EstadoMensuraSvc#actualiza(ar.org.promeba.beans.EstadoMensura)
	 */
	@Override
	public void actualiza(EstadoMensura bean) {
		this.estadoMensuraDao.actualiza(bean);
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.EstadoMensuraSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.estadoMensuraDao.borra(id);
	}
	
	
}
