package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.AreaFuncionalXRol;
import ar.org.promeba.beans.Rol;
import ar.org.promeba.beans.Usuario;

public interface RolSvc {

	public abstract List<Rol> selecciona(int desde, int hasta, String nombre, String usuarioId);
	public abstract int cuenta(String nombre, String usuarioId);
	public abstract Rol obtiene(String id);
	public abstract void inserta(Rol rol);
	public abstract void actualiza(Rol rol);
	public abstract void borra(String id);
	public List<AreaFuncionalXRol> seleccionaAreasFuncionalesXRol(int offset, int limit, String rolId);
	public int cuentaAreasFuncionalesXRol(String rolId);
	


}