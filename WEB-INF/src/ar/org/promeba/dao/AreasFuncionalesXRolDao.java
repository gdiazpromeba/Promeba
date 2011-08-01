package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.AreaFuncionalXRol;


public interface AreasFuncionalesXRolDao {
	
	List<AreaFuncionalXRol> seleccionaAreasFuncionalesXRol(int offset, int limit, String rolId);
	List<AreaFuncionalXRol> areasFuncionalesXRolVisibles(int offset, int limit, String rolId);
	int cuentaAreasFuncionalesXRol(String rolId);
	int cuentaAreasFuncionalesXRolVisibles(String rolId);
	void inserta(AreaFuncionalXRol bean);
	void modifica(AreaFuncionalXRol bean);
	void borra(String id);
	
	
}
