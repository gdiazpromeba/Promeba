package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.TirXSolicitud;

public interface TirXSolicitudDao {

    public abstract void inserta(TirXSolicitud bean);

    public abstract void modifica(TirXSolicitud bean);

    public abstract List<TirXSolicitud> seleccionaTirXSolicitud(int offset, int limit, String solicitudId);

    public abstract int cuentaTirXSolicitud(String solicitudId);

    public abstract void borra(String id);

}