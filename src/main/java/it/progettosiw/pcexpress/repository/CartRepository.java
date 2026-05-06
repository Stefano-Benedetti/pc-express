package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {
}
