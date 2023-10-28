package ps.repository;

import dto.response.ParadaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Parada;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

	// devuelve todas las paradas cercanas que estan en una longitud
	/*@Query("SELECT p FROM Parada p where p.longitud BETWEEN :lonAct :latAct or p.latitud BETWEEN :lonAct :latAct")
	public List<ParadaResponse> paradasCercanasApunto(int longAct, int latAct);*/

	@Query("SELECT p FROM Parada p where  p.longitud = :lon and p.latitud =:lat")
	public List<Parada> monopatinEstacionado(Double lon, Double lat);

}
