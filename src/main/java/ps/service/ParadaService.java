package ps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dto.ParadaDTO;
import ps.model.Parada;
import ps.repository.ParadaRepository;

@Service
public class ParadaService {

    // private final String direccion = "http://localhost:8093/parada";
//    @Value("${paradaURL}")
//    public String paradaURL;

    private ParadaRepository paradaRepository;

    @Autowired
    public ParadaService(ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }

    public List<ParadaDTO> dameParadas() {
        return paradaRepository.obtenerParadasDTO();
    }

    public Parada guardarParada(ParadaDTO par_dto) {
        Parada par_new = new Parada(
                par_dto.getNombre(),
                par_dto.getLongitud(),
                par_dto.getLatitud());

        return paradaRepository.save(par_new);
    }

}
