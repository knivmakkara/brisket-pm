package se.kwikstrom.brisket.crm.domain;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Promemoria {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDateTime due;
	@OneToOne
	private Customer customer;
	private String customerPhone;
	private String customerEmail;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "address1", column = @Column(name = "delivery_address1")),
	    @AttributeOverride(name = "address2", column = @Column(name = "delivery_address2")),
	    @AttributeOverride(name = "zipCode", column = @Column(name = "delivery_zipCode")),
	    @AttributeOverride(name = "postal", column = @Column(name = "delivery_postal")), })
	private Address deliverTo = new Address();
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "address1", column = @Column(name = "invoice_address1")),
	    @AttributeOverride(name = "address2", column = @Column(name = "invoice_address2")),
	    @AttributeOverride(name = "zipCode", column = @Column(name = "invoice_zipCode")),
	    @AttributeOverride(name = "postal", column = @Column(name = "invoice_postal")), })
	private Address invoiceTo = new Address();

	private String menu;
	private String allergies;
	private String receivedBy;
	private String deliveryType;
	private String staff;
	private String rental;
	private String misc;

	public Promemoria(Integer id, LocalDateTime due, Customer customer, String customerPhone, String customerEmail,
	    Address deliverTo, Address invoiceTo, String menu, String allergies, String receivedBy, String deliveryType,
	    String staff, String rental, String misc) {
		super();
		this.id = id;
		this.due = due;
		this.customer = customer;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.deliverTo = deliverTo;
		this.invoiceTo = invoiceTo;
		this.menu = menu;
		this.allergies = allergies;
		this.receivedBy = receivedBy;
		this.deliveryType = deliveryType;
		this.staff = staff;
		this.rental = rental;
		this.misc = misc;
	}

	public Promemoria(Promemoria other) {
		this(other.id, other.due, other.customer, other.customerPhone, other.customerEmail,
		    other.deliverTo != null ? other.deliverTo : new Address(),
		    other.invoiceTo != null ? other.invoiceTo : new Address(), other.menu, other.allergies, other.receivedBy,
		    other.deliveryType, other.staff, other.rental, other.misc);
	}

	public Promemoria() {
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getDue() {
		return due;
	}

	public void setDue(LocalDateTime due) {
		this.due = due;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Address getDeliverTo() {
		return deliverTo;
	}

	public void setDeliverTo(Address deliverTo) {
		this.deliverTo = deliverTo;
	}

	public Address getInvoiceTo() {
		return invoiceTo;
	}

	public void setInvoiceTo(Address invoiceTo) {
		this.invoiceTo = invoiceTo;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getRental() {
		return rental;
	}

	public void setRental(String rental) {
		this.rental = rental;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public String getCustomerName() {
		return this.customer != null ? customer.getName() : null;
	}

}
