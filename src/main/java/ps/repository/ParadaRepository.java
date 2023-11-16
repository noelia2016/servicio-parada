package ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Parada;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

	// devuelve todas las paradas cercanas que estan en una longitud
	@Query("SELECT p FROM Parada p WHERE (p.longitud BETWEEN :longAct - :radio AND :longAct + :radio) AND (p.latitud BETWEEN :latAct - :radio AND :latAct + :radio)")
	public List<Parada> paradasCercanasApunto(Double longAct, Double latAct, Double radio);

	// devuelve las paradas segun latidud y longitud pasada
	@Query("SELECT p FROM Parada p where  p.longitud = :lon and p.latitud =:lat")
	public List<Parada> monopatinEstacionado(Double lon, Double lat);

}
