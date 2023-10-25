package ps.repository;

import dto.response.ParadaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Parada;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

	List<Parada> findByValorGreaterThan(double valor);

	// muestra una parada por nombre
	@Query("SELECT p FROM Parada p WHERE p.nombre LIKE %:nombre%")
	public Parada xNombre(String nombre);

	@Query("SELECT NEW com.example.demo.dtos.EstudianteCarreraDTO(c , e, i, (i.fecha + i. antiguedad) as fech) FROM Inscripto i JOIN i.estudiante e JOIN i.carrera c WHERE i.esEgresado = 1")
	public List<ParadaResponse> getParadas();
}
