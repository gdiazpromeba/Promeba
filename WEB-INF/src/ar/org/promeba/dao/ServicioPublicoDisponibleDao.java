package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.ServicioPublicoDisponible;

public interface ServicioPublicoDisponibleDao {

	public abstract void borra(String id);

	public abstract void inserta(ServicioPublicoDisponible bean);

	public abstract void actualiza(ServicioPublicoDisponible bean);

	public abstract List<ServicioPublicoDisponible> selecciona(int start,
			int limit, String nombre);

	public abstract int cuenta(String nombre);

	public abstract ServicioPublicoDisponible obtiene(String id);

}