package ps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Parada;
import dto.ParadaDTO;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

	// devuelve todas las paradas cercanas que estan en una longitud
	@Query("SELECT p FROM Parada p WHERE (p.longitud BETWEEN :longAct - :radio AND :longAct + :radio) AND (p.latitud BETWEEN :latAct - :radio AND :latAct + :radio)")
	public List<Parada> paradasCercanasApunto(Double longAct, Double latAct, Double radio);

	// devuelve las paradas segun latidud y longitud pasada
	@Query("SELECT p FROM Parada p where  p.longitud = :lon and p.latitud =:lat")
	public List<Parada> monopatinEstacionado(Double lon, Double lat);

	@Query("SELECT new dto.ParadaDTO (p.nombre, p.longitud, p.latitud) FROM Parada p")
	public List<ParadaDTO> obtenerParadasDTO();

	Optional<Parada> findById(Long id);

}
