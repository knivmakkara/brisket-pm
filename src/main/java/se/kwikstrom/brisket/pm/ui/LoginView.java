package se.kwikstrom.brisket.pm.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import se.kwikstrom.brisket.pm.service.LoginException;
import se.kwikstrom.brisket.pm.service.LoginService;
import se.kwikstrom.brisket.pm.service.ProtectedService;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends FormLayout implements View {
	public static final String VIEW_NAME = "view";

	@Autowired
	private LoginService loginService;

	@Autowired
	private ProtectedService protectedService;

	@PostConstruct
	void init() {
		TextField userNameTf = new TextField("Användarnamn");
		addComponent(userNameTf);
		TextField passwordTf = new TextField("Lösenord");
		addComponent(passwordTf);
		addComponent(new Button("Logga in", (e) -> {
			try {
				loginService.login(userNameTf.getValue(), passwordTf.getValue());
			} catch (LoginException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}));
		addComponent(new Button("Test protected", (e) -> protectedService.someProtectedMethod()));
		addComponent(new Button("Test unprotected", (e) -> protectedService.someUnprotectedMethod()));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// This view is constructed in the init() method()
	}
}
