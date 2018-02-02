package se.kwikstrom.brisket.crm.ui;

import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class CrudHeader extends HorizontalLayout {
	private TextField filter;
	private Button newcustomer;
	private Button editcustomer;
	private Button deletecustomer;
	private ValueChangeListener<String> filterchange;

	public CrudHeader(ClickListener create, ClickListener update, ClickListener delete,
	    ValueChangeListener<String> filterchange) {
		filter = new TextField();
		filter.addValueChangeListener(filterchange);

		newcustomer = new Button("Ny");
		newcustomer.setIcon(VaadinIcons.PLUS);
		newcustomer.addClickListener(create);

		editcustomer = new Button("Ändra");
		editcustomer.setIcon(VaadinIcons.EDIT);
		editcustomer.addClickListener(update);

		deletecustomer = new Button("Ta bort");
		deletecustomer.addStyleName(ValoTheme.BUTTON_DANGER);
		deletecustomer.setIcon(VaadinIcons.TRASH);
		deletecustomer.addClickListener(delete);

		HorizontalLayout buttons = new HorizontalLayout(newcustomer, editcustomer, deletecustomer);

		addComponents(filter, buttons);
		setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
		setComponentAlignment(buttons, Alignment.MIDDLE_RIGHT);
	}

	public String getFilter() {
		return filter.getValue();
	}

}
