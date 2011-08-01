package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Domicilio;
import ar.org.promeba.beans.Rol;
import ar.org.promeba.beans.RolXUsuario;
import ar.org.promeba.beans.Usuario;

public interface UsuarioSvc {
	
	public List<Usuario> selecciona(int desde, int hasta, String email, String rolId);
	public int cuenta(String email, String rolId);
	public void inserta(Usuario usuario);
	public void actualiza(Usuario usuario);
	public void borra(String id);
	public Usuario obtiene(String id);
	/**
	 * agrega una relación usuario-rol
	 * @param usuarioId
	 * @param rolId
	 */
	public void agregaUsuarioRol(RolXUsuario rolXUsuario);
	
	/**
	 * borra una relación entre un rol y un usuario
	 * @param id  el id de la columna de relación
	 */
	public void borraUsuarioRol(String id);
	
	
	/**
	 * dado un usuario, selecciona los roles que ya le han sido asignados 
	 * @param offset
	 * @param limit
	 * @param usuarioId
	 * @return
	 */
	public List<RolXUsuario> rolesAsignados(int offset, int limit, String usuarioId);
	
	/**
	 * cuenta total de rolesAsignados
	 * @param usuarioId
	 * @return
	 */
	public int cuentaRolesAsignados(String usuarioId);
	
	/**
	 * determina si existe esa combinación de usuario y rol
	 * @param usuarioId
	 * @param rolId
	 * @return
	 */
	public boolean existe(String usuarioId, String rolId);
	
	/**
	 * para autenticación. Verifica si existe esa combinación de usuario y clave
	 * @param login
	 * @param clave
	 * @return
	 */
	public Usuario verifica(String login, String clave);
	
}