package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.TipoRiesgo;

public interface TipoRiesgoDao {

	public abstract void borra(String id);

	public abstract void inserta(TipoRiesgo bean);

	public abstract void actualiza(TipoRiesgo bean);

	public abstract List<TipoRiesgo> selecciona(int start, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract TipoRiesgo obtiene(String id);

}