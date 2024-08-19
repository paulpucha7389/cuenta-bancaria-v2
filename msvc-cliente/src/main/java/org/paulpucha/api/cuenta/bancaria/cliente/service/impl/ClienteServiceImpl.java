/**
 *
 */
package org.paulpucha.api.cuenta.bancaria.cliente.service.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.paulpucha.api.cuenta.bancaria.cliente.controller.dto.entrada.ClienteEntradaDto;
import org.paulpucha.api.cuenta.bancaria.cliente.exception.ClienteException;
import org.paulpucha.api.cuenta.bancaria.cliente.model.entity.Cliente;
import org.paulpucha.api.cuenta.bancaria.cliente.repository.ClienteRepository;
import org.paulpucha.api.cuenta.bancaria.cliente.service.ClienteService;
import org.paulpucha.api.cuenta.bancaria.cliente.state.EstadoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b> Servicio para el cliente. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 09 jul. 2024 $]
 *     </p>
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Cliente create(ClienteEntradaDto clienteEntradaDto) throws ClienteException {
        try {
            Optional<Cliente> clienteEncontrado = clienteRepository
                .obtenerPorIdentificacion(clienteEntradaDto.getIdentificacion());
            if (clienteEncontrado.isPresent()) {
                throw new ClienteException("No es posible crear este cliente porque ya existe.");
            } else {

                //Cambio estado cliente

                EstadoCliente estado = EstadoCliente.valueOf(clienteEntradaDto.getEstado());

                Cliente cliente = Cliente.builder().direccion(clienteEntradaDto.getDireccion())
                        .edad(clienteEntradaDto.getEdad())
                        .genero(clienteEntradaDto.getGenero())
                        .identificacion(clienteEntradaDto.getIdentificacion())
                        .nombre(clienteEntradaDto.getNombre())
                        .telefono(clienteEntradaDto.getTelefono())
                        .contrasena(clienteEntradaDto.getContrasena())
                        .estado(estado)
                        .build();

                return clienteRepository.save(cliente);
            }
        } catch (IllegalArgumentException e) {
            // Manejar excepción cuando el estado es inválido
            throw new ClienteException("Estado inválido: debe ser 'ACTIVO' o 'INACTIVO'", e);
        }
        catch (ClienteException e) {
            throw new ClienteException("Ocurrió un error al crear el cliente: " + e.getMessage(), e);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cliente> read() {
        return clienteRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Cliente update(ClienteEntradaDto clienteEntradaDto) throws ClienteException {
        try {
            Optional<Cliente> clienteEncontrado = clienteRepository.obtenerPorIdentificacion(
                clienteEntradaDto.getIdentificacion());

            if (clienteEncontrado.isPresent()) {
                EstadoCliente estado = EstadoCliente.valueOf(clienteEntradaDto.getEstado());
                Cliente cliente = clienteEncontrado.get();
                cliente.setNombre(clienteEntradaDto.getNombre());
                cliente.setGenero(clienteEntradaDto.getGenero());
                cliente.setEdad(clienteEntradaDto.getEdad());
                //cliente.setIdentificacion(clienteEntradaDto.getIdentificacion());
                cliente.setDireccion(clienteEntradaDto.getDireccion());
                cliente.setTelefono(clienteEntradaDto.getTelefono());
                cliente.setContrasena(clienteEntradaDto.getContrasena());
                cliente.setEstado(estado);
                return clienteRepository.save(cliente);

            } else {
                throw new ClienteException("Cliente no se encuentra registrado");
            }
        }catch (IllegalArgumentException e) {
            // Manejar excepción cuando el estado es inválido
            throw new ClienteException("Estado inválido: debe ser 'ACTIVO' o 'INACTIVO'", e);
        }
        catch (ClienteException e) {
            throw new ClienteException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void delete(String identificacion) throws ClienteException {
        try {
            Optional<Cliente> clienteEncontrado = clienteRepository.obtenerPorIdentificacion(
                identificacion);
            if (!clienteEncontrado.isPresent()) {
                throw new ClienteException("No se puede eliminar, cliente no encontrado");
            }
            clienteRepository.deleteById(clienteEncontrado.get().getClienteId());

        } catch (ClienteException e) {
            throw new ClienteException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Cliente> obtenerPorIdentificacion(String identificacion)
        throws ClienteException {
        
        try {
            Optional<Cliente> clienteEncontrado = clienteRepository
                .obtenerPorIdentificacion(identificacion);
            if (!clienteEncontrado.isPresent()) {
                throw new ClienteException("No existe cliente con la identificacion ingresada");
            }
            return clienteEncontrado;
        } catch (ClienteException e) {
            throw new ClienteException(e);
        }
    }
}
