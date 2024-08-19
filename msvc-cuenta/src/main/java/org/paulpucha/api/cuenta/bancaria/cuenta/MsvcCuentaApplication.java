package org.paulpucha.api.cuenta.bancaria.cuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcCuentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCuentaApplication.class, args);

	}
}
