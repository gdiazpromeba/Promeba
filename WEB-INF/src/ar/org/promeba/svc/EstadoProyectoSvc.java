package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.EstadoProyecto;

public interface EstadoProyectoSvc {

	public abstract void borra(String id);

	public abstract void inserta(EstadoProyecto bean);

	public abstract void actualiza(EstadoProyecto bean);

	public abstract List<EstadoProyecto> selecciona(int start, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract EstadoProyecto obtiene(String id);

}