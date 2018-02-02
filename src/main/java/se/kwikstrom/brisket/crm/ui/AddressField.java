package se.kwikstrom.brisket.crm.ui;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.crm.domain.Address;

public class AddressField extends CustomField<Address> {
	private TextField address1 = new TextField("Adress");
	private TextField address2 = new TextField();
	private TextField zipCode = new TextField("Postkod");
	private TextField postal = new TextField("Postort");
	private Binder<Address> binder = new BeanValidationBinder<Address>(Address.class);

	@Override
	public Address getValue() {
		return binder.getBean();
	}

	@Override
	protected Component initContent() {
		binder.bindInstanceFields(this);
		VerticalLayout verticalLayout = new VerticalLayout(address1, address2, zipCode, postal);
		verticalLayout.setMargin(true);
		return verticalLayout;
	}

	@Override
	protected void doSetValue(Address value) {
		binder.setBean(value);
	}

}
