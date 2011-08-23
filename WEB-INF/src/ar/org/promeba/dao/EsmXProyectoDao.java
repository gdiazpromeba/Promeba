package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.EsmXProyecto;

public interface EsmXProyectoDao {

    public abstract void inserta(EsmXProyecto bean);

    public abstract void modifica(EsmXProyecto bean);

    public abstract List<EsmXProyecto> seleccionaEsmXProyecto(int offset, int limit, String solicitudId);

    public abstract int cuentaEsmXProyecto(String solicitudId);

    public abstract void borra(String id);

}