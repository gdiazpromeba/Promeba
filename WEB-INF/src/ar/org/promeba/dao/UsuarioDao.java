package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Usuario;

public interface UsuarioDao {
	
	public List<Usuario> selecciona(int desde, int hasta, String email, String rolId);
	public int cuenta(String email, String rolId);
	public void inserta(Usuario usuario);
	public void actualiza(Usuario usuario);
	public void borra(String id);
	public Usuario obtiene(String id);
	public Usuario verifica(String login, String clave);
	

}
