package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the language database table.
 * 
 */
@Cacheable
@Entity
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "SEQ_LANGUAJE", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", initialValue = 7,allocationSize =1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="SEQ_LANGUAJE")
	@Column(name="language_id")
	private int languageId;

	@Temporal(TemporalType.DATE)
	@Column(name="last_update")
	private Date lastUpdate;

	private String name;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language1")
	private List<Film> films1;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language2")
	private List<Film> films2;

	public Language() {
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms1() {
		return this.films1;
	}

	public void setFilms1(List<Film> films1) {
		this.films1 = films1;
	}

	
	public Film addFilms1(Film films1) {
		getFilms1().add(films1);
		films1.setLanguage1(this);

		return films1;
	}

	public Film removeFilms1(Film films1) {
		getFilms1().remove(films1);
		films1.setLanguage1(null);

		return films1;
	}
	public List<Film> getFilms2() {
		return this.films2;
	}

	public void setFilms2(List<Film> films2) {
		this.films2 = films2;
	}

	
	public Film addFilms2(Film films2) {
		getFilms2().add(films2);
		films2.setLanguage2(this);

		return films2;
	}

	public Film removeFilms2(Film films2) {
		getFilms2().remove(films2);
		films2.setLanguage2(null);

		return films2;
	}
}