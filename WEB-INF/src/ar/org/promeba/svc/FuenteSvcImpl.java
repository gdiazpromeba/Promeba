package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Fuente;
import ar.org.promeba.dao.FuenteDao;

public class FuenteSvcImpl implements FuenteSvc     {

	@Autowired
	private FuenteDao fuenteDao;
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.FuenteSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<Fuente> selecciona(int desde, int hasta, String nombre){
		return this.fuenteDao.selecciona(desde, hasta, nombre);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.FuenteSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.fuenteDao.cuenta(nombre);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.FuenteSvc#obtiene(java.lang.String)
	 */
	@Override
	public Fuente obtiene(String id){
		return this.fuenteDao.obtiene(id);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.FuenteSvc#inserta(ar.org.promeba.beans.Fuente)
	 */
	@Override
	public void inserta(Fuente bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.fuenteDao.inserta(bean);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.FuenteSvc#actualiza(ar.org.promeba.beans.Fuente)
	 */
	@Override
	public void actualiza(Fuente bean) {
		this.fuenteDao.actualiza(bean);
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.FuenteSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.fuenteDao.borra(id);
	}
	
	
}
