package se.kwikstrom.brisket.pm.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.pm.service.LoginException;
import se.kwikstrom.brisket.pm.service.LoginService;
import se.kwikstrom.brisket.pm.service.ProtectedService;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "login";

	@Autowired
	private LoginService loginService;

	@Autowired
	private ProtectedService protectedService;

	@PostConstruct
	void init() {
		TextField userNameTf = new TextField("Användarnamn");
		userNameTf.setWidth(null);
		TextField passwordTf = new TextField("Lösenord");
		Button loginBtn = new Button("Logga in", (e) -> {
			try {
				loginService.login(userNameTf.getValue(), passwordTf.getValue());
				getUI().getNavigator().navigateTo(MainView.VIEW_NAME);
			} catch (LoginException e1) {
			}
		});

		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		addComponent(userNameTf);
		addComponent(passwordTf);
		addComponent(loginBtn);
	}

}
