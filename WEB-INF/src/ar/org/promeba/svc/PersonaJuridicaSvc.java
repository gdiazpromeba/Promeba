package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.PersonaJuridica;

public interface PersonaJuridicaSvc {

	public abstract List<PersonaJuridica> selecciona(int desde, int hasta,
			String apellido);

	public abstract int cuenta(String apellido);

	public abstract PersonaJuridica obtiene(String id);

	public abstract void inserta(PersonaJuridica bean);

	public abstract void actualiza(PersonaJuridica bean);

	public abstract void borra(String id);

}