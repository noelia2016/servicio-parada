import ar.edu.cresta.dto.ParadaDTO;
import ar.edu.cresta.model.Parada;
import ar.edu.cresta.repository.ParadaRepository;
import ar.edu.cresta.service.ParadaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/* 
 	Prueba Unitaria:
	Una prueba unitaria verifica el comportamiento de una unidad aislada de código, 
	como un método o una función, sin depender de otras partes del sistema.
	Se utiliza Mockito para simular el comportamiento de las dependencias y 
	se enfoca en probar la lógica interna de la unidad bajo prueba.

	Prueba de Integración:
	Una prueba de integración evalúa la interacción entre múltiples componentes del sistema.
	Se utiliza un entorno más realista, como una base de datos en memoria, 
	para verificar que los componentes interactúen correctamente. 
	En este contexto, Spring Boot y la anotación @DataJpaTest se utilizan para 
	configurar un entorno de prueba de integración con una base de datos real. 
	La prueba garantiza que el sistema funcione de manera cohesiva en conjunto. 
*/

public class ParadaPruebaUnidadTest {

	/* NO SE GRABA EN BD */

	@Mock // Clase simulada
	private ParadaRepository ParadaRepository;

	@InjectMocks // Clase donde se inyectara el mock
	private ParadaService ParadaService;

	// Inicializa todo
	public ParadaServiceUnidadTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGuardarParada() {
		/*
		 * Configurar el comportamiento simulado del repositorio:
		 * 
		 * Primero se crear una instancia de ParadaDTO, luego está configurando el
		 * comportamiento simulado del método save del repositorio
		 * (ParadaRepository).
		 * 
		 * La parte Mockito.any(Parada.class, porq save espera Parada) indica
		 * que la simulación se activará cuando se llame al método save con cualquier
		 * instancia de la clase Parada.
		 * 
		 * La parte .thenReturn(...)) indica que, cuando se llame al método save con cualquier
		 * instancia de Parada, Mockito devolverá una instancia específica de
		 * Parada creada con los valores proporcionados .
		 */
		ParadaDTO parDTO = new ParadaDTO("Parada1", 23456, 2345);
		when(ParadaRepository.save(Mockito.any(Parada.class)))
				.thenReturn(new Parada("Parada1", 23456, 2345));

		// Llamar al método del servicio y verificar el resultado
		Parada parEnBase = ParadaService.guardarParada(parDTO);

		// Verificar que el método del repositorio se llamó una vez y con los parámetros
		// correctos
		// Mockito.verify(ParadaRepository,
		// Mockito.times(1)).save(Mockito.any(Parada.class));

		// Verificar que los datos del club guardado coinciden con los datos
		// proporcionados en el DTO
		assertEquals("Parada1", parEnBase.getNombre());
		assertEquals(23456, parEnBase.getLongitud());
		assertEquals(2345, parEnBase.getLatitud());
	}
}