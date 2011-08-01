package ar.org.promeba.beans;

public class Departamento {
	
	private String id;
	private String nombre;
	private String provinciaId;



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

	public String getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(String provinciaId) {
		this.provinciaId = provinciaId;
	}

}
