package org.paulpucha.api.cuenta.bancaria.cuenta.controller.util;

import java.util.HashMap;
import java.util.Map;
import org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto.salida.BaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidaWsUtil {

    /**
     * <b> Metodo para tratar los errores de validaciones de los datos de entrada. </b>
     * <p>
     * [Author: Paul Pucha, Date: 09 jul. 2024]
     * </p>
     *
     * @param resultado parametro de entrada
     * @return ResponseEntity<BaseResponseDto> lista o mensaje de error
     */
    public static ResponseEntity<BaseResponseDto> validar(
        BindingResult resultado) {
        Map<String, String> errores = new HashMap<>();
        resultado.getFieldErrors().forEach(error -> {
            errores.put(error.getField(),
                "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(BaseResponseDto.builder().data(errores).build());
    }

}
