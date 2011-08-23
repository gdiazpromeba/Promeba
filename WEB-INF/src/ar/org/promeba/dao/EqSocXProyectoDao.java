package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.EqSocXProyecto;

public interface EqSocXProyectoDao {

    public abstract void inserta(EqSocXProyecto bean);

    public abstract void modifica(EqSocXProyecto bean);

    public abstract List<EqSocXProyecto> seleccionaEqSocXProyecto(int offset, int limit, String solicitudId);

    public abstract int cuentaEqSocXProyecto(String solicitudId);

    public abstract void borra(String id);

}