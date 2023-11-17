package dto;

public class ParadaDTO {

	private String nombre;
	private int longitud;
	private int latitud;

	public ParadaDTO() {
	}

	public ParadaDTO(String nombre, int lon, int lat) {
		this.nombre = nombre;
		this.longitud = lon;
		this.latitud = lat;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public int getLatitud() {
		return latitud;
	}

	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.latitud = longitud;
	}

	@Override
	public String toString() {
		return "ParadaDTO [nombre=" + nombre + ", longitud=" + longitud + ", latitud=" + latitud + "]";
	}

}
