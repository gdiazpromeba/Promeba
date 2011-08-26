package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.TirXProyecto;

public interface TirXProyectoDao {

    public abstract void inserta(TirXProyecto bean);

    public abstract void modifica(TirXProyecto bean);

    public abstract List<TirXProyecto> seleccionaTirXProyecto(int offset, int limit, String proyectoId);

    public abstract int cuentaTirXProyecto(String proyectoId);

    public abstract void borra(String id);

}