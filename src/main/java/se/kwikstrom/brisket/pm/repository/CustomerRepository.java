package se.kwikstrom.brisket.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kwikstrom.brisket.pm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
