/**
 *
 */
package org.paulpucha.api.cuenta.bancaria.cuenta.service;

import java.util.List;
import java.util.Optional;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.entrada.CuentaEntradaDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.paulpucha.api.cuenta.bancaria.cuenta.model.entity.Cuenta;

/**
 *
 * <b> Interfaz del servicio para el Cuenta. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *          </p>
 */
public interface CuentaService {

    /**
     * <b> Metodo que crea cuentas. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param cuentaEntradaDto parametro de entrada
     * @return objeto Cuenta
     * @throws CuentaException indica que ha ocurrido una excepcion.
     */
    Cuenta create(CuentaEntradaDto cuentaEntradaDto) throws CuentaException;

    /**
     * <b> Metodo que actualiza la cuenta. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param cuentaEntradaDto parametro de entrada
     * @return objeto cuenta.
     * @throws CuentaException indica que ha ocurrido una excepcion.
     */
    Cuenta update(CuentaEntradaDto cuentaEntradaDto) throws CuentaException;

    /**
     * <b> Metodo que elimina un registro por su id. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param id parametro de entrada.
     * @throws CuentaException indica que ha ocurrido una excepcion.
     */
    void delete(Long id) throws CuentaException;

    /**
     * <b> Metodo para obtener las cuentas de un cliente por su identificacion. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @return Lista de cuentas.
     * @throws CuentaException indica que ha ocurrido una excepcion.
     **/
    List<Cuenta> obtenerCuentasPorCliente(String identificacion) throws CuentaException;

    /**
     * <b> Metodo para obtener la cuenta dado el numero de cuenta. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param numeroCuenta parametro de entrada
     * @return la cuenta.
     **/
    Optional<Cuenta> obtenerPorNumeroCuenta(int numeroCuenta);

}
