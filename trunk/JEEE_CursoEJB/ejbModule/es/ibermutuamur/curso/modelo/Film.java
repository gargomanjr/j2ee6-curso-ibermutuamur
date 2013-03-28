package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.ObjectTypeConverter;
import org.eclipse.persistence.annotations.ConversionValue;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "SEQ_FILM", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", initialValue = 1001)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_FILM")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="film_id")
	private int filmId;

	@Lob
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="last_update")
	private Date lastUpdate;

	private int length;

	private String rating;

	@Temporal(TemporalType.DATE)
	@Column(name="release_year")
	private Date releaseYear;

	@Column(name="rental_duration")
	private byte rentalDuration;

	@Column(name="rental_rate")
	private BigDecimal rentalRate;

	@Column(name="replacement_cost")
	private BigDecimal replacementCost;
	
	@Column(name="special_features")
	@Enumerated(EnumType.STRING)
	@ObjectTypeConverter(name = "features", objectType = Features.class, dataType = String.class, conversionValues = {
		@ConversionValue(objectValue = "Trailers", dataValue = "Trailers"),
		@ConversionValue(objectValue = "Commentaries", dataValue = "Commentaries"),
		@ConversionValue(objectValue = "Deleted", dataValue = "Deleted Scenes"),
		@ConversionValue(objectValue = "Behind", dataValue = "Behind the Scenes") })
	@Convert("features")
	private Features specialFeatures;
	 

	public Features getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(Features specialFeatures) {
		this.specialFeatures = specialFeatures;
	}


	private String title;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language1;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	private Language language2;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film")
	private List<FilmActor> filmActors;

	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="film")
	private List<FilmCategory> filmCategories;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="film")
	private List<Inventory> inventories;
	
	@Transient
	private long diasDesdeUltAct;
	

	public Film() {
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}
	
	/*
	public Object getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(Object specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	 */
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage1() {
		return this.language1;
	}

	public void setLanguage1(Language language1) {
		this.language1 = language1;
	}

	
	public Language getLanguage2() {
		return this.language2;
	}

	public void setLanguage2(Language language2) {
		this.language2 = language2;
	}

	
	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	
	public FilmActor addFilmActors(FilmActor filmActors) {
		getFilmActors().add(filmActors);
		filmActors.setFilm(this);

		return filmActors;
	}

	public FilmActor removeFilmActors(FilmActor filmActors) {
		getFilmActors().remove(filmActors);
		filmActors.setFilm(null);

		return filmActors;
	}
	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	
	public FilmCategory addFilmCategories(FilmCategory filmCategories) {
		getFilmCategories().add(filmCategories);
		filmCategories.setFilm(this);

		return filmCategories;
	}

	public FilmCategory removeFilmCategories(FilmCategory filmCategories) {
		getFilmCategories().remove(filmCategories);
		filmCategories.setFilm(null);

		return filmCategories;
	}
	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	
	public Inventory addInventories(Inventory inventories) {
		getInventories().add(inventories);
		inventories.setFilm(this);

		return inventories;
	}

	public Inventory removeInventories(Inventory inventories) {
		getInventories().remove(inventories);
		inventories.setFilm(null);

		return inventories;
	}

	public long getDiasDesdeUltAct() {
		Date hoy = new Date();
		long diffTime = hoy.getTime() - this.getLastUpdate().getTime();
		long diffDays = diffTime / (1000 * 60 * 60 * 24);		
		return diffDays;
	}
	

	public enum Features { 
		Trailers,
		Commentaries,
		Deleted,
		Behind
	} 
}