package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.PersonaFisica;

public interface PersonaFisicaSvc {

	public abstract List<PersonaFisica> selecciona(int desde, int hasta,
			String apellido);

	public abstract int cuenta(String apellido);

	public abstract PersonaFisica obtiene(String id);

	public abstract void inserta(PersonaFisica bean);

	public abstract void actualiza(PersonaFisica bean);

	public abstract void borra(String id);

}