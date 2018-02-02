package se.kwikstrom.brisket.crm;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kwikstrom.brisket.crm.domain.Address;
import se.kwikstrom.brisket.crm.domain.Customer;
import se.kwikstrom.brisket.crm.domain.Promemoria;
import se.kwikstrom.brisket.crm.domain.User;
import se.kwikstrom.brisket.crm.repository.CustomerRepository;
import se.kwikstrom.brisket.crm.repository.PromemoriaRepository;
import se.kwikstrom.brisket.crm.repository.UserRepository;

@Component
public class SampleData {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PromemoriaRepository promemoriaRepo;

	@PostConstruct
	private void init() {
		User u1 = new User();
		u1.setName("stoffe");
		u1.setPassword("pass");

		userRepository.save(u1);

		createCustomers();
		createPMs();
	}

	private void createPMs() {
		for (int i = 0; i < 1000; i++) {
			Promemoria c = new Promemoria();
			c.setCustomerName("Kund " + i);
			c.setCustomerEmail("E-post" + i);
			c.setCustomerPhone("2342423" + i);
			c.setDeliverTo(new Address("address1" + i, "address2" + i, "zip" + i, "postal" + i));
			c.setInvoiceTo(new Address("address1" + i, "address2" + i, "zip" + i, "postal" + i));
			c.setDue(LocalDateTime.now());

			promemoriaRepo.save(c);
		}

	}

	private void createCustomers() {
		for (int i = 0; i < 1000; i++) {
			Customer c = new Customer();
			c.setName("Kund " + i);
			c.setEmail("E-post" + i);
			c.setPhone("2342423" + i);
			c.setNotes("Anteckningar " + i);
			c.setDeliveryAddress(new Address("address1" + i, "address2" + i, "zip" + i, "postal" + i));
			c.setVisitationAddress(new Address("address1" + i, "address2" + i, "zip" + i, "postal" + i));

			customerRepo.save(c);
		}
	}
}
