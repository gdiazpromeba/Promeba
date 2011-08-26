package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.TirXProyecto;

public interface TirXProyectoSvc {

    public abstract void inserta(TirXProyecto bean);

    /* (non-Javadoc)
     * @see ar.org.promeba.svc.TirXProyectoSvc#modifica(ar.org.promeba.beans.TirXProyecto)
     */
    public abstract void modifica(TirXProyecto bean);

    /* (non-Javadoc)
     * @see ar.org.promeba.svc.TirXProyectoSvc#seleccionaTirXProyecto(int, int, java.lang.String)
     */
    public abstract List<TirXProyecto> seleccionaTirXProyecto(int offset, int limit, String proyectoId);

    /* (non-Javadoc)
     * @see ar.org.promeba.svc.TirXProyectoSvc#cuentaTirXProyecto(java.lang.String)
     */
    public abstract int cuentaTirXProyecto(String proyectoId);

    /* (non-Javadoc)
     * @see ar.org.promeba.svc.TirXProyectoSvc#borra(java.lang.String)
     */
    public abstract void borra(String id);

}