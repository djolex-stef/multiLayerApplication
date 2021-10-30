package rva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 -SpringBootApplication - anotacija se postavlja na klasi koja ce se koristiti za pokretanje aplikacije. Klasa koja pokrece aplikaciju mora se nalaziti u osnovnom paketu. Predstavlja kombinaciju anotacija @Configuration , @EnableAutoConfiguration i @ComponentScan

-@Configuration anotacija koja oznacava klasu koja definise Spring bean-ove
-@EnableAutoConfiguration anotacija koja oznacava klasu koja ce kreirati spring beanove
-@ComponentScan anotacija koja oznacava gde ce se traziit klase, metode, ili varijable instanci
 */
@SpringBootApplication
public class BackendRvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendRvaApplication.class, args);
	}

}
