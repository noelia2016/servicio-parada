package ps.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Parada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double longitud;
	private double latitud;

	// Constructor vacío
	public Parada() {
	}

	// Constructor con todos los campos
	public Parada(double lon, double lat) {
		
		this.longitud = lon;
		this.latitud = lat;

	}

	// Getters y setters
	public double getLongitud() {
		return longitud;
	}

	public void setTLongitud(Long valor) {
		this.longitud = valor;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setTLatitud(Long valor) {
		this.latitud = valor;
	}

}
