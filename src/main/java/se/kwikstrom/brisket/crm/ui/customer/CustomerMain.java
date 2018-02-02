package se.kwikstrom.brisket.crm.ui.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.crm.domain.Customer;
import se.kwikstrom.brisket.crm.repository.CustomerRepository;
import se.kwikstrom.brisket.crm.ui.CrudHeader;

@SpringComponent
@UIScope
public class CustomerMain extends VerticalLayout {

	private CrudHeader crudHeader;
	private Grid<Customer> customerGrid = new Grid<Customer>();
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerMain(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		init();
	}

	public void init() {

		customerGrid.setWidth("100%");
		customerGrid.setHeight("600px");
		customerGrid.addColumn(Customer::getName).setCaption("Kund");
		customerGrid.addColumn(Customer::getPhone).setCaption("Telefon");
		customerGrid.addColumn(Customer::getEmail).setCaption("E-post");
		crudHeader = createCrudButtons();
		addComponent(crudHeader);
		addComponent(customerGrid);
		updateGrid();
	}

	private CrudHeader createCrudButtons() {
		CrudHeader crudButtons = new CrudHeader((e) -> newCustomerClick(e), (e) -> editCustomerClick(e),
		    (e) -> deleteCustomerClick(e), (e) -> filterChange(e));
		crudButtons.setWidth("100%");
		return crudButtons;
	}

	private void filterChange(ValueChangeEvent<String> e) {
		updateGrid();
	}

	private void deleteCustomerClick(ClickEvent e) {
		Customer selected = customerGrid.asSingleSelect().getValue();
		if (selected != null) {
			customerRepository.delete(selected);
			updateGrid();
		}
	}

	private void editCustomerClick(ClickEvent e) {
		Customer selected = customerGrid.asSingleSelect().getValue();
		if (selected != null) {
			CustomerWindow wnd = new CustomerWindow(new Customer(selected), customerRepository);
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
		List<Customer> result = null;
		String filterStr = crudHeader.getFilter();
		if (filterStr != null && filterStr.isEmpty() == false) {
			result = customerRepository.findAllByNameLikeIgnoreCase("%" + filterStr + "%");
		} else {
			result = customerRepository.findAll();
		}
		customerGrid.setItems(result);
	}
}
