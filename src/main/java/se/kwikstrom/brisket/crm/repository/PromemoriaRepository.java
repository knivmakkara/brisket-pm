package se.kwikstrom.brisket.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kwikstrom.brisket.crm.domain.Promemoria;

public interface PromemoriaRepository extends JpaRepository<Promemoria, Integer> {

	List<Promemoria> findAllByCustomerNameLikeIgnoreCase(String string);
}
