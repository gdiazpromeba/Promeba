package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.SituacionDominial;
import ar.org.promeba.dao.SituacionDominialDao;
import ar.org.promeba.svc.SituacionDominialSvc;

public class SituacionDominialSvcImpl implements SituacionDominialSvc    {

	@Autowired
	private SituacionDominialDao situacionDominialDao;


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SituacionDominialSvc#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<SituacionDominial> selecciona(int desde, int hasta, String nombre){
		return this.situacionDominialDao.selecciona(desde, hasta, nombre);
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SituacionDominialSvc#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){
		return this.situacionDominialDao.cuenta(nombre);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SituacionDominialSvc#obtiene(java.lang.String)
	 */
	@Override
	public SituacionDominial obtiene(String id){
		return this.situacionDominialDao.obtiene(id);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SituacionDominialSvc#inserta(ar.org.promeba.beans.SituacionDominial)
	 */
	@Override
	public void inserta(SituacionDominial bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.situacionDominialDao.inserta(bean);
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SituacionDominialSvc#actualiza(ar.org.promeba.beans.SituacionDominial)
	 */
	@Override
	public void actualiza(SituacionDominial bean) {
		this.situacionDominialDao.actualiza(bean);
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.SituacionDominialSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.situacionDominialDao.borra(id);
	}
	
	
}
