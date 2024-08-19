package org.paulpucha.api.cuenta.bancaria.cuenta;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.paulpucha.api.cuenta.bancaria.cuenta.model.entity.Cuenta;
import org.paulpucha.api.cuenta.bancaria.cuenta.repository.CuentaRepository;
import org.paulpucha.api.cuenta.bancaria.cuenta.service.impl.CuentaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CuentaTest {

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @Mock
    private CuentaRepository cuentaRepository;

    @Test
    void whenObtenerCuentaPorNumeroCuentaThenNotNull() {
        int datosEntrada = 456789;

        Cuenta cuentaEsperada = Cuenta.builder().numeroCuenta(456789).idCuenta(1L).idCliente(1L).
            tipoCuenta("Ahorros").saldoInicial(BigDecimal.valueOf(1000L)).estado("True").build();

        Optional<Cuenta> respuestaActual;
        when(this.cuentaRepository.findByNumeroCuenta(datosEntrada)).thenReturn(
            Optional.ofNullable(cuentaEsperada));
        respuestaActual = this.cuentaService.obtenerPorNumeroCuenta(datosEntrada);
        Assert.assertNotNull(respuestaActual);
    }

    @Test
    void whenObtenerCuentaPorNumeroCuentaThenOptionalEmpty() {
        int datosEntrada = 456789;

        // Configura el mock para que devuelva un Optional vacío cuando no se encuentra la cuenta
        when(this.cuentaRepository.findByNumeroCuenta(datosEntrada)).thenReturn(Optional.empty());

        // Ejecuta el método bajo prueba
        Optional<Cuenta> respuestaActual = this.cuentaService.obtenerPorNumeroCuenta(datosEntrada);

        // Verifica que la respuesta es Optional.empty()
        assertTrue(respuestaActual.isEmpty(), "No se puedo encuentrar la cuenta");
    }
}
