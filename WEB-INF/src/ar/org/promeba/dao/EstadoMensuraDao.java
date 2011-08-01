package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.EstadoMensura;

public interface EstadoMensuraDao {

	public abstract void borra(String id);

	public abstract void inserta(EstadoMensura bean);

	public abstract void actualiza(EstadoMensura bean);

	public abstract List<EstadoMensura> selecciona(int start, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract EstadoMensura obtiene(String id);

}