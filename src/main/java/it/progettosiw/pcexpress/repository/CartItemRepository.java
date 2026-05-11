package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {
}
