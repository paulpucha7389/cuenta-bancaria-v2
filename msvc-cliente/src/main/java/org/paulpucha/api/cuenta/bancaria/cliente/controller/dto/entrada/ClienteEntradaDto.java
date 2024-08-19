/**
 * 
 */
package org.paulpucha.api.cuenta.bancaria.cliente.controller.dto.entrada;

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
 * <b> Clase DTO para la entrada de los datos del cliente. </b>
 * 
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 09 jul. 2024 $]
 *          </p>
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntradaDto {

	@NotBlank
	private String identificacion;

	@NotBlank
	private String contrasena;

	@NotBlank
	private String estado;

	@NotBlank
	private String direccion;

	private int edad;

	private String genero;

	@NotBlank
	private String nombre;

	@NotBlank
	private String telefono;

}
