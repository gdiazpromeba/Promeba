package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Rol;
import ar.org.promeba.beans.RolXUsuario;
import ar.org.promeba.beans.Usuario;


public interface RolesXUsuarioDao {
	
	public void inserta(RolXUsuario rolXUsuario);
	public void borra(String id);
	public List<RolXUsuario> seleccionaUsuariosXRol(int offset, int limit, String rolId);
	int cuentaUsuariosXRol(String rolId);
	public List<RolXUsuario> seleccionaRolesXUsuario(int offset, int limit, String usuarioId);
	int cuentaRolesXUsuario(String usuarioId);
	public boolean existe(String usuarioId, String rolId);
	
}
