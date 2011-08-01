package ar.org.promeba.svc;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.RolXUsuario;
import ar.org.promeba.beans.Usuario;
import ar.org.promeba.dao.RolesXUsuarioDao;
import ar.org.promeba.dao.UsuarioDao;

public class UsuarioSvcImpl implements UsuarioSvc {

	@Autowired
	private UsuarioDao usuarioDao;
	private RolesXUsuarioDao rolesXUsuarioDao;

	
	public void setRolesXUsuarioDao(RolesXUsuarioDao rolesXUsuarioDao) {
		this.rolesXUsuarioDao = rolesXUsuarioDao;
	}
	
	
	
	public List<Usuario> selecciona(int desde, int hasta, String email, String rolId){
		return this.usuarioDao.selecciona(desde, hasta, email, rolId);
	}
	
	public int cuenta(String email, String rolId) {
		return this.usuarioDao.cuenta(email, rolId);
	}
	
	public Usuario obtiene(String id) {
		return this.usuarioDao.obtiene(id);
	}
	
	public void inserta(Usuario usuario) {
		UUID uuid=UUID.randomUUID();
		usuario.setId(uuid.toString().substring(0, 32));
		usuario.setHabilitado(true);
		this.usuarioDao.inserta(usuario);
	}
	
	public void actualiza(Usuario usuario) {
		this.usuarioDao.actualiza(usuario);
	}
	
	public void borra(String id) {
		Usuario usu= this.usuarioDao.obtiene(id);
		this.usuarioDao.borra(id);
	}
	
	/**
	 * agrega una relación usuario-rol
	 * @param usuarioId
	 * @param rolId
	 */
	public void agregaUsuarioRol(RolXUsuario rolXUsuario){
		UUID uuid=UUID.randomUUID();
		rolXUsuario.setId(uuid.toString().substring(0, 32));
		this.rolesXUsuarioDao.inserta(rolXUsuario);
	}
	
	public void borraUsuarioRol(String id){
		this.rolesXUsuarioDao.borra(id);
	}
	
	


	
	public List<RolXUsuario> rolesAsignados(int offset, int limit, String usuarioId){
		return this.rolesXUsuarioDao.seleccionaRolesXUsuario(offset, limit, usuarioId);
	}
	
	public int cuentaRolesAsignados(String usuarioId){
		return this.rolesXUsuarioDao.cuentaRolesXUsuario(usuarioId);
	}

	public Usuario verifica(String login, String clave) {
		return this.usuarioDao.verifica(login, clave);
	}
	
	public boolean existe(String usuarioId, String rolId) {
		return this.rolesXUsuarioDao.existe(usuarioId, rolId);
	}

	

	
}
