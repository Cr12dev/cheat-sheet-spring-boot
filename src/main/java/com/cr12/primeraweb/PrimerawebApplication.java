package com.cr12.primeraweb;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimerawebApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		// 2. IMPORTANTE: Registrar las variables en el sistema antes de arrancar Spring
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD") != null ? dotenv.get("DB_PASSWORD") : "");

		// 3. Ahora sí, arrancar la aplicación
		SpringApplication.run(PrimerawebApplication.class, args);
	}

}
