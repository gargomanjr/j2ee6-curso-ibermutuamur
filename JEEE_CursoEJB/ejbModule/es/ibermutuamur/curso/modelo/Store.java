package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="store_id")
	private byte storeId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="store")
	private List<Customer> customers;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="store")
	private List<Inventory> inventories;

	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="store")
	private List<Staff> staffs;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="manager_staff_id")
	private Staff staff;

	public Store() {
	}

	public byte getStoreId() {
		return this.storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	
	public Customer addCustomers(Customer customers) {
		getCustomers().add(customers);
		customers.setStore(this);

		return customers;
	}

	public Customer removeCustomers(Customer customers) {
		getCustomers().remove(customers);
		customers.setStore(null);

		return customers;
	}
	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	
	public Inventory addInventories(Inventory inventories) {
		getInventories().add(inventories);
		inventories.setStore(this);

		return inventories;
	}

	public Inventory removeInventories(Inventory inventories) {
		getInventories().remove(inventories);
		inventories.setStore(null);

		return inventories;
	}
	public List<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	
	public Staff addStaffs(Staff staffs) {
		getStaffs().add(staffs);
		staffs.setStore(this);

		return staffs;
	}

	public Staff removeStaffs(Staff staffs) {
		getStaffs().remove(staffs);
		staffs.setStore(null);

		return staffs;
	}
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	
}