
package ar.org.promeba.beans;

/**
 * los beanes de relación deben tener un id independiente, y datos mínimos de la
 * tabla secundaria en cuestión
 * 
 * @author Juan Martin Scaduto
 * @since 01/08/2011
 */
public class SidXSolicitud {

    private String id;
    private String sidId;
    private String solicitudId;
    private String sidNombre;
    private String descripcion; 
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSidId() {
        return sidId;
    }
    public void setSidId(String sidId) {
        this.sidId = sidId;
    }
    public String getSolicitudId() {
        return solicitudId;
    }
    public void setSolicitudId(String solicitudId) {
        this.solicitudId = solicitudId;
    }
    public String getSidNombre() {
        return sidNombre;
    }
    public void setSidNombre(String sidNombre) {
        this.sidNombre = sidNombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
