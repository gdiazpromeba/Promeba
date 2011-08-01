package ar.org.promeba.beans;

/**
 * los beanes de relación deben tener un id independiente, y datos mínimos de la
 * tabla secundaria en cuestión
 * @author gonzalo
 *
 */
public class EqSocXSolicitud {
	private String id;
	private String eqSocId;
	private String solicitudId;
	private String eqSocNombre;
	private String descripcion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEqSocId() {
		return eqSocId;
	}
	public void setEqSocId(String eqSocId) {
		this.eqSocId = eqSocId;
	}
	public String getSolicitudId() {
		return solicitudId;
	}
	public void setSolicitudId(String solicitudId) {
		this.solicitudId = solicitudId;
	}
	public String getEqSocNombre() {
		return eqSocNombre;
	}
	public void setEqSocNombre(String eqSocNombre) {
		this.eqSocNombre = eqSocNombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
