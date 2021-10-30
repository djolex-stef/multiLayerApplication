package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*
 * Klasa predstavlja jednu java bean klasu, znaci da se u klasi nalaze varijable instanci i get i set metode 
 * za te varijable, 
 * da implementira serializable interface i da ima u ovom slucaju implicitni prazan konstruktor
 */
/**
 * The persistent class for the klijent database table.
 * 
 */
/*
 * -@Entity predstavlja JPA anotaciju, uloga anotacije je da stavi do znanja 
 * da se radi o entitetu koji ima id i koji se koristi kako bi se omogucila persistencija podatak, 
 * Klasa as @Entity predstavlja klasu koja se mapira u tabelu bazi podataka
 */

/*
 * -@JsonIgnore je FasterXml/Jackson anotacija koja oznacava da odredjenu varijablu instance treba ignorisati, 
 * bez ove anotacije stvorila bi se beskonacna petlja
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@NamedQuery(name="Klijent.findAll", query="SELECT k FROM Klijent k")

/*
 * @NamedQuery anotacija je takodje JPA anotacija koja omogucava odredjenom upitu, 
 * dati naziv po kom ga kasnije moze referencirati
 */

public class Klijent implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * -@Id predstavlja JPA anotaciju i oznacava varijablu instance koja mapira primarni kljuc u bazi podataka, 
	 * ukoliko je anotirana sa @entity mora imati varijablu instance koja ce biti anotirana sa @ID
	 */
	/*
	 * -@SequenceGenerator predstavlja JPA anotaciju koja se koristi kako bi se naveo naziv sekvence 
	 * u bazi podataka koja ce se koristiti za odredjivanje naredne vrednosti, 
	 * naziv, sekvence podataka i vrednost za allocationSize, bez ovog parametra id-evi bi imali negativnu vrednost
	 */
	/*
	 * -@GeneratedValue je JPA anotacija i oznacava 
	 * da ce vrednost biti automatski generisana i navodi neophodne parametre, strategiju, 
	 * generator koji ce se koristiti
	 */
	@Id
	@SequenceGenerator(name="KLIJENT_ID_GENERATOR", sequenceName="KLIJENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KLIJENT_ID_GENERATOR")
	private Integer id;

	@Column(name="broj_lk")
	private Integer brojLk;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Kredit
	@ManyToOne
	@JoinColumn(name="kredit")
	private Kredit kredit;

	//bi-directional many-to-one association to Racun
	@OneToMany(mappedBy="klijent")
	@JsonIgnore
	private List<Racun> racuns;

	public Klijent() {
	}
	
	public Klijent(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojLk() {
		return this.brojLk;
	}

	public void setBrojLk(Integer brojLk) {
		this.brojLk = brojLk;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Kredit getKredit() {
		return this.kredit;
	}

	public void setKredit(Kredit kredit) {
		this.kredit = kredit;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setKlijent(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setKlijent(null);

		return racun;
	}

}