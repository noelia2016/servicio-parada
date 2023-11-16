import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.net.http.HttpHeaders;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ps.model.Parada;
import ps.repository.ParadaRepository;
import ps.service.ParadaServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParadaServiceTest {

  @Autowired
  private ParadaServicio paradaService;

  @MockBean
  private ParadaRepository paradaRepository;

  @Test
  public void testRetrieveParadaWithMockRepository() throws Exception {

    Optional<Parada> optStudent = Optional.of(createParada());

    when(paradaRepository.findById(1L)).thenReturn(optStudent);

    assert paradaService.findById(1L).getNombre().contains("Numancia");

  }

  /* crea la parada para probar el test */
  private Parada createParada() {

    Parada parada = new Parada();
    parada.setId(12);
    parada.seNombre("Cerrito chico");
    parada.setLongitud(34);
    parada.setLatitud(64);
    return parada;

  }

  /*
   * @Test
   * void testParada() throws Exception {
   * 
   * HttpEntity<String> entity = new HttpEntity<String>(null, headers);
   * ResponseEntity<Parada> response = restTemplate.exchange(
   * createURLWithPort("/paradas"), HttpMethod.POST, entity, String.class);
   * String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
   * assertTrue(actual.contains("/paradas"));
   * }
   */
}