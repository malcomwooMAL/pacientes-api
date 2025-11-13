package edu.senai.consultorio_backend.pacientes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the Pacientes API application.
 * This class is responsible for bootstrapping and launching the Spring Boot application.
 */
@SpringBootApplication
public class PacientesApiApplication {

	/**
	 * The main method, which serves as the entry point for the application.
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PacientesApiApplication.class, args);
	}

}
