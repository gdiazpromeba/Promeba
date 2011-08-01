package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Cargo;

public interface CargoDao {

	public abstract void borra(String id);

	public abstract void inserta(Cargo bean);

	public abstract void actualiza(Cargo bean);

	public abstract List<Cargo> selecciona(int start, int limit, String nombre);

	public abstract int cuenta(String nombre);

	public abstract Cargo obtiene(String id);

}