package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Solicitud;

public interface SolicitudSvc {

    public abstract List<Solicitud> selecciona(int desde, int hasta, String subejecutorId, String estado,
	    String provinciaId, String regionId);

    public abstract int cuenta(String subejecutorId, String usuarioId, String provinciaId, String regionId);

    public abstract Solicitud obtiene(String id);

    public abstract void inserta(Solicitud bean);

    public abstract void actualiza(Solicitud bean);

    public abstract void borra(String id);

}