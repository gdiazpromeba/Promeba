package ar.org.promeba.beans;


public class EqSocXProyecto {
	private String id;
	private String eqSocId;
	private String proyectoId;
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

	public String getProyectoId() {
	    return proyectoId;
	}
	public void setProyectoId(String proyectoId) {
	    this.proyectoId = proyectoId;
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
