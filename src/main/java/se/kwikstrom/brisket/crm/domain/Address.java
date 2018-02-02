package se.kwikstrom.brisket.crm.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
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