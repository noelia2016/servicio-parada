package ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ps.model.Parada;

import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

	List<Parada> findByValorGreaterThan(double valor);

	public List<Parada> getAll();
    public List<Parada> getByLastName(String lname);
    public Employee save(Parada employee); // Add new parada
    public Employee update(Employee updatedParada, String id);
    public void deleteById(String id);
    public Parada getById(String id);
    public boolean isIdFound(String id);

}
