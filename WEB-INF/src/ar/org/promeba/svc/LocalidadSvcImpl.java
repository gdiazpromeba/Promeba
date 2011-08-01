package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Localidad;
import ar.org.promeba.dao.LocalidadDao;

public class LocalidadSvcImpl implements LocalidadSvc  {

	@Autowired
	private LocalidadDao localidadDao;
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.LocalidadSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		localidadDao.borra(id);

	}

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.LocalidadSvc#inserta(ar.org.promeba.beans.Localidad)
	 */
	@Override
	public void inserta(Localidad bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		localidadDao.inserta(bean);
	}

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.LocalidadSvc#actualiza(ar.org.promeba.beans.Localidad)
	 */
	@Override
	public void actualiza(Localidad bean) {
		localidadDao.actualiza(bean);

	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.LocalidadSvc#selecciona(java.lang.String)
	 */
	@Override
	public List<Localidad> selecciona(String departamentoId) {
		return localidadDao.selecciona(departamentoId);
	}

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.LocalidadSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String departamentoId) {
		return localidadDao.cuenta(departamentoId);
	}

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.LocalidadSvc#obtiene(java.lang.String)
	 */
	@Override
	public Localidad obtiene(String id) {
		return localidadDao.obtiene(id);
	}

}
