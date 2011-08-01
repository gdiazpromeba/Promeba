package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.EstadoMensura;

public interface EstadoMensuraSvc {

	public abstract List<EstadoMensura> selecciona(int desde, int hasta,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract EstadoMensura obtiene(String id);

	public abstract void inserta(EstadoMensura bean);

	public abstract void actualiza(EstadoMensura bean);

	public abstract void borra(String id);

}