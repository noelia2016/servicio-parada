package ps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import dto.requets.ErrorResponse;
import dto.response.ParadaResponse;
import ps.model.Parada;
import ps.repository.ParadaRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/parada")
public class ParadaController {
	
	@Autowired
	private ParadaRepository paradaRepository;

	@Value("${variable_env}")
	private String variable_env;

	@GetMapping("/variable_env")
	public String obtener_variable_env() {
		return variable_env;
	}

	@GetMapping("/string")
	public String obtener_string_hardcodeado() {
		return "Un mensaje de texto.";
	}

	// Obtener todos los Paradas
	@GetMapping("/paradas")
	public ResponseEntity<Object> obtenerParadas() {
		try {
			// TODO: Pasar al service.
			ParadaResponse p = new ParadaResponse(paradaRepository.findAll());
			// Algun llamado al service.
			//throw new Exception("Este es un mensaje opcional");
			return ResponseEntity.ok(p);
			
		} catch (Exception e) {
			// Ojo con esto por que puede enviar un error de BD al front,
			// se deberia controlar con e custom o error generico.
			ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
		}
	}

	// Crear un nuevo Parada
	@PostMapping
	public Parada crearParada(@RequestBody Parada parada) {
		return paradaRepository.save(parada);
	}

	// Actualizar un Parada existente por ID
	/*@PutMapping("/{id}")
	public Parada actualizarParada(@PathVariable Long id, @RequestBody Parada paradaAct) {
		// este toma los datos que estan en el body
		paradaAct.setId(id);
		return paradaRepository.save(paradaAct);
	}

	// Eliminar un Parada por ID
	@DeleteMapping("/{id}")
	public void eliminarParada(@PathVariable Long id) {
		paradaRepository.deleteById(id);
	}*/

	// busca paradas cercanas a un punto
	/*@GetMapping("/parada_cercanas/{long}/{lat}")
    public List<Parada> paradasCercanas(@PathVariable int longAct, @PathVariable int latAct){
        
		return paradaRepository.paradasCercanasApunto(longAct, latAct);
        
    } */

	// busco por longitud y latitud si un monopatin esta estacionado
	@GetMapping("/estacionado/{lon}/{lat}")
	public Boolean estaEstacionado(@PathVariable Double lon, @PathVariable Double lat) {

		// busca si la posicion que mandan esta en una parada valida
		List<Parada>  estacionado = paradaRepository.monopatinEstacionado(lon, lat);
		System.out.println(estacionado);
		// verifica si no hay datos
        if (estacionado.size() == 0) {
            return false;
        }else{
		// devuelve true si esta estacionado
        	return true;
		}
	}

}
