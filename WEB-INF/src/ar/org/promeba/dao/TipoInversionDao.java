package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.TipoInversion;

public interface TipoInversionDao {

	public abstract void borra(String id);

	public abstract void inserta(TipoInversion bean);

	public abstract void actualiza(TipoInversion bean);

	public abstract List<TipoInversion> selecciona(int start, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract TipoInversion obtiene(String id);

}