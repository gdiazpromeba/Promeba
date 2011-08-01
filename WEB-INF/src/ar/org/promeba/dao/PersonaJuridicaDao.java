package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.PersonaJuridica;

public interface PersonaJuridicaDao {

	public abstract void borra(String id);

	public abstract void inserta(PersonaJuridica bean);

	public abstract void actualiza(PersonaJuridica bean);

	public abstract List<PersonaJuridica> selecciona(int offset, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract PersonaJuridica obtiene(String id);

}