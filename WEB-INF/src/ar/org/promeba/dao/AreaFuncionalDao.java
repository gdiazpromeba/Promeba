package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.AreaFuncional;

public interface AreaFuncionalDao {

	public abstract void borra(String id);

	public abstract void inserta(AreaFuncional bean);

	public abstract void actualiza(AreaFuncional bean);

	public abstract List<AreaFuncional> selecciona(int offset, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract AreaFuncional obtiene(String id);

}