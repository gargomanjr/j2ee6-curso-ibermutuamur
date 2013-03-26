package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private int categoryId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private String name;

	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="category")
	private List<FilmCategory> filmCategories;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	
	public FilmCategory addFilmCategories(FilmCategory filmCategories) {
		getFilmCategories().add(filmCategories);
		filmCategories.setCategory(this);

		return filmCategories;
	}

	public FilmCategory removeFilmCategories(FilmCategory filmCategories) {
		getFilmCategories().remove(filmCategories);
		filmCategories.setCategory(null);

		return filmCategories;
	}
}