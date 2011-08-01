package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.AreaFuncional;

public interface AreaFuncionalSvc {

	public abstract List<AreaFuncional> selecciona(int desde, int hasta,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract AreaFuncional obtiene(String id);

	public abstract void inserta(AreaFuncional bean);

	public abstract void actualiza(AreaFuncional bean);

	public abstract void borra(String id);

}