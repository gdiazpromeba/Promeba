package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Proyecto;
import ar.org.promeba.beans.Solicitud;

public interface ProyectoDao {

    public abstract void borra(String id);

    public abstract void inserta(Proyecto bean);

    public abstract void actualiza(Proyecto bean);

    public abstract List<Proyecto> selecciona(int start, int limit, String subejecutorId, String estado,
	    String provinciaId, String regionId);

    public abstract int cuenta(String subejecutorId, String estado, String provinciaId, String regionId);

    public abstract Proyecto obtiene(String id);

}