
package ar.org.promeba.beans;

/**
 * los beanes de relación deben tener un id independiente, y datos mínimos de la
 * tabla secundaria en cuestión
 * 
 * @author Juan Martin Scaduto
 * @since 01/08/2011
 */
public class EsmXSolicitud {

    private String id;
    private String esmId;
    private String solicitudId;
    private String esmNombre;
    private String descripcion;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEsmId() {
        return esmId;
    }
    public void setEsmId(String esmId) {
        this.esmId = esmId;
    }
    public String getSolicitudId() {
        return solicitudId;
    }
    public void setSolicitudId(String solicitudId) {
        this.solicitudId = solicitudId;
    }
    public String getEsmNombre() {
        return esmNombre;
    }
    public void setEsmNombre(String esmNombre) {
        this.esmNombre = esmNombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    
    
}
