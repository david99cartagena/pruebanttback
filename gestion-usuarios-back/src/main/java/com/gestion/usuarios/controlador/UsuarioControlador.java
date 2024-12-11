package com.gestion.usuarios.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.usuarios.excepciones.ResourceNotFoundException;
import com.gestion.usuarios.modelo.Usuario;
import com.gestion.usuarios.repositorio.UsuarioRepositorio;
import com.gestion.usuarios.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioControlador {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	// Listar todos los usuarios
	@GetMapping("/usuarios")
	public ResponseEntity<ApiResponse<List<Usuario>>> listarTodosLosUsuarios() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		return ResponseEntity.ok(new ApiResponse<>(200, usuarios, null));
	}

	// Guardar usuarios
	@PostMapping("/usuarios")
	public ResponseEntity<ApiResponse<Usuario>> guardarUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario nuevoUsuario = usuarioRepositorio.save(usuario);
			return ResponseEntity.status(201).body(new ApiResponse<>(201, nuevoUsuario, null));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse<>(400, null, "Error al guardar el usuario: " + e.getMessage()));
		}
	}

	// Obtener usuario por Id
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<ApiResponse<Usuario>> obtenerUsuarioporId(@PathVariable Long id) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
		return ResponseEntity.ok(new ApiResponse<>(200, usuario, null));
	}

//	 localhost:8090/api/v1/usuarios/consultar-por-documento?tipoDocumento=C&identificacion=23445322
	@PostMapping("/usuarios/consultar-por-documento")
	public ResponseEntity<ApiResponse<Map<String, String>>> consultarPorDocumento(
			@RequestParam("tipoDocumento") String tipoDocumento, @RequestParam("identificacion") int identificacion) {

		if (!tipoDocumento.equals("C") && !tipoDocumento.equals("P")) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(400, null,
					"El tipo de documento debe ser 'C' (Cédula de Ciudadanía) o 'P' (Pasaporte)"));
		}

		if (identificacion == 0) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse<>(400, null, "La identificación es obligatoria y debe ser un número válido"));
		}

		Map<String, String> datosQuemados = new HashMap<>();
		datosQuemados.put("primer_nombre", "Juan");
		datosQuemados.put("segundo_nombre", "Carlos");
		datosQuemados.put("primer_apellido", "Pérez");
		datosQuemados.put("segundo_apellido", "García");
		datosQuemados.put("telefono", "3001234567");
		datosQuemados.put("direccion", "Calle 123 #45-67");
		datosQuemados.put("ciudad_residencia", "Bogotá");

		return ResponseEntity.ok(new ApiResponse<>(200, datosQuemados, null));
	}

	/*
	 * @PutMapping("/usuarios/{id}") public ResponseEntity<Usuario>
	 * actualizarUsuario(@PathVariable Long id, @RequestBody Usuario
	 * detallesUsuario) {
	 * 
	 * Usuario usuario = usuarioRepositorio.findById(id) .orElseThrow(() -> new
	 * ResourceNotFoundException("No existe el usuario con el ID : " + id));
	 * 
	 * usuario.setIdentificacion(detallesUsuario.getIdentificacion());
	 * usuario.setPrimer_nombre(detallesUsuario.getPrimer_nombre());
	 * usuario.setSegundo_nombre(detallesUsuario.getSegundo_nombre());
	 * usuario.setPrimer_apellido(detallesUsuario.getPrimer_apellido());
	 * usuario.setSegundo_apellido(detallesUsuario.getSegundo_apellido());
	 * 
	 * usuario.setTelefono(detallesUsuario.getTelefono());
	 * usuario.setDireccion(detallesUsuario.getDireccion());
	 * usuario.setCiudad_residencia(detallesUsuario.getCiudad_residencia());
	 * 
	 * Usuario usuarioActualizado = usuarioRepositorio.save(usuario);
	 * 
	 * 
	 * return ResponseEntity.ok(usuarioActualizado); }
	 */

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<ApiResponse<Usuario>> actualizarUsuario(@PathVariable Long id,
			@RequestBody Usuario detallesUsuario) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));

		// Actualizar los campos del usuario
		usuario.setIdentificacion(detallesUsuario.getIdentificacion());
		usuario.setPrimer_nombre(detallesUsuario.getPrimer_nombre());
		usuario.setSegundo_nombre(detallesUsuario.getSegundo_nombre());
		usuario.setPrimer_apellido(detallesUsuario.getPrimer_apellido());
		usuario.setSegundo_apellido(detallesUsuario.getSegundo_apellido());
		usuario.setTelefono(detallesUsuario.getTelefono());
		usuario.setDireccion(detallesUsuario.getDireccion());
		usuario.setCiudad_residencia(detallesUsuario.getCiudad_residencia());

		Usuario usuarioActualizado = usuarioRepositorio.save(usuario);
		return ResponseEntity.ok(new ApiResponse<>(200, usuarioActualizado, null));
	}

	/*
	 * @DeleteMapping("/usuarios/{id}") public ResponseEntity<Map<String, Boolean>>
	 * eliminarUsuario(@PathVariable Long id) { Usuario usuario =
	 * usuarioRepositorio.findById(id) .orElseThrow(() -> new
	 * ResourceNotFoundException("No existe el usuario con el ID : " + id));
	 * 
	 * usuarioRepositorio.delete(usuario); Map<String, Boolean> respuesta = new
	 * HashMap<>(); respuesta.put("eliminar", Boolean.TRUE); return
	 * ResponseEntity.ok(respuesta); }
	 */

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<ApiResponse<Map<String, Boolean>>> eliminarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));

		usuarioRepositorio.delete(usuario);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);

		return ResponseEntity.ok(new ApiResponse<>(200, respuesta, null));
	}

}
