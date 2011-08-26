package ar.org.promeba.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Proyecto {
	private String id;
	private String descripcion;
	private String solicitudId;
	private String solicitudDescripcion;
	private String subejecutorId;
	private String subejecutorNombre;
	private BigDecimal presupuestoEstimado;
	private int cantidadLotes;
	private String estado;
	private Date fechaDesde;
	private Date fechaHasta;
	private String situacionDominialId;
	private String situacionDominialNombre;
	private String tipoInversionNombre;
	private String tipoInversionId;
	private String mesaGestionId;
	
	public String getMesaGestionId() {
	    return mesaGestionId;
	}
	public void setMesaGestionId(String mesaGestionId) {
	    this.mesaGestionId = mesaGestionId;
	}
	public String getTipoInversionNombre() {
	    return tipoInversionNombre;
	}
	public void setTipoInversionNombre(String tipoInversionNombre) {
	    this.tipoInversionNombre = tipoInversionNombre;
	}
	public String getTipoInversionId() {
	    return tipoInversionId;
	}
	public void setTipoInversionId(String tipoInversionId) {
	    this.tipoInversionId = tipoInversionId;
	}
	public String getSituacionDominialId() {
	    return situacionDominialId;
	}
	public void setSituacionDominialId(String situacionDominialId) {
	    this.situacionDominialId = situacionDominialId;
	}
	public String getSituacionDominialNombre() {
	    return situacionDominialNombre;
	}
	public void setSituacionDominialNombre(String situacionDominialNombre) {
	    this.situacionDominialNombre = situacionDominialNombre;
	}
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



	public String getSubejecutorNombre() {
		return subejecutorNombre;
	}
	public void setSubejecutorNombre(String subejecutorNombre) {
		this.subejecutorNombre = subejecutorNombre;
	}
	public String getSolicitudId() {
	    return solicitudId;
	}
	public void setSolicitudId(String solicitudId) {
	    this.solicitudId = solicitudId;
	}
	public String getSolicitudDescripcion() {
	    return solicitudDescripcion;
	}
	public void setSolicitudDescripcion(String solicitudDescripcion) {
	    this.solicitudDescripcion = solicitudDescripcion;
	}

	
	
	
	
	
	

}
