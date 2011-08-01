package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Cargo;

public interface CargoSvc {

	public abstract List<Cargo> selecciona(int desde, int hasta, String nombre);

	public abstract int cuenta(String nombre);

	public abstract Cargo obtiene(String id);

	public abstract void inserta(Cargo bean);

	public abstract void actualiza(Cargo bean);

	public abstract void borra(String id);

}