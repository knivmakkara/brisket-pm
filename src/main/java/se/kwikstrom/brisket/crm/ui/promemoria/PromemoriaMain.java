package se.kwikstrom.brisket.crm.ui.promemoria;

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
import se.kwikstrom.brisket.crm.repository.PromemoriaRepository;
import se.kwikstrom.brisket.crm.ui.CrudHeader;

@UIScope
@SpringComponent
public class PromemoriaMain extends VerticalLayout {
	private CrudHeader crudHeader;
	private Grid<Promemoria> promemoriaGrid = new Grid<Promemoria>();
	private PromemoriaRepository promemoriaRepository;

	@Autowired
	public PromemoriaMain(PromemoriaRepository promemoriaRepository) {
		this.promemoriaRepository = promemoriaRepository;
		init();
	}

	private void init() {
		promemoriaGrid.setWidth("100%");
		promemoriaGrid.setHeight("600px");
		promemoriaGrid.addColumn(Promemoria::getCustomerName).setCaption("Kund");
		promemoriaGrid.addColumn(Promemoria::getCustomerPhone).setCaption("Telefon");
		promemoriaGrid.addColumn(Promemoria::getCustomerEmail).setCaption("E-post");
		promemoriaGrid.addColumn(Promemoria::getDue).setCaption("Datum/Tid");
		crudHeader = createCrudButtons();
		addComponent(crudHeader);
		addComponent(promemoriaGrid);
		updateGrid();
	}

	private void updateGrid() {

		List<Promemoria> result = null;
		String filterStr = crudHeader.getFilter();
		if (filterStr != null && filterStr.isEmpty() == false) {
			result = promemoriaRepository.findAllByCustomerNameLikeIgnoreCase("%" + filterStr + "%");
		} else {
			result = promemoriaRepository.findAll();
		}
		promemoriaGrid.setItems(result);
	}

	private CrudHeader createCrudButtons() {
		CrudHeader crudButtons = new CrudHeader((e) -> newCustomerClick(e), (e) -> editCustomerClick(e),
		    (e) -> deleteCustomerClick(e), (e) -> filterChange(e));
		crudButtons.setWidth("100%");
		return crudButtons;
	}

	private void editCustomerClick(ClickEvent e) {
		Promemoria selected = promemoriaGrid.asSingleSelect().getValue();
		if (selected != null) {
			PromemoriaWindow wnd = new PromemoriaWindow(new Promemoria(selected), promemoriaRepository);
			UI.getCurrent().addWindow(wnd);
		}
	}

	private void newCustomerClick(ClickEvent e) {
		// TODO Auto-generated method stub
	}

	private void filterChange(ValueChangeEvent<String> e) {
		updateGrid();
	}

	private void deleteCustomerClick(ClickEvent e) {
		// TODO Auto-generated method stub
	}

}
