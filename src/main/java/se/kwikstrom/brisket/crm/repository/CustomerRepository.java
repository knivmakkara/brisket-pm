package se.kwikstrom.brisket.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kwikstrom.brisket.crm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
