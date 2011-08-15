package ar.org.promeba.beans;

/**
 * tipos de riesgo por solicitud
 * @author Gonzalo
 *
 */
public class TirXSolicitud {
	private String id;
	private String tirId;
	private String solicitudId;
	private String tirNombre;
	private String descripcion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTirId() {
		return tirId;
	}
	public void setTirId(String tirId) {
		this.tirId = tirId;
	}
	public String getSolicitudId() {
		return solicitudId;
	}
	public void setSolicitudId(String solicitudId) {
		this.solicitudId = solicitudId;
	}
	public String getTirNombre() {
		return tirNombre;
	}
	public void setTirNombre(String tirNombre) {
		this.tirNombre = tirNombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
