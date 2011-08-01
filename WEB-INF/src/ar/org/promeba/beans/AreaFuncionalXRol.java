package ar.org.promeba.beans;

public class AreaFuncionalXRol {
	
	String id;
	String areaId;
	String areaNombre;
	String rolId;
	String rolNombre;
	boolean vision;
	boolean escritura;
	boolean lectura;
	boolean ejecucion;
	boolean impresion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaNombre() {
		return areaNombre;
	}
	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}
	public String getRolId() {
		return rolId;
	}
	public void setRolId(String rolId) {
		this.rolId = rolId;
	}
	public String getRolNombre() {
		return rolNombre;
	}
	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}
	public boolean isVision() {
		return vision;
	}
	public void setVision(boolean vision) {
		this.vision = vision;
	}
	public boolean isEscritura() {
		return escritura;
	}
	public void setEscritura(boolean escritura) {
		this.escritura = escritura;
	}
	public boolean isLectura() {
		return lectura;
	}
	public void setLectura(boolean lectura) {
		this.lectura = lectura;
	}
	public boolean isEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(boolean ejecucion) {
		this.ejecucion = ejecucion;
	}
	public boolean isImpresion() {
		return impresion;
	}
	public void setImpresion(boolean impresion) {
		this.impresion = impresion;
	}


}
