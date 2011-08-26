package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.SpdXProyecto;

public interface SpdXProyectoSvc {

    public abstract void inserta(SpdXProyecto bean);

    public abstract void modifica(SpdXProyecto bean);

    public abstract List<SpdXProyecto> seleccionaSpdXProyecto(int offset, int limit, String proyectoId);

    public abstract int cuentaSpdXProyecto(String proyectoId);

    public abstract void borra(String id);

}