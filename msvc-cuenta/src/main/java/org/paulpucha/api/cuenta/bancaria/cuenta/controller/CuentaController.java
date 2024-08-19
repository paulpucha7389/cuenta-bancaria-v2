/**
 *
 */
package org.paulpucha.api.cuenta.bancaria.cuenta.controller;

import javax.validation.Valid;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.entrada.CuentaEntradaDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.salida.BaseResponseDto;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.util.ValidaWsUtil;
import org.paulpucha.api.cuenta.bancaria.cuenta.service.CuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b> Clase controlador de las cuentas. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *     </p>
 */
@RestController
@RequestMapping("/api/cuentas")
public class
CuentaController {

    private static final Logger log = LoggerFactory.getLogger(CuentaController.class);
    private static final String ERROR_MN = "Ha ocurrido un error {}";

    @Autowired
    private CuentaService service;

    /**
     * <b> Metodo que crea cuentas. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param cuentaEntradaDto parametro de entrada
     * @return ResponseEntity<BaseResponseDto> objeto o mensaje de error
     */
    @PostMapping
    public ResponseEntity<BaseResponseDto> guardar(
        @Valid @RequestBody CuentaEntradaDto cuentaEntradaDto,
        BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return ValidaWsUtil.validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponseDto.builder().data(service.create(cuentaEntradaDto)).build());
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest()
                .body(BaseResponseDto.builder().message(e.getCause().getMessage()).build());
        }
    }

    /**
     * <b> Metodo para obtener las cuentas de un cliente por su identificacion. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param identificacion parametro de entrada
     * @return ResponseEntity<BaseResponseDto> lista o mensaje de error
     **/
    @GetMapping(path = "/{identificacion}")
    public ResponseEntity<BaseResponseDto> obtenerCuentasPorCliente(
        @PathVariable String identificacion) {
        try {
            return ResponseEntity.ok().body(
                BaseResponseDto.builder().data(service.obtenerCuentasPorCliente(identificacion))
                    .build());
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest()
                .body(BaseResponseDto.builder().message(e.getCause().getMessage()).build());
        }
    }

    /**
     * <b> Metodo que actualiza la cuenta. </b>
     * <p>
     * [Author: Paul Pucha, Date: 08 jul. 2024]
     * </p>
     *
     * @param cuentaEntradaDto parametro de entrada
     * @return ResponseEntity<BaseResponseDto> lista o mensaje de error
     */
    @PutMapping
    public ResponseEntity<BaseResponseDto> actualizar(
        @Validated @RequestBody CuentaEntradaDto cuentaEntradaDto,
        BindingResult resultado) {
        try {
            if (resultado.hasErrors()) {
                return ValidaWsUtil.validar(resultado);
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponseDto.builder().data(service.update(cuentaEntradaDto)).build());
        } catch (Exception e) {
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
            service.delete(id);
            return ResponseEntity.ok()
                .body(BaseResponseDto.builder().message("Registro Eliminado").build());
        } catch (Exception e) {
            log.error(ERROR_MN, e.getCause().getMessage());
            return ResponseEntity.badRequest()
                .body(BaseResponseDto.builder().message(e.getCause().getMessage()).build());
        }
    }
}
