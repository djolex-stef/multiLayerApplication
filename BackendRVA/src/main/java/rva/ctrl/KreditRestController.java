package rva.ctrl;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Kredit;
import rva.repository.KreditRepository;

/*
 * -RestController predstavlja anotaciju koja se koristi na nivou klase, to je kombinacija @Controller i @ResponseBody
-Controller - anotacija koja se koristi da bi se oznacilo da se radi o klasi koja je spring controller 
i moze da se koristi npr u Spring MVC
-ResponseBody - oznacava da rezultat izvrsavanja treba da bude smesten u Response Body-ju u zeljenom formatu
 */

/*
 * -RequestMapping - predstavlja anotaciju koja se moze koristiti na nivou klase i na noviu metode.
Sluzi za mapiranje web zahteva na odredjene klase ili metode, u zagradi se navodi putanja
 */
@Api(tags = {"Kredit CRUD operacije"})
@RestController
@CrossOrigin
public class KreditRestController {
	/*
	 * -@autowired se moze primeniti nad varijablom instance, setter metodama i konstruktorima. 
	 * Oznacava da je neophodno injektovati zavisni objekat. 
	 * Prilikom pokretanja aplikacije IOC kontejner prolazi kroz kompletan kod trazeci 
	 * anotacije koje oznacavaju da je potrebno kreirati objekte
	*/
	/*-Upotrebnom @Autowired stavljeno je do znanja da je potrebno 
	kreirati objekt klase koja ce implementirati repozitorijum i proslediti 
	klasi RestController referencu na taj objekat
	 */
	@Autowired
	KreditRepository kreditRepository;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("kredit")
	@ApiOperation(value = "Vraca kolekciju svih Kredita iz baze podataka")
	public Collection<Kredit> getKrediti(){
		return this.kreditRepository.findAll();
	}
	
	
	
		
		
	@GetMapping("kredit/{id}")
	@ApiOperation(value = "Vraca Kredit iz baze podataka za prosledjeni ID")
	public Kredit getOne(@PathVariable("id") Integer id) {
		return kreditRepository.getOne(id);
	}
	
	
	@GetMapping("kredit/naziv/{naziv}") 
	@ApiOperation(value = "Vraca Kredit iz baze podataka za prosledjeni naziv")
	public Collection<Kredit> getkreditByNaziv(@PathVariable("naziv") String naziv) { 
		return kreditRepository.findByNazivContainingIgnoreCase(naziv); 
	} 
	
	@PostMapping("kredit")
	@ApiOperation(value = "Dodavanje kredita u bazu podataka")
	public ResponseEntity<Kredit> insertKredit(@RequestBody Kredit kredit){
		if(!kreditRepository.existsById(kredit.getId())) {
			kreditRepository.save(kredit);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("kredit")
	@ApiOperation(value = "Modifikovanje kredita iz baze podataka")
	public ResponseEntity<Kredit> updateKredit (@RequestBody Kredit kredit ){
		if(kreditRepository.existsById(kredit.getId())) {
			kreditRepository.save(kredit);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	/*
	 * -JDBCTemplate klasa pojednostavljuje koriscenje JDBC ( java database Connectivity )
	 */
	
	@DeleteMapping("kredit/{id}")
	@ApiOperation(value = "Brisanje kredita iz baze podataka za prosledjeni ID")
	public ResponseEntity<Kredit> deletekredit(@PathVariable("id") Integer id){
		if(!kreditRepository.existsById(id)) 
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		kreditRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(
					"insert into \"kredit\" (\"id\", \"naziv\", \"oznaka\", \"opis\")"
							+ " values (-100,'Test posle Delete', 'Test posle Delete', 'Test posle Delete')");
						
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	

}


