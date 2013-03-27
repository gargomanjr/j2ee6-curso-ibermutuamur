package modelo;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "SEQ_CITY", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", initialValue = 601)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_CITY")
	@Column(name="city_id")
	private int cityId;

	private String city;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="city")
	private List<Address> addresses;

	//bi-directional many-to-one association to Country
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;

	public City() {
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	
	public Address addAddresses(Address addresses) {
		getAddresses().add(addresses);
		addresses.setCity(this);

		return addresses;
	}

	public Address removeAddresses(Address addresses) {
		getAddresses().remove(addresses);
		addresses.setCity(null);

		return addresses;
	}
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
}