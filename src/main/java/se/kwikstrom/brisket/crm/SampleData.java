package se.kwikstrom.brisket.crm;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kwikstrom.brisket.crm.domain.Customer;
import se.kwikstrom.brisket.crm.domain.Customer.Address;
import se.kwikstrom.brisket.crm.domain.User;
import se.kwikstrom.brisket.crm.repository.CustomerRepository;
import se.kwikstrom.brisket.crm.repository.UserRepository;

@Component
public class SampleData {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepo;

	@PostConstruct
	private void init() {
		User u1 = new User();
		u1.setName("stoffe");
		u1.setPassword("pass");

		userRepository.save(u1);

		Customer c = new Customer();
		c.setName("test");
		c.setDeliveryAddress(new Address("delivery address1", "delivery address 2", "upplands väsby", "19442"));

		customerRepo.save(c);

		Customer c1 = new Customer();
		c1.setName("test2");

		customerRepo.save(c1);
	}
}
