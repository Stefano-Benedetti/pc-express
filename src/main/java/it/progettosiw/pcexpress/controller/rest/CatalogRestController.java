package it.progettosiw.pcexpress.controller.rest;

import it.progettosiw.pcexpress.dto.ReactPCDto;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.service.PCService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogRestController {

    private final PCService pcService;

    public CatalogRestController(PCService pcService) {
        this.pcService = pcService;
    }

    @GetMapping
    public List<ReactPCDto> getCatalog() {

        List<ReactPCDto> risposta = new ArrayList<ReactPCDto>();

        for(PC pc : this.pcService.getAllPCs()){
            risposta.add(new ReactPCDto(pc.getId(), pc.getNome(), pc.getPrezzo()));
        }

        return risposta;
    }
}
