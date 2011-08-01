package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Subejecutor;

public interface SubejecutorSvc {

	public abstract void borra(String id);

	public abstract void inserta(Subejecutor bean);

	public abstract void actualiza(Subejecutor bean);

	public abstract List<Subejecutor> selecciona(int start, int limit,
			String nombre, String regionId);

	public abstract int cuenta(String nombre, String regionId);

}