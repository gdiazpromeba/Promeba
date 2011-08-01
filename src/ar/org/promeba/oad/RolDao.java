package ar.org.promeba.oad;

import java.util.List;



public interface RolDao {

	public abstract void borra(String id);

	public abstract void inserta(ar.org.promeba.beans.Rol rol);

	public abstract void actualiza(ar.org.promeba.beans.Rol rol);

	public abstract List<ar.org.promeba.beans.Rol> selecciona(int offset, int limit);

	public abstract ar.org.promeba.beans.Rol obtiene(String id);

}