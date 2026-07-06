package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.Sale;
import it.progettosiw.pcexpress.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    //restituisce tutte le sale in ordine decrescente di data
    List<Sale> findAllByOrderByDateOfSaleDesc();

    List<Sale> findAllByUserIdOrderByDateOfSaleDesc(Long userId);

}
