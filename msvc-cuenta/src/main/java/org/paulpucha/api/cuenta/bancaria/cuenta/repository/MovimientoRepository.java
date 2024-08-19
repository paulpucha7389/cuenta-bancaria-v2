
package org.paulpucha.api.cuenta.bancaria.cuenta.repository;

import java.util.Date;
import java.util.List;

import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.ReporteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.paulpucha.api.cuenta.bancaria.cuenta.model.entity.Movimiento;
import org.springframework.data.repository.query.Param;

/**
 * 
 * <b> Interfaz del repositorio del movimiento. </b>
 * 
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *          </p>
 */
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	List<Movimiento> findByIdCuenta(Long idCuenta);

	@Modifying
	@Query(value = "DELETE FROM Movimiento m WHERE m.id_movimiento = :idMovimiento", nativeQuery = true)
	void deleteByIdMovimiento(@Param("idMovimiento") Long idMovimiento);

	@Query(value = "SELECT CAST(m.fecha AS DATE) as fecha, cl.nombre as cliente, "
			+ " cu.numero_cuenta as numeroCuenta, cu.tipo_cuenta as tipo, "
			+ " m.saldo_anterior as saldoInicial, cu.estado, m.valor as movimiento, "
			+ " m.saldo as saldoDisponible, m.tipo_movimiento as tipoMovimiento "
			+ "FROM movimiento m, cuenta cu, cliente cl "
			+ "where cu.id_cliente = cl.cliente_Id and m.id_cuenta = cu.id_cuenta "
			+ "and CAST(m.fecha AS DATE) between CAST(:fechaInicial AS DATE) AND CAST(:fechaFinal AS DATE) "
			+ "and cl.identificacion = :identificacion order by m.fecha desc", nativeQuery = true)
	List<ReporteDto> obtenerMovimientosPorIdentificacionPorFechas(String identificacion, Date fechaInicial, Date fechaFinal);

}
