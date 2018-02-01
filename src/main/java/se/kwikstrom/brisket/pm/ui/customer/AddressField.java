package se.kwikstrom.brisket.pm.ui.customer;

import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.pm.domain.Customer.Address;

public class AddressField extends CustomField<Address> {
	private TextField address1 = new TextField("Adress");
	private TextField address2 = new TextField("");
	private TextField zipCode = new TextField("Postkod");
	private TextField postal = new TextField("Postort");
	private Binder<Address> binder = new Binder<Address>(Address.class);

	@Override
	public Address getValue() {
		return binder.getBean();
	}

	@Override
	protected Component initContent() {
		binder.bindInstanceFields(this);
		VerticalLayout verticalLayout = new VerticalLayout(address1, address2, zipCode, postal);
		verticalLayout.setMargin(false);
		return verticalLayout;
	}

	@Override
	protected void doSetValue(Address value) {
		binder.setBean(value);
	}

}
