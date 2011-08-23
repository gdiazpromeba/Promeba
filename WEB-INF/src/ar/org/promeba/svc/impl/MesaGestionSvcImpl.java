package ar.org.promeba.svc.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.MesaGestion;
import ar.org.promeba.dao.MesaGestionDao;
import ar.org.promeba.svc.MesaGestionSvc;

public class MesaGestionSvcImpl implements MesaGestionSvc     {

	@Autowired
	private MesaGestionDao mesaGestionDao;


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.MesaGestionSvc#selecciona(int, int, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<MesaGestion> selecciona(int desde, int hasta, Date fechaActaAcuerdo, Date fechaMesaGestion, String estado){
		return this.mesaGestionDao.selecciona(desde, hasta, fechaActaAcuerdo, fechaMesaGestion, estado);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.MesaGestionSvc#cuenta(java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public int cuenta(Date fechaActaAcuerdo, Date fechaMesaGestion, String estado){
		return this.mesaGestionDao.cuenta(fechaActaAcuerdo, fechaMesaGestion, estado);
	}

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.MesaGestionSvc#obtiene(java.lang.String)
	 */
	@Override
	public MesaGestion obtiene(String id){
		return this.mesaGestionDao.obtiene(id);
	}
	

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.MesaGestionSvc#inserta(ar.org.promeba.beans.MesaGestion)
	 */
	@Override
	public void inserta(MesaGestion bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.mesaGestionDao.inserta(bean);
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.MesaGestionSvc#actualiza(ar.org.promeba.beans.MesaGestion)
	 */
	@Override
	public void actualiza(MesaGestion bean) {
		this.mesaGestionDao.actualiza(bean);
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.MesaGestionSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.mesaGestionDao.borra(id);
	}
	
	
}
