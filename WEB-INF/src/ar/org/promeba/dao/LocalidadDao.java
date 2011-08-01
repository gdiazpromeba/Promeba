package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Localidad;

public interface LocalidadDao {

	public abstract void borra(String id);

	public abstract void inserta(Localidad bean);

	public abstract void actualiza(Localidad bean);

	public abstract List<Localidad> selecciona(String departamentoId);

	public abstract int cuenta(String departamentoId);

	public abstract Localidad obtiene(String id);

}