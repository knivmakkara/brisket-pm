package se.kwikstrom.brisket.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import se.kwikstrom.brisket.crm.domain.Promemoria;

public interface PromemoriaRepository extends JpaRepository<Promemoria, Integer> {

	@Query("SELECT a FROM Promemoria a INNER JOIN a.customer WHERE upper(a.customer.name) LIKE upper(concat('%',?1,'%'))")
	List<Promemoria> findAllByCustomerNameLike(String string);
}
