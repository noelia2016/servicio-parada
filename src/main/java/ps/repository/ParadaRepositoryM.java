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

	/* para acceder al nombre de parada */
	@Query("{name:'?0'}")
	ParadaM findItemByName(String name);

	public long count();

}
