package se.kwikstrom.brisket.pm.ui.customer;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.pm.domain.Customer;

public class CustomerField extends CustomField<Customer> {
	private TextField name = new TextField("Namn");
	private TextField phone = new TextField("Telefon");
	private TextField email = new TextField("E-post");
	private TextArea notes = new TextArea("Anteckningar");
	private AddressField deliveryAddress = new AddressField();
	private AddressField visitationAddress = new AddressField();
	private Binder<Customer> binder = new BeanValidationBinder<Customer>(Customer.class);

	public CustomerField() {
	}

	@Override
	public Customer getValue() {
		return binder.getBean();
	}

	@Override
	protected Component initContent() {
		binder.bindInstanceFields(this);
		Panel deliveryPanel = new Panel("Leverans-/Faktureringsadress", deliveryAddress);
		Panel visitationPanel = new Panel("Besöksadress", visitationAddress);

		VerticalLayout verticalLayout2 = new VerticalLayout(notes);
		VerticalLayout verticalLayout = new VerticalLayout(
		    new Panel("Allmänt", new HorizontalLayout(new VerticalLayout(name, phone, email), verticalLayout2)),
		    new HorizontalLayout(deliveryPanel, visitationPanel));
		verticalLayout.setMargin(false);
		return verticalLayout;
	}

	@Override
	protected void doSetValue(Customer value) {
		binder.setBean(value);
	}

}
