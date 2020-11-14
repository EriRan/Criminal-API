package fi.eriran.criminalapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	/**
	 * Spring boot graphql starter finds resolvers and scalars with magic
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
