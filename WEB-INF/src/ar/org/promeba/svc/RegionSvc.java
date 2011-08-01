package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Coordenada;
import ar.org.promeba.beans.Region;

public interface RegionSvc {

	public abstract void borra(String id);

	public abstract void inserta(Region bean);

	public abstract void actualiza(Region bean);

	public abstract List<Region> selecciona();

	public abstract int cuenta();
	public abstract Region obtiene(String id);
	

	public List<Coordenada> getPoligono(String regionId);
	
	public int getPoligonoCuenta(String regionId);

}