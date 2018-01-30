package se.kwikstrom.brisket.pm;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kwikstrom.brisket.pm.domain.User;
import se.kwikstrom.brisket.pm.repository.UserRepository;

@Component
public class SampleData {
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	private void init() {
		User u1 = new User();
		u1.setName("stoffe");
		u1.setPassword("pass");

		userRepository.save(u1);
	}
}
