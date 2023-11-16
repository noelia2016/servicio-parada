import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

import ps.controller.ParadaController;
import ps.model.Parada;
import ps.repository.ParadaRepository;
import ps.service.ParadaServicio;

@WebMvcTest(ParadaController.class)
public class ParadaControlerTest {

  @Autowired
  private MockBean mockMvc;

  @MockBean
  private ParadaServicio service;

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${nombre}")
  String nombre;

  /**
   * @throws Exception
   */
  @Test
  public void givenParada_whenGetParadaById_thenReturnParada()
      throws Exception {

    Parada book = createParada();
    given(service.findById(1L)).willReturn(book);
    var findById = mockMvc.perform(
        get(service.paradaURL + "/12")
            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();
    var b = objectMapper.readValue(findById.getResponse().getContentAsString(), Parada.class);
    assert b.getId().equalsIgnoreCase(12);

  }

  /** test para saber si contiene la parada */
  @Test
  public void testRetrieveParadaWithMockRepository() throws Exception {

    Optional<Parada> optStudent = Optional.of(createParada());
    when(ParadaRepository.findById(1L)).thenReturn(optStudent);
    assert service.findById(1L).getNombre().contains("Numancia");

  }

  /* crea la parada para probar el test */
  private Parada createParada() {

    Parada parada = new Parada();
    parada.setId(12);
    parada.setNombre(nombre);
    parada.setLongitud(34);
    parada.setLatitud(64);
    return parada;

  }
}