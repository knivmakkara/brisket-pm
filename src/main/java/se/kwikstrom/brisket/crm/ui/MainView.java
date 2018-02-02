package se.kwikstrom.brisket.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

import se.kwikstrom.brisket.crm.service.LoginService;
import se.kwikstrom.brisket.crm.ui.customer.CustomerMain;

@SpringView(name = MainView.VIEW_NAME)
public class MainView extends VerticalLayout implements SecureView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "";

	@Autowired
	private LoginService loginService;

	@Autowired
	private CustomerMain customerMain;

	@PostConstruct
	public void init() {
		TabSheet tabSheet = new TabSheet();
		tabSheet.addTab(customerMain, "Kunder");
		addComponent(tabSheet);
	}

	@Override
	public LoginService getLoginService() {
		return loginService;
	}

}
