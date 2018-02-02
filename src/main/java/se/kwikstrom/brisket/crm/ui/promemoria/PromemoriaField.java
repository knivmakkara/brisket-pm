package se.kwikstrom.brisket.crm.ui.promemoria;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.crm.domain.Promemoria;
import se.kwikstrom.brisket.crm.ui.AddressField;

public class PromemoriaField extends CustomField<Promemoria> {

	private DateTimeField due = new DateTimeField("");
	private TextField customerName = new TextField("");
	private TextField customerPhone = new TextField("");
	private TextField customerEmail = new TextField("");
	private TextField menu = new TextField("");
	private TextField allergies = new TextField("");
	private TextField receivedBy = new TextField("");
	private TextField deliveryType = new TextField("");
	private TextField staff = new TextField("");
	private TextField rental = new TextField("");
	private TextField misc = new TextField("");
	private AddressField invoiceTo = new AddressField();
	private AddressField deliverTo = new AddressField();

	private Binder<Promemoria> binder = new BeanValidationBinder<>(Promemoria.class);

	@Override
	public Promemoria getValue() {
		return binder.getBean();
	}

	@Override
	protected Component initContent() {
		binder.bindInstanceFields(this);

		return new VerticalLayout(due, customerName, customerPhone, customerEmail, menu, allergies, receivedBy);
	}

	@Override
	protected void doSetValue(Promemoria value) {
		binder.setBean(value);
	}

}
