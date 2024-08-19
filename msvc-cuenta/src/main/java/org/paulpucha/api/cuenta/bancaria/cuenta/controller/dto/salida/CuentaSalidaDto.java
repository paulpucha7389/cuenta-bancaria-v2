package org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.salida;

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
 * <b> Clase Dto para la entrada de la cuenta. </b>
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
public class CuentaSalidaDto {

    private Long idCuenta;
    private String identificacion;
    private String tipoCuenta;
    private double saldoInicial;
    private String estado;
    private Long idCliente;
    private int numeroCuenta;

}
