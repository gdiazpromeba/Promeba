package ar.org.promeba.oad;

import java.util.List;

import ar.org.promeba.beans.Usuario;

public interface UsuarioDao {
	
	public List<Usuario> selecciona(int desde, int hasta);
	public void inserta(Usuario usuario);
	public void actualiza(Usuario usuario);
	public void borra(String id);
	public Usuario obtiene(String id);

}
