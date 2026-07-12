package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.exceptions.PCDoesNotExistException;
import it.progettosiw.pcexpress.exceptions.PCWithThisCodeAlreadyExistsException;
import it.progettosiw.pcexpress.exceptions.PCNotFoundException;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.repository.CartItemRepository;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return pcRepository.findById(id)
                .orElseThrow(() -> new PCNotFoundException(id));
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
    public void update(Long pcId, String nome, Float prezzo, Integer disponibilita, MultipartFile image) throws PCNotFoundException, IOException {
        PC pc = pcRepository.findById(pcId).orElseThrow(() -> new PCDoesNotExistException(pcId));
        pc.setNome(nome);
        pc.setPrezzo(prezzo);
        pc.setDisponibilita(disponibilita);
        if (image != null && !image.isEmpty() && image.getContentType().startsWith("image/"))
            pc.setImmagine(image.getBytes());
        pcRepository.save(pc);
    }

    @Transactional
    public Long cloneWithChanges(PC pc, Boolean toZero, MultipartFile image) throws PCWithThisCodeAlreadyExistsException, IOException {
        PC newpc = new PC(pc);
        if (image != null && !image.isEmpty() && image.getContentType().startsWith("image/"))
            newpc.setImmagine(image.getBytes());
        else{
            PC oldPc = pcRepository.findById(pc.getId()).orElseThrow(() -> new PCDoesNotExistException(pc.getId()));
            newpc.setImmagine(oldPc.getImmagine());
        }
        this.save(newpc);
        if(toZero)
            toZeroAvailability(pc);
        return newpc.getId();
    }

    @Transactional
    public void toZeroAvailability(PC pc) throws PCNotFoundException {
        PC pcToZero = pcRepository.findById(pc.getId()).orElseThrow(() -> new PCDoesNotExistException(pc.getId()));
        pcToZero.setDisponibilita(0);
        pcRepository.save(pcToZero);
    }

}
