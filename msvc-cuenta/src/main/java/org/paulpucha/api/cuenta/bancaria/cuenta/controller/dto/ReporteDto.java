package org.paulpucha.api.cuenta.bancaria.cuenta.controller.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ReporteDto {

        public Date getFecha();

        public String getCliente();

        public int getNumeroCuenta();

        public String getTipo();

        public BigDecimal getSaldoInicial();

        public String getEstado();

        public BigDecimal getMovimiento();

        public BigDecimal getSaldoDisponible();

        public String getTipoMovimiento();

    }
