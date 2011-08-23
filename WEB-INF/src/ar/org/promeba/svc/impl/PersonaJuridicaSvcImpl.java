package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Domicilio;
import ar.org.promeba.beans.PersonaJuridica;
import ar.org.promeba.dao.DomicilioDao;
import ar.org.promeba.dao.PersonaJuridicaDao;
import ar.org.promeba.svc.PersonaJuridicaSvc;

public class PersonaJuridicaSvcImpl implements PersonaJuridicaSvc {

	@Autowired
	private PersonaJuridicaDao personaJuridicaDao;
	private DomicilioDao domicilioDao;

	@Override
	public List<PersonaJuridica> selecciona(int desde, int hasta, String nombre){
		return this.personaJuridicaDao.selecciona(desde, hasta, nombre);
	}
	
	
	@Override
	public int cuenta(String nombre) {
		return this.personaJuridicaDao.cuenta(nombre);
	}
	
	
	@Override
	public PersonaJuridica obtiene(String id) {
		return this.personaJuridicaDao.obtiene(id);
	}
	
	
	@Override
	public void inserta(PersonaJuridica bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		String uuidDomicilio=UUID.randomUUID().toString().substring(0, 32);
		Domicilio domicilio=bean.getDomicilio();
		domicilio.setId(uuidDomicilio);
		this.domicilioDao.inserta(bean.getDomicilio());
		this.personaJuridicaDao.inserta(bean);
	}
	
	
	@Override
	public void actualiza(PersonaJuridica bean) {
		Domicilio dom=bean.getDomicilio();
		if (StringUtils.isEmpty(dom.getId())){
			String uuidDomicilio=UUID.randomUUID().toString().substring(0, 32);
			dom.setId(uuidDomicilio);
			this.domicilioDao.inserta(dom);
		}else{
			this.domicilioDao.modifica(dom);
		}
		this.personaJuridicaDao.actualiza(bean);
	}
	
	
	@Override
	public void borra(String id) {
		PersonaJuridica bean= this.personaJuridicaDao.obtiene(id);
		String domicilioId=bean.getDomicilio().getId();
		this.personaJuridicaDao.borra(id);
		this.domicilioDao.borra(domicilioId);
	}
	

	public void setDomicilioDao(DomicilioDao domicilioDao) {
		this.domicilioDao = domicilioDao;
	}
	

	
}
