package org.paulpucha.api.cuenta.bancaria.cuenta.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * <b> Clase entidad de la tabla movimiento. </b>
 * 
 * @author Paul Pucha
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Paul Pucha $, $Date: 08 jul. 2024 $]
 *          </p>
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMovimiento;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private BigDecimal saldo;

	@NotBlank
	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;

	@NotNull
	private BigDecimal valor;

	@Column(name = "saldo_anterior")
	private BigDecimal saldoAnterior;

	@NotNull
	@Column(name = "id_cuenta")
	private Long idCuenta;

	@Transient
	private List<Cuenta> cuentaList;

	/* bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente", insertable = false, updatable = false)
	@JsonIgnore
	private Cliente cliente;

	@Column
	private Long idCliente;*/

	// bi-directional many-to-one association to Cuenta
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cuenta", insertable = false, updatable = false)
	@JsonIgnore
	private Cuenta cuenta;



}