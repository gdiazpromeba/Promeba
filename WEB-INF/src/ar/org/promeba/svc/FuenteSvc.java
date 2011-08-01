package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Fuente;

public interface FuenteSvc {

	public abstract List<Fuente> selecciona(int desde, int hasta, String nombre);

	public abstract int cuenta(String nombre);

	public abstract Fuente obtiene(String id);

	public abstract void inserta(Fuente bean);

	public abstract void actualiza(Fuente bean);

	public abstract void borra(String id);

}