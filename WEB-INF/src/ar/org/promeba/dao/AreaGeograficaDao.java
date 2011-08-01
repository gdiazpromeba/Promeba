package ar.org.promeba.dao;

import java.util.Date;
import java.util.List;

import ar.org.promeba.beans.AreaGeografica;

public interface AreaGeograficaDao {

	public abstract void borra(String id);

	public abstract void inserta(AreaGeografica bean);

	public abstract void actualiza(AreaGeografica bean);

	public abstract List<AreaGeografica> selecciona(int offset, int limit,
			String nombre, Date fechaDesde, Date fechaHasta);

	public abstract int cuenta(String nombre, Date fechaDesde, Date fechaHasta);

	public abstract AreaGeografica obtiene(String id);

}