package ar.org.promeba.svc;

import java.util.Date;
import java.util.List;

import ar.org.promeba.beans.AreaGeografica;

public interface AreaGeograficaSvc {

	public abstract List<AreaGeografica> selecciona(int desde, int hasta,
			String nombre, Date fechaDesde, Date fechaHasta);

	public abstract int cuenta(String nombre, Date fechaDesde, Date fechaHasta);

	public abstract AreaGeografica obtiene(String id);

	public abstract void inserta(AreaGeografica bean);

	public abstract void actualiza(AreaGeografica bean);

	public abstract void borra(String id);

}