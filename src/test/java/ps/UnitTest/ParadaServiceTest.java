import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.net.http.HttpHeaders;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import dto.ParadaDTO;
import ps.model.Parada;
import ps.repository.ParadaRepository;
import ps.service.ParadaService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParadaServiceTest {

  @Autowired
  private ParadaService paradaService;

  @MockBean
  private ParadaRepository paradaRepository;

  @Test
  public void testRetrieveParadaWithMockRepository() throws Exception {

    Optional<ParadaDTO> optParada = Optional.of(crearParada("Numancia", 23, 45));

    // when(paradaRepository.findById(1L)).thenReturn(optParada);

    // assert ((ParadaRepository)
    // paradaService).findById(1L).getNombre().contains("Numancia");

  }

  private ParadaDTO crearParada(String string, int i, int j) {
    ParadaDTO paradaDTO = new ParadaDTO("Nordico 1", 2345, 3456);
    return paradaDTO;
  }

}