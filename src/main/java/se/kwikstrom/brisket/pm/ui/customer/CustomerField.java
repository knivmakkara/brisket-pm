package se.kwikstrom.brisket.pm.ui.customer;

import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.pm.domain.Customer;

public class CustomerField extends CustomField<Customer> {
	private TextField name = new TextField("Namn");
	private TextField phone = new TextField("Telefon");
	private TextField email = new TextField("E-post");
	private AddressField deliveryAddress = new AddressField();
	private AddressField visitationAddress = new AddressField();
	private Binder<Customer> binder = new Binder<Customer>(Customer.class);

	public CustomerField() {
	}

	@Override
	public Customer getValue() {
		return binder.getBean();
	}

	@Override
	protected Component initContent() {
		binder.bindInstanceFields(this);
		VerticalLayout verticalLayout = new VerticalLayout(name, phone, email,
		    new HorizontalLayout(deliveryAddress, visitationAddress));
		verticalLayout.setMargin(false);
		return verticalLayout;
	}

	@Override
	protected void doSetValue(Customer value) {
		binder.setBean(value);
	}

}
