package com.cr12.primeraweb;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrimerawebApplicationTests {

	static {
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();

		if (dotenv.get("DB_URL") != null)
			System.setProperty("DB_URL", dotenv.get("DB_URL"));

		if (dotenv.get("DB_USER") != null)
			System.setProperty("DB_USER", dotenv.get("DB_USER"));

		if (dotenv.get("DB_PASSWORD") != null)
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
	}

	@Test
	void contextLoads() {
	}

}
