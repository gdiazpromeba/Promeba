package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.EstadoProyecto;
import ar.org.promeba.dao.EstadoProyectoDao;

public class EstadoProyectoSvcImpl implements EstadoProyectoSvc  {

	@Autowired
	private EstadoProyectoDao estadoProyectoDao;

	public List<EstadoProyecto> selecciona(int desde, int hasta, String nombre){
		return this.estadoProyectoDao.selecciona(desde, hasta, nombre);
	}
	

	public int cuenta(String nombre){
		return this.estadoProyectoDao.cuenta(nombre);
	}

	

	public EstadoProyecto obtiene(String id){
		return this.estadoProyectoDao.obtiene(id);
	}
	


	public void inserta(EstadoProyecto bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		this.estadoProyectoDao.inserta(bean);
	}
	


	public void actualiza(EstadoProyecto bean) {
		this.estadoProyectoDao.actualiza(bean);
	}



	public void borra(String id) {
		this.estadoProyectoDao.borra(id);
	}
	
	
}
