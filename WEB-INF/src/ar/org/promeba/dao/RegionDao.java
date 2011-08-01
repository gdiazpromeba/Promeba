package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Region;

public interface RegionDao {

	public abstract void borra(String id);

	public abstract void inserta(Region bean);

	public abstract void actualiza(Region bean);

	public abstract List<Region> selecciona();

	public abstract int cuenta();

	public abstract Region obtiene(String id);

}