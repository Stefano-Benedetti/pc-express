package it.progettosiw.pcexpress.repository;

import it.progettosiw.pcexpress.model.PC;
import org.springframework.data.repository.CrudRepository;

public interface PCRepository extends CrudRepository<PC,Long> {

    public boolean existsByCodice(Long codice);

}
