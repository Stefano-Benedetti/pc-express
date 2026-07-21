package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long user_id);

    boolean existsByEmail(String email);
}
