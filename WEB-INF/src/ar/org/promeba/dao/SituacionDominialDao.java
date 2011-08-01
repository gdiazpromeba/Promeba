package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.SituacionDominial;

public interface SituacionDominialDao {

	public abstract void borra(String id);

	public abstract void inserta(SituacionDominial bean);

	public abstract void actualiza(SituacionDominial bean);

	public abstract List<SituacionDominial> selecciona(int start, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract SituacionDominial obtiene(String id);

}