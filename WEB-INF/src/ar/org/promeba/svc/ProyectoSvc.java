package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.Proyecto;

public interface ProyectoSvc {

    public abstract List<Proyecto> selecciona(int desde, int hasta, String subejecutorId, String estado,
	    String provinciaId, String regionId);

    public abstract int cuenta(String subejecutorId, String usuarioId, String provinciaId, String regionId);

    public abstract Proyecto obtiene(String id);

    public abstract void inserta(Proyecto bean);

    public abstract void actualiza(Proyecto bean);

    public abstract void borra(String id);

}