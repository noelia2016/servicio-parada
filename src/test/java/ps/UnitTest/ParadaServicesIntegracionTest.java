import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import dto.ParadaDTO;
import ps.model.Parada;
import ps.repository.ParadaRepository;
import ps.service.ParadaService;

@DataJpaTest // configura la prueba para utilizar una configuración específica de JPA y
             // proporciona una base de datos en memoria para las pruebas
@AutoConfigureMockMvc // se usa para configurar automáticamente el entorno de la aplicación
public class ParadaServicesIntegracionTest {

    @Autowired
    private ParadaRepository repository;

    @Test
    public void testGuardarIntegration() {

        // Configuración manual sin usar @Autowired
        ParadaService service = new ParadaService(repository);

        // Crear un objeto DTO con datos de prueba
        ParadaDTO paradaDTO = new ParadaDTO("Nordico 1", 2345, 3456);

        // Llamar al método del servicio que interactúa con la base de datos
        Parada parGuardado = service.guardarParada(paradaDTO);

        // Verificar que la parada se ha guardado correctamente en la base de datos
        assertNotNull(parGuardado.getId());

        // verifico que sea igual a lo que creo
        assertEquals("Nordico 1", parGuardado.getNombre());
        assertEquals(2345, parGuardado.getLongitud());
        assertEquals(3456, parGuardado.getLatitud());

        // Verificar que la parada se puede recuperar correctamente de la base de datos
        Parada parRecuperado = repository.findById(parGuardado.getId()).orElse(null);
        assertNotNull(parRecuperado);
        assertEquals("Nordico 1", parRecuperado.getNombre());
        assertEquals(2345, parRecuperado.getLongitud());
        assertEquals(3456, parRecuperado.getLatitud());

    }
}
