package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inventory_id")
	private int inventoryId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="inventory")
	private List<Rental> rentals;

	public Inventory() {
	}

	public int getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	
	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	
	public Rental addRentals(Rental rentals) {
		getRentals().add(rentals);
		rentals.setInventory(this);

		return rentals;
	}

	public Rental removeRentals(Rental rentals) {
		getRentals().remove(rentals);
		rentals.setInventory(null);

		return rentals;
	}
}