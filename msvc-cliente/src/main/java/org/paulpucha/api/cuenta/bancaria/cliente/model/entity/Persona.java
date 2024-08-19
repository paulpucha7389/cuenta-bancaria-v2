package org.paulpucha.api.cuenta.bancaria.cliente.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * <b> Clase persona que sera heradad por cliente. </b>
 * 
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 09 jul. 2024 $]
 *          </p>
 */
@MappedSuperclass
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String direccion;

	private int edad;

	private String genero;

	@Column(unique = true)
	@NotBlank
	private String identificacion;

	@NotBlank
	private String nombre;

	@NotBlank
	private String telefono;

}