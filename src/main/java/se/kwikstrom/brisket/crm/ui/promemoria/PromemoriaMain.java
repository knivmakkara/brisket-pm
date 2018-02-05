package se.kwikstrom.brisket.crm.ui.promemoria;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.crm.domain.Promemoria;
import se.kwikstrom.brisket.crm.repository.CustomerRepository;
import se.kwikstrom.brisket.crm.repository.PromemoriaRepository;
import se.kwikstrom.brisket.crm.ui.CrudHeader;

@UIScope
@SpringComponent
public class PromemoriaMain extends VerticalLayout {
	private CrudHeader crudHeader;
	private Grid<Promemoria> promemoriaGrid = new Grid<Promemoria>();
	private PromemoriaRepository promemoriaRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public PromemoriaMain(PromemoriaRepository promemoriaRepository, CustomerRepository customerRepository) {
		this.promemoriaRepository = promemoriaRepository;
		this.customerRepository = customerRepository;
		init();
	}

	private void init() {
		promemoriaGrid.setWidth("100%");
		promemoriaGrid.setHeight("600px");
		promemoriaGrid.addColumn(Promemoria::getCustomerName).setCaption("Kund");
		promemoriaGrid.addColumn(Promemoria::getCustomerPhone).setCaption("Telefon");
		promemoriaGrid.addColumn(Promemoria::getCustomerEmail).setCaption("E-post");
		promemoriaGrid.addColumn(Promemoria::getNumberGuests).setCaption("Antal");
		promemoriaGrid.addColumn((p) -> p.getDue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
		    .setCaption("Datum/Tid");
		crudHeader = createCrudButtons();
		addComponent(crudHeader);
		addComponent(promemoriaGrid);
		updateGrid();
	}

	private void updateGrid() {

		List<Promemoria> result = null;
		String filterStr = crudHeader.getFilter();
		if (filterStr != null && filterStr.isEmpty() == false) {
			result = promemoriaRepository.findAllByCustomerNameLike("%" + filterStr + "%");
		} else {
			result = promemoriaRepository.findAll();
		}
		promemoriaGrid.setItems(result);
	}

	private CrudHeader createCrudButtons() {
		CrudHeader crudButtons = new CrudHeader((e) -> newPmClick(e), (e) -> editPmClick(e), (e) -> deletePmClick(e),
		    (e) -> filterChange(e));
		crudButtons.setWidth("100%");
		return crudButtons;
	}

	private void editPmClick(ClickEvent e) {
		showPmWindow(new Promemoria(promemoriaGrid.asSingleSelect().getValue()));
	}

	private void showPmWindow(Promemoria selected) {
		if (selected != null) {
			PromemoriaWindow wnd = new PromemoriaWindow(selected, promemoriaRepository, customerRepository);
			UI.getCurrent().addWindow(wnd);
		}
	}

	private void newPmClick(ClickEvent e) {
		showPmWindow(new Promemoria());
	}

	private void filterChange(ValueChangeEvent<String> e) {
		updateGrid();
	}

	private void deletePmClick(ClickEvent e) {
		Promemoria selected = promemoriaGrid.asSingleSelect().getValue();
		if (selected != null) {
			promemoriaRepository.delete(selected);
			updateGrid();
		}
	}

}
