package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
