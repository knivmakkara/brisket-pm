package se.kwikstrom.brisket.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kwikstrom.brisket.crm.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByName(String username);
}
