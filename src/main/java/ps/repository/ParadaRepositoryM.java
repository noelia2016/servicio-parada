package ps.repository;

import dto.response.ParadaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Parada;
import ps.model.ParadaM;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ParadaRepositoryM extends MongoRepository<ParadaM, Long> {

	@Autowired
	MongoTemplate mongoTemplate;

	/* para acceder al nombre de parada */
	@Query("{name:'?0'}")
	ParadaM findItemByName(String name);

	public long count();

	// devuelve todas las paradas cercanas que estan en una longitud
	/*
	 * @Query("SELECT p FROM Parada p WHERE (p.longitud BETWEEN :longAct - :radio AND :longAct + :radio) AND (p.latitud BETWEEN :latAct - :radio AND :latAct + :radio)"
	 * )
	 * public List<Parada> paradasCercanasApunto(Double longAct, Double latAct,
	 * Double radio);
	 */

	// devuelve las paradas segun latidud y longitud pasada
	// @Query("SELECT p FROM Parada p where p.longitud = :lon and p.latitud =:lat");
	public List<Parada> monopatinEstacionado(Double lon, Double lat){
		@Query("value=longitud:'?lon'","value=latitud:lat")
		List<Parada> findAll(Double lon , Double lat);
	}
}
