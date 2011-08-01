package ar.org.promeba.beans;

public class Domicilio {
	
	  private String id;
	  private String regionId;
	  private String regionNombre;
	  private String provinciaId;
	  private String provinciaNombre;
	  private String departamentoId;
	  private String departamentoNombre;
	  private String localidadId;
	  private String localidadNombre;
	  private String calle;
	  private int numero;
	  private String piso;
	  private String departamento;
	  private String tipo;
	  private String codigoPostal;
	  private String barrio;
	  private String manzana;
	  private String sector;
	  
	  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getProvinciaId() {
		return provinciaId;
	}
	public void setProvinciaId(String provinciaId) {
		this.provinciaId = provinciaId;
	}
	public String getDepartamentoId() {
		return departamentoId;
	}
	public void setDepartamentoId(String departamentoId) {
		this.departamentoId = departamentoId;
	}
	public String getLocalidadId() {
		return localidadId;
	}
	public void setLocalidadId(String localidadId) {
		this.localidadId = localidadId;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getManzana() {
		return manzana;
	}
	public void setManzana(String manzana) {
		this.manzana = manzana;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getProvinciaNombre() {
		return provinciaNombre;
	}
	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}
	public String getDepartamentoNombre() {
		return departamentoNombre;
	}
	public void setDepartamentoNombre(String departamentoNombre) {
		this.departamentoNombre = departamentoNombre;
	}
	public String getLocalidadNombre() {
		return localidadNombre;
	}
	public void setLocalidadNombre(String localidadNombre) {
		this.localidadNombre = localidadNombre;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getRegionNombre() {
		return regionNombre;
	}
	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}
}
