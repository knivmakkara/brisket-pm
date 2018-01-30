package se.kwikstrom.brisket.pm.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class ProtectedService {
	@Secured("ROLE_USER")
	public void someProtectedMethod() {
		System.out.println("Hello from protected");
	}

	public void someUnprotectedMethod() {
		System.out.println("Hello from unprotected");
	}
}
