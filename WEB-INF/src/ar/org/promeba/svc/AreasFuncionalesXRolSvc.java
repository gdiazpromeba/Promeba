package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.AreaFuncionalXRol;

public interface AreasFuncionalesXRolSvc {

	public abstract void inserta(AreaFuncionalXRol bean);

	public abstract void modifica(AreaFuncionalXRol bean);

	public abstract List<AreaFuncionalXRol> seleccionaAreasFuncionalesXRol(
			int offset, int limit, String rolId);
	
	public abstract List<AreaFuncionalXRol> areasFuncionalesXRolVisibles(
			int offset, int limit, String rolId);

	public abstract int cuentaAreasFuncionalesXRol(String rolId);
	public abstract int cuentaAreasFuncionalesXRolVisibles(String rolId);

	public abstract void borra(String id);

}