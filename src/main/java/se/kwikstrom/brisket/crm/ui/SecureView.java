package se.kwikstrom.brisket.crm.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.UI;

import se.kwikstrom.brisket.crm.service.LoginService;

public interface SecureView extends View {

	default void enter(ViewChangeEvent event) {

		if (getLoginService().isLoggedIn() == false) {
			getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
		}
		// View.super.enter(event);
	}

	LoginService getLoginService();

	UI getUI();

}
