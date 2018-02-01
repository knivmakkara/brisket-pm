package se.kwikstrom.brisket.pm.ui.customer;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import se.kwikstrom.brisket.pm.domain.Customer;
import se.kwikstrom.brisket.pm.repository.CustomerRepository;

@SpringComponent
@UIScope
public class CustomerMain extends VerticalLayout {

	private Grid<Customer> customerGrid = new Grid<Customer>();
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerMain(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		init();
	}

	public void init() {

		customerGrid.setSizeFull();
		customerGrid.addColumn(Customer::getName).setCaption("Kund");
		customerGrid.addColumn(Customer::getPhone).setCaption("Telefon");
		customerGrid.addColumn(Customer::getEmail).setCaption("E-post");
		addComponent(createEditButtons());
		addComponent(customerGrid);
		updateGrid();
	}

	private Component createEditButtons() {
		Button newcustomer = new Button("Ny kund", (e) -> newCustomerClick(e));
		newcustomer.setIcon(VaadinIcons.PLUS);
		Button editcustomer = new Button("Ändra", (e) -> editCustomerClick(e));
		editcustomer.setIcon(VaadinIcons.EDIT);
		Button deletecustomer = new Button("Ta bort", (e) -> deleteCustomerClick(e));
		deletecustomer.addStyleName(ValoTheme.BUTTON_DANGER);
		deletecustomer.setIcon(VaadinIcons.TRASH);
		return new HorizontalLayout(newcustomer, editcustomer, deletecustomer);
	}

	private void deleteCustomerClick(ClickEvent e) {

	}

	private void editCustomerClick(ClickEvent e) {
		Customer selected = customerGrid.asSingleSelect().getValue();
		if (selected != null) {
			CustomerWindow wnd = new CustomerWindow(selected, customerRepository);
			wnd.setCustomerSavedListener(() -> updateGrid());
			UI.getCurrent().addWindow(wnd);
		}
	}

	private void newCustomerClick(ClickEvent e) {
		CustomerWindow wnd = new CustomerWindow(new Customer(), customerRepository);
		wnd.setCustomerSavedListener(() -> updateGrid());
		UI.getCurrent().addWindow(wnd);
	}

	private void updateGrid() {
		customerGrid.setItems(customerRepository.findAll());
	}
}
