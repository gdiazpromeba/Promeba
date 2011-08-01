package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Persona;

public interface PersonaDao {

	public abstract List<Persona> selecciona(int offset, int limit,
			String denominacion, boolean fisica, boolean juridica);

	public abstract int cuenta(String denominacion, boolean fisica,
			boolean juridica);

	public abstract Persona obtiene(String id);

}