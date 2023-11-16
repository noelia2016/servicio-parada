package ps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.requets.ErrorResponse;
import dto.response.ParadaResponse;
import ps.model.Parada;
import ps.repository.ParadaRepository;
import ps.service.TokenServicio;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;

@RestController
@Tag(name = "Servicio Parada", description = "Encargado de todo lo referente a las paradas de los monopatin")
// @RequestMapping("/parada")
public class ParadaController {

	private final ParadaRepository paradaRepository;

	@Autowired
	private TokenServicio token;

	@Value("${variable_env}")
	private String variable_env;

	ParadaController(ParadaRepository paradaRepository) {
		this.paradaRepository = paradaRepository;
	}

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
	public ResponseEntity<Object> obtenerParadas(@RequestHeader("Authorization") String authorization) {

		try {
			/* si el token no es valido no puede mostrar las paradas. */
			if (token.autorizado(authorization) == null)
				return null;
			// si tod esta bien devuelve las paradas
			ParadaResponse p = new ParadaResponse(paradaRepository.findAll());
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
	@Operation(summary = "Agregar una parada", description = "Se incorpora una nueva parada para estacionar Monopatines")
	public Parada crearParada(@Parameter(description = "Una parada para guardar") @RequestBody Parada parada,
			@RequestHeader("Authorization") String authorization) {

		if (token.autorizado(authorization).contains("ADMIN")) {
			return paradaRepository.save(parada);
		} else {
			return null;
		}
	}

	// Actualizar un Parada existente por ID
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar parada", description = "Se actualiza una parada elegida")
	public Parada actualizarParada(
			@Parameter(description = "El Id de la parada a actualizar", example = "123") @PathVariable Long id,
			@RequestBody Parada paradaAct, @RequestHeader("Authorization") String authorization) {

		if (token.autorizado(authorization).contains("ADMIN")) {
			// este toma los datos que estan en el body
			paradaAct.setId(id);
			return paradaRepository.save(paradaAct);
		} else {
			return null;
		}
	}

	// Eliminar un Parada por ID
	@DeleteMapping("/{id}")
	@Operation(summary = "Se elimina una parada", description = "Se da de baja una parada especifica indicando el id")
	public void eliminarParada(@Parameter(description = "El Id de la parada", example = "123") @PathVariable Long id,
			@RequestHeader("Authorization") String authorization) {

			if (token.autorizado(authorization).contains("ADMIN")) {
				Parada paradaAct;
				// este toma los datos que estan en el body
				paradaAct.setId(id);
				paradaRepository.deleteById(id);
			}
	}

	// busca paradas cercanas a un punto
	/**
	 * @param longAct
	 * @param latAct
	 * @param authorization
	 * @return
	 */
	@GetMapping("/parada_cercanas/{longAct}/{latAct}")
	@Operation(summary = "Se obtienen las paradas cercanas", description = "Se retorna las paradas cercanas a una longitud y latitud especifica pasada por parametro")
	public List<Parada> paradasCercanas(@PathVariable Double longAct, @PathVariable Double latAct,
			@RequestHeader("Authorization") String authorization) {

		// si no hay token valido no obtengo paradas cercanas
		if (token.autorizado(authorization) == null)
			return Collections.emptyList();

		/* si esta todo bien retorno las paradas cercanas al punto de partida */ 
		Double radio = 400.0;
		return paradaRepository.paradasCercanasApunto(longAct, latAct, radio);
	}

	// busco por longitud y latitud si un monopatin esta estacionado
	@GetMapping("/estacionado/{lon}/{lat}")
	@Operation(summary = "Indica si un monopatin esta estacionado", description = "Segun longotud y latitud indica devuelve TRUE si el monopatin esta estacionado. FALSE caso contrario")
	public Boolean estaEstacionado(
			@Parameter(description = "Recibe por parametro longitud y latitud donde esta posicionado el usuario", example = "123") @PathVariable Double lon,
			@PathVariable Double lat, @RequestHeader("Authorization") String authorization) {

		// si no hay token valido no obtengo paradas cercanas
		if (token.autorizado(authorization) == null)
			return false;

		// busca si la posicion que mandan esta en una parada valida
		List<Parada> estacionado = paradaRepository.monopatinEstacionado(lon, lat);

		// verifica si no hay datos en la lista que devuelve
		if (estacionado.isEmpty()) {
			return false;
		}
		// devuelve true si esta estacionado
		return true;
	}

}
