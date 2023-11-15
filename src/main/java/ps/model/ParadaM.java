package ps.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ParadaM")
public class ParadaM {

	@Id
	private String id;

	private String nombre;
	private int longitud;
	private int latitud;

	/**
	 * Modelo de datos para MongoDB - no sql
	 */
	public ParadaM(String id, String name, int lon, int lat) {

		super();
		this.id = id;
		this.nombre = name;
		this.logitud = lon;
		this.latitud = lat;
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

	public void setNombre(String name) {
		this.nombre = name;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int lon) {
		this.longitud = lon;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(int lat) {
		this.latitud = lat;
	}

}
