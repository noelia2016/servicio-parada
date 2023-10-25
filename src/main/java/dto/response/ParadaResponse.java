package dto.response;

import java.util.List;

import ps.model.Parada;

public class ParadaResponse {

	private List<Parada> paradas;

	public ParadaResponse(List<Parada> paradas) {
		this.paradas = paradas;
	}

	public List<Parada> getParadas() {
		return paradas;
	}

	public void setParadas(List<Parada> paradas) {
		this.paradas = paradas;
	}
}
