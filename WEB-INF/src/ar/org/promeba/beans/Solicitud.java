package ar.org.promeba.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Solicitud {
	private String id;
	private String descripcion;
	private String subejecutorId;
	private String subejecutorNombre;
	private String link;
	private BigDecimal presupuestoEstimado;
	private int cantidadLotes;
	private String estado;
	private Date fechaDesde;
	private Date fechaHasta;
	private Date fechaIngresoPOA;
	private Date fechaIngresoPGEP;
	private Date fechaIngresoPA;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSubejecutorId() {
		return subejecutorId;
	}
	public void setSubejecutorId(String subejecutorId) {
		this.subejecutorId = subejecutorId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public BigDecimal getPresupuestoEstimado() {
		return presupuestoEstimado;
	}
	public void setPresupuestoEstimado(BigDecimal presupuestoEstimado) {
		this.presupuestoEstimado = presupuestoEstimado;
	}
	public int getCantidadLotes() {
		return cantidadLotes;
	}
	public void setCantidadLotes(int cantidadLotes) {
		this.cantidadLotes = cantidadLotes;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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


	public Date getFechaIngresoPGEP() {
		return fechaIngresoPGEP;
	}
	public void setFechaIngresoPGEP(Date fechaIngresoPGEP) {
		this.fechaIngresoPGEP = fechaIngresoPGEP;
	}
	public Date getFechaIngresoPA() {
		return fechaIngresoPA;
	}
	public void setFechaIngresoPA(Date fechaIngresoPA) {
		this.fechaIngresoPA = fechaIngresoPA;
	}
	public String getSubejecutorNombre() {
		return subejecutorNombre;
	}
	public void setSubejecutorNombre(String subejecutorNombre) {
		this.subejecutorNombre = subejecutorNombre;
	}
	public Date getFechaIngresoPOA() {
		return fechaIngresoPOA;
	}
	public void setFechaIngresoPOA(Date fechaIngresoPOA) {
		this.fechaIngresoPOA = fechaIngresoPOA;
	}
	
	
	
	
	
	

}
