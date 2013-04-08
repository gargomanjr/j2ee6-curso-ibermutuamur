package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version 
	@Column
	int version;
	
	@TableGenerator(name = "SEQ_COUNTRY", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", initialValue = 110,allocationSize=1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="SEQ_COUNTRY")
	@Column(name="country_id")
	private int countryId;

	private String country;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country")
	private List<City> cities;

	public Country() {
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	
	public City addCities(City cities) {
		getCities().add(cities);
		cities.setCountry(this);

		return cities;
	}

	public City removeCities(City cities) {
		getCities().remove(cities);
		cities.setCountry(null);

		return cities;
	}
	
	public int getVersion() { 
	    return version; 
	}
}