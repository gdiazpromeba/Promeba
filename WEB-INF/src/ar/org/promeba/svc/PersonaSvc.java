package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Persona;

public interface PersonaSvc {

	public abstract List<Persona> selecciona(int desde, int hasta,
			String nombre, boolean fisica, boolean juridica);

	public abstract int cuenta(String nombre, boolean fisica, boolean juridica);

	public abstract Persona obtiene(String id);

}