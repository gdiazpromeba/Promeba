package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.SituacionDominial;

public interface SituacionDominialSvc {

	public abstract List<SituacionDominial> selecciona(int desde, int hasta,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract SituacionDominial obtiene(String id);

	public abstract void inserta(SituacionDominial bean);

	public abstract void actualiza(SituacionDominial bean);

	public abstract void borra(String id);

}