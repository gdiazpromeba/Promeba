package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Domicilio;
import ar.org.promeba.beans.PersonaFisica;
import ar.org.promeba.dao.DomicilioDao;
import ar.org.promeba.dao.PersonaFisicaDao;
import ar.org.promeba.svc.PersonaFisicaSvc;

public class PersonaFisicaSvcImpl implements PersonaFisicaSvc {

	@Autowired
	private PersonaFisicaDao personaFisicaDao;
	private DomicilioDao domicilioDao;

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaFisicaSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<PersonaFisica> selecciona(int desde, int hasta, String apellido){
		return this.personaFisicaDao.selecciona(desde, hasta, apellido);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaFisicaSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String apellido) {
		return this.personaFisicaDao.cuenta(apellido);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaFisicaSvc#obtiene(java.lang.String)
	 */
	@Override
	public PersonaFisica obtiene(String id) {
		return this.personaFisicaDao.obtiene(id);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaFisicaSvc#inserta(ar.org.promeba.beans.PersonaFisica)
	 */
	@Override
	public void inserta(PersonaFisica bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		String uuidDomicilio=UUID.randomUUID().toString().substring(0, 32);
		Domicilio domicilio=bean.getDomicilio();
		domicilio.setId(uuidDomicilio);
		this.domicilioDao.inserta(bean.getDomicilio());
		this.personaFisicaDao.inserta(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaFisicaSvc#actualiza(ar.org.promeba.beans.PersonaFisica)
	 */
	@Override
	public void actualiza(PersonaFisica bean) {
		Domicilio dom=bean.getDomicilio();
		if (StringUtils.isEmpty(dom.getId())){
			String uuidDomicilio=UUID.randomUUID().toString().substring(0, 32);
			dom.setId(uuidDomicilio);
			this.domicilioDao.inserta(dom);
		}else{
			this.domicilioDao.modifica(dom);
		}
		this.personaFisicaDao.actualiza(bean);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.PersonaFisicaSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		PersonaFisica bean= this.personaFisicaDao.obtiene(id);
		String domicilioId=bean.getDomicilio().getId();
		this.personaFisicaDao.borra(id);
		this.domicilioDao.borra(domicilioId);
	}
	

	public void setDomicilioDao(DomicilioDao domicilioDao) {
		this.domicilioDao = domicilioDao;
	}
	

	
}
