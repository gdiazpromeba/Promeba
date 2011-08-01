package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.AreaFuncionalXRol;
import ar.org.promeba.beans.Rol;



public interface RolDao {

	public abstract void borra(String id);
	public abstract void inserta(ar.org.promeba.beans.Rol rol);
	public abstract void actualiza(ar.org.promeba.beans.Rol rol);
	public List<Rol> selecciona(int offset, int limit, String nombre, String usuarioId);
	public int cuenta(String nombre, String usuarioId);
	public abstract Rol obtiene(String id);

}