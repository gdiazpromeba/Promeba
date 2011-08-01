package ar.org.promeba.beans;

import java.util.Date;

public class Subejecutor {
	
	private String id;
	private String nombre;
	private Domicilio domicilio;
	private Persona persona;
	private String email;
	private String caracter;
	private String contacto;
	private Date fechaLegitimacion;
	private String convenio;
	
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
	public String getCaracter() {
		return caracter;
	}
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public Date getFechaLegitimacion() {
		return fechaLegitimacion;
	}
	public void setFechaLegitimacion(Date fechaLegitimacion) {
		this.fechaLegitimacion = fechaLegitimacion;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}


}
