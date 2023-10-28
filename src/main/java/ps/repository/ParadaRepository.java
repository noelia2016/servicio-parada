package ps.repository;

import dto.response.ParadaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Parada;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

	//List<Parada> findByValorGreaterThan(double valor);

	// muestra una parada por nombre
	@Query("SELECT p FROM Parada p WHERE p.nombre LIKE %:nombre%")
	public Parada xNombre(String nombre);

	// devuelve todas las paradas
	@Query("SELECT p FROM Parada p ")
	public List<ParadaResponse> getParadas();

	// devuelve todas las paradas cercanas que estan en una longitud
	@Query("SELECT p FROM Parada p where p.longitud BETWEEN :lonAct :latAct or p.latitud BETWEEN :lonAct :latAct")
	public List<ParadaResponse> paradasCercanasApunto(int longAct, int latAct);
}
