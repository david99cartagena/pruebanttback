package com.gestion.usuarios.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "identificacion", length = 10, nullable = false)
	private int identificacion;

	@Column(name = "primer_nombre", length = 60, nullable = false)
	private String primer_nombre;

	@Column(name = "segundo_nombre", length = 60, nullable = false)
	private String segundo_nombre;

	@Column(name = "primer_apellido", length = 60, nullable = false)
	private String primer_apellido;

	@Column(name = "segundo_apellido", length = 60, nullable = false)
	private String segundo_apellido;

	@Column(name = "telefono", length = 60, nullable = false)
	private String telefono;

	@Column(name = "direccion", length = 60, nullable = false)
	private String direccion;

	@Column(name = "ciudad_residencia", length = 60, nullable = false)
	private String ciudad_residencia;

	@Column(name = "tipo_documento", length = 1, nullable = false)
	private String tipoDocumento;

	public Usuario() {
	}

	public Usuario(Long id, int identificacion, String primer_nombre, String segundo_nombre, String primer_apellido,
			String segundo_apellido, String telefono, String direccion, String ciudad_residencia,
			String tipoDocumento) {
		super();
		this.id = id;
		this.identificacion = identificacion;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.ciudad_residencia = ciudad_residencia;
		this.tipoDocumento = tipoDocumento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}

	public String getSegundo_nombre() {
		return segundo_nombre;
	}

	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad_residencia() {
		return ciudad_residencia;
	}

	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudad_residencia = ciudad_residencia;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
