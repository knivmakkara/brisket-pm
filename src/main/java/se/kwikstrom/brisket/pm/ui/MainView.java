package se.kwikstrom.brisket.pm.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.pm.service.LoginService;

@SpringView(name = MainView.VIEW_NAME)
public class MainView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "";

	@Autowired
	private LoginService loginService;

	@PostConstruct
	public void init() {
		addComponent(new Label("Main view"));
		addComponent(new Button("Login", (e) -> getUI().getNavigator().navigateTo(LoginView.VIEW_NAME)));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (loginService.isLoggedIn() == false) {
			getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
		}
		View.super.enter(event);
	}

}
