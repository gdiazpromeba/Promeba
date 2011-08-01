package ar.org.promeba.beans;

import java.util.Date;

public class PersonaJuridica {
	
	String id;
	String nombre;
	Date fechaInscripcion;
	String personeria;
	String cuit;
	Domicilio domicilio;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getPersoneria() {
		return personeria;
	}
	public void setPersoneria(String personeria) {
		this.personeria = personeria;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
