package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.PersonaFisica;

public interface PersonaFisicaDao {

	public abstract void borra(String id);

	public abstract void inserta(PersonaFisica bean);

	public abstract void actualiza(PersonaFisica bean);

	public abstract List<PersonaFisica> selecciona(int offset, int limit,
			String apellido);

	public abstract int cuenta(String apellido);

	public abstract PersonaFisica obtiene(String id);

}