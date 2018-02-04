package se.kwikstrom.brisket.crm.repository;

import java.util.List;

import se.kwikstrom.brisket.crm.domain.Customer;

public interface CustomerRepositoryCustom {
	List<Customer> findAllByNameLike(String name, int offset, int limit);

	List<Customer> findAll(int offset, int limit);
}
