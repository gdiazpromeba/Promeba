package ar.org.promeba.beans;

import java.util.Date;

public class MesaGestion {
	
	private String id;
	private Date fechaActaAcuerdo;
	private Date fechaMesaGestion;
	private String estado;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFechaActaAcuerdo() {
		return fechaActaAcuerdo;
	}
	public void setFechaActaAcuerdo(Date fechaActaAcuerdo) {
		this.fechaActaAcuerdo = fechaActaAcuerdo;
	}
	public Date getFechaMesaGestion() {
		return fechaMesaGestion;
	}
	public void setFechaMesaGestion(Date fechaMesaGestion) {
		this.fechaMesaGestion = fechaMesaGestion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
