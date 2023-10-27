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

	public List<Parada> getAll();
    public List<Parada> getByLastName(String lname);
    public Employee save(Parada employee); // Add new parada
    public Employee update(Parada updatedParada, String id);
    public void deleteById(String id);
    public Parada getById(String id);
    public boolean isIdFound(String id);
	
	// muestra una parada por nombre
	@Query("SELECT p FROM Parada p WHERE p.nombre LIKE %:nombre%")
	public Parada xNombre(String nombre);

	// devuelve todas las paradas
	@Query("SELECT p FROM Parada p ")
	public List<ParadaResponse> getParadas();
}
