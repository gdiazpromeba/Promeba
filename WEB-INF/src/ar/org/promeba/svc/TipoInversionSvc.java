package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.TipoInversion;

public interface TipoInversionSvc {

	public abstract List<TipoInversion> selecciona(int desde, int hasta,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract TipoInversion obtiene(String id);

	public abstract void inserta(TipoInversion bean);

	public abstract void actualiza(TipoInversion bean);

	public abstract void borra(String id);

}