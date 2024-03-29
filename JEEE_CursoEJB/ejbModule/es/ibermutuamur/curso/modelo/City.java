package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Cacheable(false)
@NamedStoredProcedureQuery(name="descripcionCiudad2", procedureName="descripcionCiudad",multipleResultSets=false, resultClass=DescripcionCiudad.class, 
parameters={
	      @StoredProcedureParameter(direction=Direction.IN,  name="idCiudad", queryParameter="idCiudad", type=Integer.class),
	      @StoredProcedureParameter(direction=Direction.OUT, name="ciudad",   queryParameter="ciudad", type=String.class),
	      @StoredProcedureParameter(direction=Direction.OUT, name="pais",     queryParameter="pais", type=String.class)
	      }
)
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
	@Temporal(TemporalType.DATE)
	private Date lastUpdate;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="city")
	private List<Address> addresses;

	//bi-directional many-to-one association to Country
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;

	public City(int cityId,String city,Date lastUpdate,List<Address> addresses,Country country) {
		this.cityId =cityId;
		this.city   =city;
		this.lastUpdate =lastUpdate;
		this.addresses =addresses;
		this.country =country;
		System.out.println(city);
	}
	
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

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
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