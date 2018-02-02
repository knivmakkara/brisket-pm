package se.kwikstrom.brisket.crm.service;

public interface LoginService {
	void login(String username, String password) throws LoginException;

	boolean isLoggedIn();
}
