package ar.org.promeba.beans;

/**
 * tipos de riesgo por solicitud
 * @author Gonzalo
 *
 */
public class TirXProyecto {
	private String id;
	private String tirId;
	private String proyectoId;
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
	public String getProyectoId() {
	    return proyectoId;
	}
	public void setProyectoId(String proyectoId) {
	    this.proyectoId = proyectoId;
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
