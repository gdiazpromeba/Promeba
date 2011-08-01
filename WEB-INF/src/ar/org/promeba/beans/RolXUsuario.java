package ar.org.promeba.beans;

public class RolXUsuario {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsuarioLogin() {
		return usuarioLogin;
	}
	public void setUsuarioLogin(String valor) {
		this.usuarioLogin = valor;
	}
	public String getRolId() {
		return rolId;
	}
	public void setRolId(String rolId) {
		this.rolId = rolId;
	}
	public String getRolNombre() {
		return rolNombre;
	}
	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}
	private String id;
	private String usuarioId; 
	private String usuarioLogin;
	private String rolId;
	private String rolNombre;


}
