package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.TipoDocumento;
import ar.org.promeba.dao.TipoDocumentoDao;

public class TipoDocumentoSvcImpl implements TipoDocumentoSvc  {

	@Autowired
	private TipoDocumentoDao tipoDocumentoDao;

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoDocumentoSvc#selecciona(int, int)
	 */
	@Override
	public List<TipoDocumento> selecciona(int desde, int hasta){
		return this.tipoDocumentoDao.selecciona(desde, hasta);
	}
	
	public TipoDocumento obtiene(String id){
		return this.tipoDocumentoDao.obtiene(id);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoDocumentoSvc#inserta(ar.org.promeba.beans.TipoDocumento)
	 */
	@Override
	public void inserta(TipoDocumento tipoDocumento) {
		UUID uuid=UUID.randomUUID();
		tipoDocumento.setId(uuid.toString().substring(0, 32));
		this.tipoDocumentoDao.inserta(tipoDocumento);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoDocumentoSvc#actualiza(ar.org.promeba.beans.TipoDocumento)
	 */
	@Override
	public void actualiza(TipoDocumento usuario) {
		this.tipoDocumentoDao.actualiza(usuario);
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.svc.TipoDocumentoSvc#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		this.tipoDocumentoDao.borra(id);
	}
	
}
