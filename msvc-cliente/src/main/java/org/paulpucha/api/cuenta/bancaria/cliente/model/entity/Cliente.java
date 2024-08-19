package org.paulpucha.api.cuenta.bancaria.cliente.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.paulpucha.api.cuenta.bancaria.cliente.state.EstadoCliente;

/**
 * <b> Clase entidad de la tabla cliente. </b>
 *
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *     <p>
 *     [$Author: Paul Pucha $, $Date: 09 jul. 2024 $]
 *     </p>
 */
@DynamicUpdate
@Entity
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder
    public Cliente(Long clienteId, String direccion, int edad, String genero, String identificacion, String nombre,
        String telefono, String contrasena, EstadoCliente estado) {
        super(direccion, edad, genero, identificacion, nombre, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_Id")
    private Long clienteId;

    @NotBlank
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

}
