package se.kwikstrom.brisket.crm.ui.customer;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.crm.domain.Customer;
import se.kwikstrom.brisket.crm.ui.AddressField;

public class CustomerField extends CustomField<Customer> {
	private TextField name = new TextField("Namn");
	private TextField phone = new TextField("Telefon");
	private TextField email = new TextField("E-post");
	private TextArea notes = new TextArea("Anteckningar");
	private AddressField invoiceAddress = new AddressField();
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
		Panel deliveryPanel = new Panel("Faktureringsadress", new VerticalLayout(invoiceAddress));
		Panel visitationPanel = new Panel("Besöksadress", new VerticalLayout(visitationAddress));

		VerticalLayout root = new VerticalLayout(createGeneralPanel(),
		    new HorizontalLayout(visitationPanel, deliveryPanel));
		root.setMargin(false);
		return root;
	}

	private Panel createGeneralPanel() {
		VerticalLayout col1 = new VerticalLayout(name, phone, email);
		notes.setHeight("190px");
		VerticalLayout col2 = new VerticalLayout(notes);
		col1.setMargin(false);
		col2.setMargin(false);
		HorizontalLayout content = new HorizontalLayout(col1, col2);
		return new Panel(new VerticalLayout(content));
	}

	@Override
	protected void doSetValue(Customer value) {
		binder.setBean(value);
	}

}
