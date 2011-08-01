package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.TipoRiesgo;

public interface TipoRiesgoSvc {

	public abstract List<TipoRiesgo> selecciona(int desde, int hasta,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract TipoRiesgo obtiene(String id);

	public abstract void inserta(TipoRiesgo bean);

	public abstract void actualiza(TipoRiesgo bean);

	public abstract void borra(String id);

}