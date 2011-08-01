package ar.org.promeba.beans;

public class Localidad {
	
	private String id;
	private String nombre;
	private String departamentoId;



	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartmanetoId(String value) {
		this.departamentoId = value;
	}

}
