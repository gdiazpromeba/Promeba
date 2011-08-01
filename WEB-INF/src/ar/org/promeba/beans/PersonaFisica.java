package ar.org.promeba.beans;

import java.util.Date;

public class PersonaFisica {
	
	private String id;
	private String nombre;
	private String apellido;
	private Domicilio domicilio;
	private TipoDocumento tipoDocumento;
	private long documentoNumero;
	private String ocupacion;
	private String sexo;
	private Date nacimiento;
	
	
	public long getDocumentoNumero() {
		return documentoNumero;
	}
	public void setDocumentoNumero(long documentoNumero) {
		this.documentoNumero = documentoNumero;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	
	

}
