package se.kwikstrom.brisket.pm.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	@Size(min = 1)
	private String name;
	private String email;
	@Pattern(regexp = "[\\d-\\s]+")
	private String phone;
	private String notes;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "address1", column = @Column(name = "delivery_address1")),
	    @AttributeOverride(name = "address2", column = @Column(name = "delivery_address2")),
	    @AttributeOverride(name = "zipCode", column = @Column(name = "delivery_zipCode")),
	    @AttributeOverride(name = "postal", column = @Column(name = "delivery_postal")), })
	private Address deliveryAddress = new Address();
	@Embedded
	private Address visitationAddress = new Address();

	public Customer(Integer id, String name, String email, String phone, String notes, Address deliveryAddress,
	    Address visitationAddress) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.notes = notes;
		this.deliveryAddress = deliveryAddress;
		this.visitationAddress = visitationAddress;
	}

	public Customer(Customer c) {
		this(c.id, c.name, c.email, c.phone, c.notes,
		    c.deliveryAddress != null ? new Address(c.deliveryAddress) : new Address(),
		    c.visitationAddress != null ? new Address(c.visitationAddress) : new Address());
	}

	public Customer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Address getVisitationAddress() {
		return visitationAddress;
	}

	public void setVisitationAddress(Address visitationAddress) {
		this.visitationAddress = visitationAddress;
	}

	@Embeddable
	public static class Address {
		private String address1;
		private String address2;
		private String zipCode;
		private String postal;

		public Address(String address1, String address2, String zipCode, String postal) {
			super();
			this.address1 = address1;
			this.address2 = address2;
			this.zipCode = zipCode;
			this.postal = postal;
		}

		public Address() {
		}

		public Address(Address address) {
			this(address.address1, address.address2, address.zipCode, address.postal);
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public String getPostal() {
			return postal;
		}

		public void setPostal(String postal) {
			this.postal = postal;
		}

		@Override
		public String toString() {
			return "Address [address1=" + address1 + ", address2=" + address2 + ", zipCode=" + zipCode + ", postal=" + postal
			    + "]";
		}

	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", deliveryAddress="
		    + deliveryAddress + ", visitationAddress=" + visitationAddress + "]";
	}

}
