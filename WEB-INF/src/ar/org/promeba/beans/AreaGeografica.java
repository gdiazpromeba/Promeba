package ar.org.promeba.beans;

import java.util.Date;

public class AreaGeografica {
	private String id;
	private String nombre;
	private Date fechaDesde;
	private Date fechaHasta;
	private String archivoGE;
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
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getArchivoGE() {
		return archivoGE;
	}
	public void setArchivoGE(String archivoGE) {
		this.archivoGE = archivoGE;
	}
	
	

}
