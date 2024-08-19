/**
 *
 */
package org.paulpucha.api.cuenta.bancaria.cuenta.controller;

import javax.validation.Valid;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.entrada.MovimientoEntradaDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.salida.BaseResponseDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.util.ValidaWsUtil;
import org.paulpucha.api.cuenta.bancaria.cuenta.exception.CuentaException;
import org.paulpucha.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.paulpucha.api.cuenta.bancaria.cuenta.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <b> Clase controlador para los movimientos contables. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 09 abr. 2022 $]
 *     </p>
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);
    private static final String ERROR_MN = "Ha ocurrido un error {}";

    @Autowired
    private MovimientoService service;

    @Autowired
    private CuentaService cuentaService;

    /**
     * <b> Metodo que crea los movimientos. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param movimientoEntradaDto parametro de entrada
     * @return ResponseEntity<BaseResponseDto> lista o mensaje de error
     */
    @PostMapping(path = "/create")
    public ResponseEntity<BaseResponseDto> guardar(
            @Valid @RequestBody MovimientoEntradaDto movimientoEntradaDto, BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return ValidaWsUtil.validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(BaseResponseDto.builder().data(service.create(movimientoEntradaDto)).build());
        } catch (CuentaException e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest()
                    .body(BaseResponseDto.builder().message(e.getCause().getMessage()).build());
        }
    }

    /**
     * <b> Metodo que obtiene todos los movimientos existentes. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @return ResponseEntity<BaseResponseDto> lista o mensaje de error
     **/
    @GetMapping(path = "/{numeroCuenta}")
    public ResponseEntity<BaseResponseDto> obtenerPorNumeroCuenta(@PathVariable int numeroCuenta)
        throws CuentaException {
        try {
            return ResponseEntity.ok().body(
                BaseResponseDto.builder().data(service.obtenerPorNumeroCuenta(numeroCuenta))
                    .build());
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest()
                .body(BaseResponseDto.builder().message(e.getCause().getMessage()).build());
        }
    }

    /**
     * <b> Metodo que actualiza el movimiento. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param movimientoEntradaDto parametro de entrada
     * @return ResponseEntity<BaseResponseDto> lista o mensaje de error
     */
    @PutMapping
    public ResponseEntity<BaseResponseDto> actualizar(
        @Valid @RequestBody MovimientoEntradaDto movimientoEntradaDto, BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return ValidaWsUtil.validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponseDto.builder().data(service.update(movimientoEntradaDto)).build());
        } catch (CuentaException e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest()
                .body(BaseResponseDto.builder().message(e.getCause().getMessage()).build());
        }
    }

    /**
     * <b> Metodo que elimina un registro por su id. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param id parametro de entrada
     * @return ResponseEntity<BaseResponseDto> lista o mensaje de error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> eliminar(@PathVariable("id") Long id) {
        try {
            service.deleteByIdMovimiento(id);
            return ResponseEntity.ok()
                .body(BaseResponseDto.builder().message("Registro Eliminado").build());
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest()
                .body(BaseResponseDto.builder().message(e.getCause().getMessage()).build());
        }
    }

    @GetMapping(value = "/reportes")
    public ResponseEntity<?> generarReporte(@RequestParam String identificacion,
                                            @RequestParam String fecha) {
        try {
            return ResponseEntity.ok().body(service.generarReporte(identificacion, fecha));
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest().body(e.getCause().getMessage());
        }
    }
}
