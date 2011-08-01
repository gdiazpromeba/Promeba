package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Provincia;

public interface ProvinciaDao {

	public abstract void borra(String id);

	public abstract void inserta(Provincia bean);

	public abstract void actualiza(Provincia bean);

	public abstract List<Provincia> selecciona(String areaGeograficaId);

	public abstract int cuenta(String areaGeograficaId);

	public abstract Provincia obtiene(String id);

}