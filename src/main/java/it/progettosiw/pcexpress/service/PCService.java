package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PCService {

    private PCRepository pcRepository;

    public PCService(PCRepository pcRepository){
        this.pcRepository = pcRepository;
    }

    public PC getPCById(Long id) {
        if (pcRepository.findById(id).isPresent())
            return pcRepository.findById(id).get();
        return null;
    }

    public List<PC> getAllPCs () {
        return (List<PC>) pcRepository.findAll();
    }


}
