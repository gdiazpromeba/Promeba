package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.EsmXProyecto;

public interface EsmXProyectoSvc {

    public abstract void inserta(EsmXProyecto bean);

    public abstract void modifica(EsmXProyecto bean);

    public abstract List<EsmXProyecto> seleccionaEsmXProyecto(int offset, int limit, String proyectoId);

    public abstract int cuentaEsmXProyecto(String proyectoId);

    public abstract void borra(String id);

}