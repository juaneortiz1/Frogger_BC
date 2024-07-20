package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FroggerArswApplication {

	public static void main(String[] args) {
		SpringApplication.run(FroggerArswApplication.class, args);
	}
}


