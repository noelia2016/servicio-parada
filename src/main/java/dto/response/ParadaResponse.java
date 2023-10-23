package dto.response;

import java.util.List;

import ps.model.Tarifa;

public class ParadaResponse {

	private List<Parada> Paradas;

	public TarifaResponse(List<Parada> Paradas) {
		this.Paradas = Paradas;
	}

	public List<Tarifa> getTarifas() {
		return Paradas;
	}

	public void setTarifas(List<Parada> Paradas) {
		this.Paradas = Paradas;
	}
}
