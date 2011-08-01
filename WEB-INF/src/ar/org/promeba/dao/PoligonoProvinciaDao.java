package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Coordenada;

public interface PoligonoProvinciaDao {

	public abstract void borra(String id);

	public abstract void inserta(String pk, String provinciaId,
			Coordenada bean, int orden);

	public abstract void modifica(String pk, String provinciaId,
			Coordenada bean, int orden);

	public abstract Coordenada obtiene(String id);

	public abstract List<Coordenada> selecciona(String provinciaId);
	
	int cuenta(String provinciaId);

}