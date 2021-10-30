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
import rva.jpa.Racun;
import rva.jpa.Klijent;
import rva.jpa.TipRacuna;
import rva.repository.RacunRepository;
import rva.repository.KlijentRepository;
import rva.repository.TipRacunaRepository;


@Api(tags = {"Racun CRUD operacije"})
@RestController
@CrossOrigin
public class RacunRestController {

	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@Autowired
	private TipRacunaRepository tipracunaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("racun")
	@ApiOperation(value = "Vraca kolekciju svih racuna iz baze podataka")
	public Collection<Racun> getRacuni() {
		return this.racunRepository.findAll();
	}

	
	@GetMapping("racun/{id}")
	@ApiOperation(value = "Vraca racun iz baze podataka za prosledjeni ID")
	public Racun getOne(@PathVariable("id") Integer id) {
		return racunRepository.getOne(id);
	}
	

	@GetMapping("racunpoklijentu/{id}")
	@ApiOperation(value = "Vraca kolekciju racuna iz baze podataka za prosledjenu vrednost stranog kljuca Klijent")
	public Collection<Racun> getRacunByKlijent(@PathVariable("id") int id) { 
		Klijent klijent = klijentRepository.getOne(id);
		return racunRepository.findByKlijent(klijent);
	} 
	
	@GetMapping("racunpotipuracuna/{id}") 
	@ApiOperation(value = "Vraca kolekciju racuna iz baze podataka za prosledjenu vrednost stranog kljuca TipRacuna")
	public Collection<Racun> getRacunByTipRacuna(@PathVariable("id") int id) { 
		TipRacuna tipracuna = tipracunaRepository.getOne(id);
		return racunRepository.findByTipRacuna(tipracuna);
	} 
	
	@PostMapping("racun")
	@ApiOperation(value = "Dodaje racun u bazu podataka")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("racun")
	@ApiOperation(value = "Modifikuje Racun iz baze podataka")
	public ResponseEntity<Racun> updateRacun (@RequestBody Racun racun) {
		if(racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@DeleteMapping("racun/{id}") 
	@ApiOperation(value = "Brisanje Racuna iz baze podataka za prosledjeni ID")
	public ResponseEntity<Racun> deleteRacun(@PathVariable("id") Integer id){
		if(!racunRepository.existsById(id)) 
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		racunRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(
					"insert into \"racun\" (\"id\", \"naziv\", \"oznaka\", \"opis\", \"tip_racuna\", \"klijent\")"
							+ " values (-100,'Test posle Delete', 'Test posle Delete', 'Test posle Delete', 1 , 1)");
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
