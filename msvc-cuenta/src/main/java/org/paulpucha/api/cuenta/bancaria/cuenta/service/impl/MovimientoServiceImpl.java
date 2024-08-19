/**
 *
 */
package org.paulpucha.api.cuenta.bancaria.cuenta.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.ReporteDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.entrada.MovimientoEntradaDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.enumeration.TipoMovimientoEnum;
import org.paulpucha.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.paulpucha.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.paulpucha.api.cuenta.bancaria.cuenta.repository.CuentaRepository;
import org.paulpucha.api.cuenta.bancaria.cuenta.repository.MovimientoRepository;
import org.paulpucha.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.paulpucha.api.cuenta.bancaria.cuenta.service.MovimientoService;
import org.paulpucha.api.cuenta.bancaria.cuenta.model.entity.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <b> Servicio para el Movimiento. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *     </p>
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    private static final int limiteDiario = 1000;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaService cuentaService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Movimiento create(MovimientoEntradaDto movimientoEntradaDto) throws CuentaException {
        try {
            if (movimientoEntradaDto.getValor() <= 0) {
                throw new CuentaException("El valor debe ser positivo.");
            }

            String tipoMovimiento = movimientoEntradaDto.getTipoMovimiento();
            if (!TipoMovimientoEnum.CREDITO.getDescripcion().equalsIgnoreCase(tipoMovimiento) &&
                    !TipoMovimientoEnum.DEBITO.getDescripcion().equalsIgnoreCase(tipoMovimiento)
            ) {
                throw new CuentaException("Tipo de movimiento inválido. Debe ser 'CREDITO' o 'DEBITO'.");
            }
            Optional<Cuenta> cuenta = cuentaService.obtenerPorNumeroCuenta(
                movimientoEntradaDto.getNumeroCuenta());
            if (!cuenta.isPresent()) {
                throw new CuentaException("La cuenta no existe con el número de cuenta ingresado.");
            }

            BigDecimal saldo = new BigDecimal(0);
            if (TipoMovimientoEnum.CREDITO.getDescripcion()
                .equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento())) {
                saldo = credito(cuenta.get().getSaldoInicial(),
                    BigDecimal.valueOf(movimientoEntradaDto.getValor()));
            } else if (TipoMovimientoEnum.DEBITO.getDescripcion()
                .equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento()))  {
                saldo = debito(cuenta.get().getSaldoInicial(),
                    BigDecimal.valueOf(movimientoEntradaDto.getValor()));

            }
            Movimiento movimiento = Movimiento.builder().cuenta(cuenta.get())
                .idCuenta(cuenta.get().getIdCuenta())
                .valor(BigDecimal.valueOf(Math.abs(movimientoEntradaDto.getValor())))
                .saldo(saldo).saldoAnterior(cuenta.get().getSaldoInicial())
                .tipoMovimiento(movimientoEntradaDto.getTipoMovimiento()).fecha(new Date())
                .build();
            //Actualizamos el saldo en al cuenta.
            cuenta.get().setSaldoInicial(movimiento.getSaldo());
            cuentaRepository.save(cuenta.get());
            return movimientoRepository.save(movimiento);
        } catch (CuentaException e) {
            throw new CuentaException(e);
        }
    }

    /**
     * Metodo que resta al saldo inicial el monto dado.
     *
     * @param saldoInicial
     * @param monto
     * @return
     * @throws CuentaException
     */
    private BigDecimal debito(BigDecimal saldoInicial, BigDecimal monto) throws CuentaException {
        BigDecimal nuevoSaldo = saldoInicial.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new CuentaException("Dinero insuficiente, para realizar el débito");
        }
        return saldoInicial = nuevoSaldo;
    }

    /**
     * Metodo que agrega al saldo el monto dado.
     *
     * @param saldoInicial
     * @param monto
     * @return
     */
    private BigDecimal credito(BigDecimal saldoInicial, BigDecimal monto) {
        return saldoInicial = saldoInicial.add(monto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Movimiento> obtenerPorNumeroCuenta(int numeroCuenta)
        throws CuentaException {
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findByNumeroCuenta(
                numeroCuenta);
            if (!cuenta.isPresent()) {
                throw new CuentaException("La cuenta no existe con el número de cuenta ingresado.");
            }
            return movimientoRepository.findByIdCuenta(cuenta.get().getIdCuenta());
        }catch (CuentaException e){
            throw new CuentaException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Movimiento update(MovimientoEntradaDto movimientoEntradaDto) throws CuentaException {
        try {
            Optional<Cuenta> cuenta = cuentaService.obtenerPorNumeroCuenta(
                movimientoEntradaDto.getNumeroCuenta());
            if (!cuenta.isPresent()) {
                throw new CuentaException("La cuenta no existe con el número de cuenta ingresado.");
            }

            List<Movimiento> movimientoList = obtenerPorNumeroCuenta(
                movimientoEntradaDto.getNumeroCuenta());
            if (movimientoList.isEmpty()) {
                throw new CuentaException(
                    "No existen movimientos a mostrar para el numero de cuenta ingresado.");
            }
            // Ordena la lista de movimientos por fecha (de más antiguo a más reciente)
            Collections.sort(movimientoList, Comparator.comparing(Movimiento::getFecha));

            // Obtiene el último movimiento (el más reciente)
            Movimiento movimiento = movimientoList.get(movimientoList.size() - 1);

            BigDecimal saldo = new BigDecimal(0);
            if (TipoMovimientoEnum.CREDITO.getDescripcion()
                .equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento())) {
                saldo = credito(cuenta.get().getSaldoInicial(),
                    BigDecimal.valueOf(movimientoEntradaDto.getValor()));
            } else if (TipoMovimientoEnum.DEBITO.getDescripcion()
                .equalsIgnoreCase(movimientoEntradaDto.getTipoMovimiento())) {
                saldo = debito(cuenta.get().getSaldoInicial(),
                        BigDecimal.valueOf(movimientoEntradaDto.getValor()));
            }

            movimiento.setTipoMovimiento(movimientoEntradaDto.getTipoMovimiento());
            movimiento.setValor(BigDecimal.valueOf(Math.abs(movimientoEntradaDto.getValor())));
            movimiento.setSaldo(saldo);
            movimiento.setFecha(new Date());

            //Actualizamos el saldo en la cuenta.
            cuenta.get().setSaldoInicial(movimiento.getSaldo());
            cuentaRepository.save(cuenta.get());
            return movimientoRepository.save(movimiento);
        } catch (CuentaException e) {
            throw new CuentaException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteByIdMovimiento(Long id) throws CuentaException {
        try {
            Optional<Movimiento> movimiento = movimientoRepository.findById(id);
            if (!movimiento.isPresent()) {
                throw new CuentaException(
                    "No se puede eliminar el registro con id: " + id + " no existe.");
            }
            movimientoRepository.deleteByIdMovimiento(movimiento.get().getIdMovimiento());
        } catch (CuentaException e) {
            throw new CuentaException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ReporteDto> generarReporte(String identififcacion, String fecha)
            throws CuentaException, RuntimeException {
        try {
            if (ObjectUtils.isEmpty(fecha)) {
                throw new CuentaException("Fechas Obligatorias.");
            }
            String[] fechas = fecha.split(",");
            if (fechas.length != 2) {
                throw new CuentaException(
                        "Coloque dos fechas con formato yyyyMMdd separadas como coma (.)");
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicial = formatter.parse(fechas[0]);
            Date fechaFinal = formatter.parse(fechas[1]);
            List<ReporteDto> lista = movimientoRepository.obtenerMovimientosPorIdentificacionPorFechas(
                    identififcacion, fechaInicial, fechaFinal);
            if (ObjectUtils.isEmpty(lista)) {
                throw new CuentaException("No se encuentra datos con los parametros indicados.");
            }
            return lista;

        } catch (CuentaException e) {
            throw new CuentaException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

