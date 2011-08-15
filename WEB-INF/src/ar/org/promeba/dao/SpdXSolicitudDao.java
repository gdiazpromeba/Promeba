package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.SpdXSolicitud;

public interface SpdXSolicitudDao {

    public abstract void inserta(SpdXSolicitud bean);

    public abstract void modifica(SpdXSolicitud bean);

    public abstract List<SpdXSolicitud> seleccionaSpdXSolicitud(int offset, int limit, String solicitudId);

    public abstract int cuentaSpdXSolicitud(String solicitudId);

    public abstract void borra(String id);

}