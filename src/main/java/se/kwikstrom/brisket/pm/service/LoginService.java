package se.kwikstrom.brisket.pm.service;

public interface LoginService {
	void login(String username, String password) throws LoginException;

	boolean isLoggedIn();
}
