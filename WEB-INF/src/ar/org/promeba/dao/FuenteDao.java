package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Fuente;

public interface FuenteDao {

	public abstract void borra(String id);

	public abstract void inserta(Fuente bean);

	public abstract void actualiza(Fuente bean);

	public abstract List<Fuente> selecciona(int start, int limit, String nombre);

	public abstract int cuenta(String nombre);

	public abstract Fuente obtiene(String id);

}