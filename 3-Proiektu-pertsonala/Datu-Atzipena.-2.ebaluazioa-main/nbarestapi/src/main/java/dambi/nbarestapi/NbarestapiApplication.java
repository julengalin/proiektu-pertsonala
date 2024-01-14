package dambi.nbarestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klase hau aplikazioaren main-a eraikizten du. Main honetan SpringBoot-ek bere
 * run funtzioa exekutatzen du eta aplikazioa martxan jartzen da. Horretarako, lehenik 
 * SpringBootApplication anotazioa erabiltzen da eta bestetik SprinApplication instantziaren
 * run funtzioa exekutatzen da.
 */
@SpringBootApplication
public class NbarestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbarestapiApplication.class, args);
	}

}
