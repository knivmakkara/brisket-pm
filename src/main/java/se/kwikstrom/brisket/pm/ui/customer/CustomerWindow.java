package se.kwikstrom.brisket.pm.ui.customer;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import se.kwikstrom.brisket.pm.domain.Customer;
import se.kwikstrom.brisket.pm.repository.CustomerRepository;

public class CustomerWindow extends Window {

	private CustomerField customerEditor = new CustomerField();
	private CustomerRepository customerRepository;
	private Runnable customerSavedListener;

	public CustomerWindow(Customer c, CustomerRepository customerRepository) {
		super("Kund");
		this.customerRepository = customerRepository;
		init(c);
	}

	private void init(Customer c) {
		this.customerEditor.setValue(c);
		this.center();
		this.setModal(true);

		Button save = new Button("Spara", (e) -> {
			customerRepository.save(customerEditor.getValue());
			customerSavedListener.customerSaved();
			this.close();
		});
		save.addStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		this.setContent(new VerticalLayout(customerEditor, new HorizontalLayout(save)));
	}

	public Runnable getCustomerSavedListener() {
		return customerSavedListener;
	}

	public void setCustomerSavedListener(Runnable customerSavedListener) {
		this.customerSavedListener = customerSavedListener;
	}

	public static interface Runnable {
		void customerSaved();
	}
}
