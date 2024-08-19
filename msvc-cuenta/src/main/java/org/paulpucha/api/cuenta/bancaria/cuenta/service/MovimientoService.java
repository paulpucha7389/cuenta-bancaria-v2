/**
 *
 */
package org.paulpucha.api.cuenta.bancaria.cuenta.service;

import java.util.List;

import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.ReporteDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.entrada.MovimientoEntradaDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.paulpucha.api.cuenta.bancaria.cuenta.model.entity.Movimiento;

/**
 * <b> Interfaz del servicio para el Movimiento. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *     </p>
 */
public interface MovimientoService {

    /**
     * Inserta un movimiento.
     *
     * @param movimientoEntradaDto
     * @return un objeto movimiento.
     * @throws CuentaException indica que ha ocurrido una excepcion.
     */
    Movimiento create(MovimientoEntradaDto movimientoEntradaDto) throws CuentaException;

    /**
     * Obtiene una lista de movimientos dado un numero de cuenta.
     *
     * @param numeroCuenta
     * @return lista de movimientos.
     * @throws CuentaException indica que ha ocurrido una excepcion.
     */
    List<Movimiento> obtenerPorNumeroCuenta(int numeroCuenta)
        throws CuentaException;

    /**
     * Actualiza en movimiento.
     *
     * @param movimientoEntradaDto
     * @return un objeto movimiento.
     * @throws CuentaException indica que ha ocurrido una excepcion.
     */
    Movimiento update(MovimientoEntradaDto movimientoEntradaDto) throws CuentaException;

    /**
     * Elimina un movimiento dado su id.
     *
     * @param id
     * @throws CuentaException indica que ha ocurrido una excepcion.
     */
    void deleteByIdMovimiento(Long id) throws CuentaException;

    /**
     * <b> Metodo que genera el reporte por fechas, las mismas estan separadas por
     * coma(,) y debe venir en el siguente formato yyyy-MM-dd </b>
     * <p>
     * [Author: Paul Pucha, Date: 21 abr. 2024]
     * </p>
     *
     * @param fecha fechas de entrada lista de ReporteDto.
     * @throws CuentaException,RuntimeException indica que ha ocurrido una excepcion.
     */
    List<ReporteDto> generarReporte(String identififcacion, String fecha)
            throws CuentaException, RuntimeException;


}
