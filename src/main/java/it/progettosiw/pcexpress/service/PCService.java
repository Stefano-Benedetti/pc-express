package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.exceptions.PCWithThisCodeAlreadyExistsException;
import it.progettosiw.pcexpress.exceptions.PCNotFoundException;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.repository.CartItemRepository;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PCService {

    private final CartItemRepository cartItemRepository;
    private PCRepository pcRepository;

    public PCService(PCRepository pcRepository, CartItemRepository cartItemRepository){
        this.pcRepository = pcRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional(readOnly = true)
    public PC getPCById(Long id) {
        if (pcRepository.findById(id).isPresent())
            return pcRepository.findById(id).get();
        return null;
    }

    @Transactional(readOnly = true)
    public List<PC> getAllPCs () {
        return (List<PC>) pcRepository.findAll();
    }

    @Transactional
    public void save(PC pc) throws PCWithThisCodeAlreadyExistsException {
        if(pcRepository.existsByCodice(pc.getCodice()))
            throw new PCWithThisCodeAlreadyExistsException(pc.getCodice());
        else
            pcRepository.save(pc);
    }

    @Transactional
    public void update(Long pcId, String nome, Float prezzo, Integer disponibilita) throws PCNotFoundException {
        Optional<PC> optPC = pcRepository.findById(pcId);
        if(optPC.isPresent()){
            PC pc = optPC.get();
            pc.setNome(nome);
            pc.setPrezzo(prezzo);
            pc.setDisponibilita(disponibilita);
            pcRepository.save(pc);
        }
        else
            throw new PCNotFoundException(pcId);
    }

    @Transactional
    public Long cloneWithChanges(PC pc, Boolean toZero) throws PCWithThisCodeAlreadyExistsException {
        if(pcRepository.existsByCodice(pc.getCodice())){
            throw new PCWithThisCodeAlreadyExistsException(pc.getCodice());
        }
        else {
            PC newpc = new PC(pc);
            pcRepository.save(newpc);
            if(toZero){
                toZeroAvailability(pc);
            }
            return newpc.getId();
        }
    }

    @Transactional
    public void toZeroAvailability(PC pc) throws PCNotFoundException {
        Optional<PC> optPC = pcRepository.findById(pc.getId());
        if(optPC.isPresent()){
            PC pcToZero = optPC.get();
            pcToZero.setDisponibilita(0);
            pcRepository.save(pcToZero);
        }
        else
            throw new PCNotFoundException(pc.getId());
    }

}
