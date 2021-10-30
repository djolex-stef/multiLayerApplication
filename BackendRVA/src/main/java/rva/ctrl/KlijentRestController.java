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
import rva.jpa.Klijent;
import rva.repository.KlijentRepository;
import rva.repository.KreditRepository;


@Api(tags = {"Klijent CRUD operacije"})
@RestController
@CrossOrigin
public class KlijentRestController {
	
	@Autowired
	KlijentRepository klijentRepository;
	
	@Autowired
	KreditRepository kreditRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("klijent")
	@ApiOperation(value = "Vraca kolekciju svih Klijenta iz baze podataka")
	public Collection<Klijent> getKlijenti() {
		return this.klijentRepository.findAll();
	}
	
	
	
		
		
	@GetMapping("klijent/{id}")
	@ApiOperation(value = "Vraca Klijenta iz baze podataka za prosledjeni ID")
	public Klijent getOne(@PathVariable("id") Integer id) {
		return klijentRepository.getOne(id);
	}
	
	
	@GetMapping("klijent/ime/{ime}") 
	@ApiOperation(value = "Vraca kolekciju svih Klijenta iz baze podataka za prosledjeno IME")
	public Collection<Klijent> getKlijentByIme(@PathVariable("ime") String ime) { 
		return klijentRepository.findByImeContainingIgnoreCase(ime); 
	} 
	
	@PostMapping("klijent")
	@ApiOperation(value = "Dodavanje Klijenta u bazu podataka")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent){
		if(!klijentRepository.existsById(klijent.getId())) {
			klijentRepository.save(klijent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping(value = "klijent")
	@ApiOperation(value = "Modifikovanje Klijenta iz baze podataka")
	public ResponseEntity<Klijent> updateKlijent (@RequestBody Klijent klijent ){
		if(klijentRepository.existsById(klijent.getId())) {
			klijentRepository.save(klijent);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	
	@DeleteMapping("klijent/{id}")
	@ApiOperation(value = "Brisanje Klijenta iz baze podataka za prosledjeni ID")
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable("id") Integer id){
		if(!klijentRepository.existsById(id)) 
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		jdbcTemplate.execute("delete from racun where klijent = "+id);
		klijentRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(
					"insert into \"klijent\" (\"id\", \"broj_lk\", \"ime\", \"prezime\", \"kredit\")"
							+ " values (-100,61646, 'Test posle Delete', 'Test posle Delete', '1')");
						
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	

}


