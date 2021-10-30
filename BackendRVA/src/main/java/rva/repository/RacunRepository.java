package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Racun;
import rva.jpa.Klijent;
import rva.jpa.TipRacuna;

public interface RacunRepository extends JpaRepository<Racun, Integer>{

	Collection<Racun> findByKlijent(Klijent klijent);
	Collection<Racun> findByTipRacuna(TipRacuna tipracuna);
	
}
