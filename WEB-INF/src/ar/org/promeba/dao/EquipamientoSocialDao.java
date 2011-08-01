package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.EquipamientoSocial;

public interface EquipamientoSocialDao {

	public abstract void borra(String id);

	public abstract void inserta(EquipamientoSocial bean);

	public abstract void actualiza(EquipamientoSocial bean);

	public abstract List<EquipamientoSocial> selecciona(int start, int limit,
			String nombre);

	public abstract int cuenta(String nombre);

	public abstract EquipamientoSocial obtiene(String id);

}