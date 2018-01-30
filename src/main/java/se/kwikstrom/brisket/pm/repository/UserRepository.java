package se.kwikstrom.brisket.pm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.kwikstrom.brisket.pm.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByName(String username);
}
