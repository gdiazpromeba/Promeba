package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.ServicioPublicoDisponible;

public interface ServicioPublicoDisponibleSvc {

	public abstract List<ServicioPublicoDisponible> selecciona(int desde,
			int hasta, String nombre);

	public abstract int cuenta(String nombre);

	public abstract ServicioPublicoDisponible obtiene(String id);

	public abstract void inserta(ServicioPublicoDisponible bean);

	public abstract void actualiza(ServicioPublicoDisponible bean);

	public abstract void borra(String id);

}