
package ar.org.promeba.beans;

/**
 * 
 * @author Gonzalo
 *
 */
public class SpdXSolicitud {

    private String id;
    private String spdId;
    private String solicitudId;
    private String spdNombre;
    private String descripcion; 
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSpdId() {
        return spdId;
    }
    public void setSpdId(String valor) {
        this.spdId = valor;
    }
    public String getSolicitudId() {
        return solicitudId;
    }
    public void setSolicitudId(String solicitudId) {
        this.solicitudId = solicitudId;
    }
    public String getSpdNombre() {
        return spdNombre;
    }
    public void setSpdNombre(String valor) {
        this.spdNombre = valor;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
