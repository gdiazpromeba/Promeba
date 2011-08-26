package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.EqSocXProyecto;

public interface EqSocXProyectoSvc {

    public abstract void inserta(EqSocXProyecto bean);

    public abstract void modifica(EqSocXProyecto bean);

    public abstract List<EqSocXProyecto> seleccionaEqSocXProyecto(int offset, int limit, String proyectoId);

    public abstract int cuentaEqSocXProyecto(String proyectoId);

    public abstract void borra(String id);

}