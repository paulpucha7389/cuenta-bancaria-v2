package org.paulpucha.api.cuenta.bancaria.cuenta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * <b> Clase ClienteModel modelo que nos permitira la cominicacion
 * con el msvc-cliente </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *          </p>
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel {

    private Long clienteId;

    private String contrasena;

    private String estado;

    private String direccion;

    private int edad;

    private String genero;

    private String identificacion;

    private String nombre;

    private String telefono;


}
