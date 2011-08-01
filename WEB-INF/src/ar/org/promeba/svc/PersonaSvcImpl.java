package ar.org.promeba.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Persona;
import ar.org.promeba.dao.PersonaDao;

public class PersonaSvcImpl implements PersonaSvc  {

	@Autowired
	private PersonaDao personaDao;

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaSvc#selecciona(int, int, java.lang.String, boolean, boolean)
	 */
	@Override
	public List<Persona> selecciona(int desde, int hasta, String nombre, boolean fisica, boolean juridica){
		return this.personaDao.selecciona(desde, hasta, nombre, fisica, juridica);
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaSvc#cuenta(java.lang.String, boolean, boolean)
	 */
	@Override
	public int cuenta(String nombre, boolean fisica, boolean juridica){
		return this.personaDao.cuenta(nombre, fisica, juridica);
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaSvc#obtiene(java.lang.String)
	 */
	@Override
	public Persona obtiene(String id) {
		return this.personaDao.obtiene(id);
	}
	
	
	
}
