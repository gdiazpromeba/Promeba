package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.SpdXProyecto;

public interface SpdXProyectoDao {

    public abstract void inserta(SpdXProyecto bean);

    public abstract void modifica(SpdXProyecto bean);

    public abstract List<SpdXProyecto> seleccionaSpdXProyecto(int offset, int limit, String solicitudId);

    public abstract int cuentaSpdXProyecto(String solicitudId);

    public abstract void borra(String id);

}