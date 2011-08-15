package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.EsmXSolicitud;

public interface EsmXSolicitudSvc {

    public abstract void inserta(EsmXSolicitud bean);

    public abstract void modifica(EsmXSolicitud bean);

    public abstract List<EsmXSolicitud> seleccionaEsmXSolicitud(int offset, int limit, String solicitudId);

    public abstract int cuentaEsmXSolicitud(String solicitudId);

    public abstract void borra(String id);

}