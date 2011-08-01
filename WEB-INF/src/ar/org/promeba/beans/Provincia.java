package ar.org.promeba.beans;

public class Provincia {
	
	private String id;
	private String nombre;
	private String regionId;
	private String color;



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

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String areaGeograficaId) {
		this.regionId = areaGeograficaId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
