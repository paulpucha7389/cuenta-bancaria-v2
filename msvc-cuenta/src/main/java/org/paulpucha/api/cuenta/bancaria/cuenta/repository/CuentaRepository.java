/**
 * 
 */
package org.paulpucha.api.cuenta.bancaria.cuenta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.paulpucha.api.cuenta.bancaria.cuenta.model.entity.Cuenta;

/**
 * 
 * <b> Interfaz del repositorio del Cuenta. </b>
 * 
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *          </p>
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	List<Cuenta> findByIdCliente(Long id);

	Optional<Cuenta> findByNumeroCuenta(int numeroCuenta);
}
