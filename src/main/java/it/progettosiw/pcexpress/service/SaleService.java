package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.repository.SaleRepository;
import it.progettosiw.pcexpress.repository.SoldItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    private SaleRepository saleRepository;
    private SoldItemRepository soldItemRepository;

    public SaleService(SaleRepository saleRepository, SoldItemRepository soldItemRepository) {
        this.saleRepository = saleRepository;
        this.soldItemRepository = soldItemRepository;
    }


}
