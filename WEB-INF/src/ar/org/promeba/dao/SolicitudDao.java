package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Solicitud;

public interface SolicitudDao {

    public abstract void borra(String id);

    public abstract void inserta(Solicitud bean);

    public abstract void actualiza(Solicitud bean);

    public abstract List<Solicitud> selecciona(int start, int limit, String subejecutorId, String estado,
	    String provinciaId, String regionId);

    public abstract int cuenta(String subejecutorId, String estado, String provinciaId, String regionId);

    public abstract Solicitud obtiene(String id);

}