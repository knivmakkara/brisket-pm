package se.kwikstrom.brisket.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import se.kwikstrom.brisket.crm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, CustomerRepositoryCustom {
	List<Customer> findAllByNameLikeIgnoreCase(String name);

	Page<Customer> findAllByNameLikeIgnoreCase(String name, Pageable pageable);

	@Query("SELECT count(c) FROM Customer c WHERE upper(c.name) LIKE upper(concat('%',?1,'%'))")
	int countByNameLikeIgnoreCase(String name);
}
