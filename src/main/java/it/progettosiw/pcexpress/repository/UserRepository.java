package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Long user_id);
}
