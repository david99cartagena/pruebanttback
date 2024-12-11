package com.gestion.usuarios.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.usuarios.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.tipoDocumento = :tipoDocumento AND u.identificacion = :identificacion")
	Optional<Usuario> findByTipoYIdentificacion(@Param("tipoDocumento") String tipoDocumento,
			@Param("identificacion") int identificacion);

}
