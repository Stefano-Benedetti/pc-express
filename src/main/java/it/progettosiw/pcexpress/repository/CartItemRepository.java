package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.plaf.OptionPaneUI;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {

    @Query(value = "SELECT * FROM cart_item c WHERE c.cart_id=:cart_id AND c.pc_id=:pc_id", nativeQuery = true)
    public Optional<CartItem> findCartItemByCartIdAndPcId(@Param("cart_id") Long cart_id, @Param("pc_id") Long pc_id);

//    @Query(value = "SELECT * FROM cart_item c WHERE c.pc_id=:pc_id", nativeQuery = true)
//    public Optional<CartItem> findCartItemByPcId(@Param("pc_id") Long pc_id);

    public boolean existsByPcId(Long pc_id);
}
