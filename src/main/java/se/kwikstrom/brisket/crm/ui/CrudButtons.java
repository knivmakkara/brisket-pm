package se.kwikstrom.brisket.crm.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class CrudButtons extends HorizontalLayout {
	private Button newcustomer;
	private Button editcustomer;
	private Button deletecustomer;

	public CrudButtons(ClickListener create, ClickListener update, ClickListener delete) {
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

		addComponents(newcustomer, editcustomer, deletecustomer);
	}

}
