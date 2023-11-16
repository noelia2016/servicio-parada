import java.util.Optional;

import ps.controller.ParadaController;
import ps.model.Parada;
import ps.service.ParadaServicio;

@WebMvcTest(ParadaController.class)
public class ParadaControlerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ParadaServicio service;

  @Autowired
  private ObjectMapper objectMapper;

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

    Optional<Parada> optStudent = Optional.of(createBook());
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
}