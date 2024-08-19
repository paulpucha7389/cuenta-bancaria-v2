package org.paulpucha.api.cuenta.bancaria.cliente;

import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.paulpucha.api.cuenta.bancaria.cliente.service.impl.ClienteServiceImpl;
import org.paulpucha.api.cuenta.bancaria.cliente.exception.ClienteException;
import org.paulpucha.api.cuenta.bancaria.cliente.model.entity.Cliente;
import org.paulpucha.api.cuenta.bancaria.cliente.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    void whenObtenerClientePorIdentificacionThenNotNull() throws ClienteException {
        String identificacionEntrada = "1720693500";
        Cliente clienteSalidaEsperada = Cliente.builder().nombre("Jose Lema ")
            .direccion("Otavalo sn y principal ")
            .telefono("098254785").contrasena("1234").estado("True").build();
        Optional<Cliente> actualRespuesta;
        try {
            when(this.clienteRepository.obtenerPorIdentificacion(identificacionEntrada)).thenReturn(
                Optional.ofNullable(clienteSalidaEsperada));
            actualRespuesta = this.clienteService.obtenerPorIdentificacion(identificacionEntrada);
        } catch (ClienteException e) {
            throw new ClienteException(e);
        }
        Assert.assertEquals(Optional.ofNullable(clienteSalidaEsperada), actualRespuesta);
    }
}
