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
import rva.jpa.TipRacuna;
import rva.repository.TipRacunaRepository;

@Api(tags = {"Tip Racuna CRUD operacije"})
@RestController
@CrossOrigin
public class TipRacunaRestController {
	
	@Autowired
	TipRacunaRepository tipracunaRepository;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("tipracuna")
	@ApiOperation(value = "Vraca kolekciju svih Tipova Racuna iz baze podataka")
	public Collection<TipRacuna> getTipRacuna(){
		return this.tipracunaRepository.findAll();
	}
	
	
	
		
		
	@GetMapping("tipracuna/{id}")
	@ApiOperation(value = "Vraca Tip Racuna iz baze podataka za prosledjeni ID")
	public TipRacuna getOne(@PathVariable("id") Integer id) {
		return tipracunaRepository.getOne(id);
	}
	
	
	@GetMapping("tipracuna/naziv/{naziv}") 
	@ApiOperation(value = "Vraca kolekciju Racuna iz baze podataka za prosledjeni Naziv")
	public Collection<TipRacuna> getTipRacunaByNaziv(@PathVariable("naziv") String naziv) { 
		return tipracunaRepository.findByNazivContainingIgnoreCase(naziv); 
	} 
	
	@PostMapping("tipracuna")
	@ApiOperation(value = "Dodavanje Tipa Racuna u bazu podataka")
	public ResponseEntity<TipRacuna> insertTipRacuna(@RequestBody TipRacuna tipracuna){
		if(!tipracunaRepository.existsById(tipracuna.getId())) {
			tipracunaRepository.save(tipracuna);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("tipracuna")
	@ApiOperation(value = "Modifikovanje Tipa Racuna iz baze podataka")
	public ResponseEntity<TipRacuna> updateTipRacuna (@RequestBody TipRacuna tipracuna ){
		if(tipracunaRepository.existsById(tipracuna.getId())) {
			tipracunaRepository.save(tipracuna);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	
	@DeleteMapping("tipracuna/{id}")
	@ApiOperation(value = "Brisanje Tipa Racuna iz baze podataka za prosledjeni ID")
	public ResponseEntity<TipRacuna> deleteTipRacuna(@PathVariable("id") Integer id){
		if(!tipracunaRepository.existsById(id)) 
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		tipracunaRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(
					"insert into \"tip_racuna\" (\"id\", \"naziv\", \"oznaka\", \"opis\")"
							+ " values (-100,'Test posle Delete', 'Test posle Delete', 'Test posle Delete')");
						
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	

}


