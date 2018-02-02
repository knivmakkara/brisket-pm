package se.kwikstrom.brisket.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kwikstrom.brisket.crm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	List<Customer> findAllByNameLikeIgnoreCase(String name);
}
