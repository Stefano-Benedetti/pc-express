package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.repository.CartItemRepository;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.springframework.stereotype.Service;

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

    public PC getPCById(Long id) {
        if (pcRepository.findById(id).isPresent())
            return pcRepository.findById(id).get();
        return null;
    }

    public List<PC> getAllPCs () {
        return (List<PC>) pcRepository.findAll();
    }

    public void save(PC pc){
        pcRepository.save(pc);
    }

    public void modifyOrCloneWithChanges(PC pc){
        if(cartItemRepository.existsByPcId(pc.getId()) /* or è stato venduto */){
            PC clonePC = new PC(10001L,pc.getNome(),pc.getCpu(),pc.getGpu(),pc.getRam(),pc.getRom(),pc.getCasePc(),pc.getPrezzo(),pc.getDisponibilita());
            System.out.println("da clonare");
        }
        else{
            pcRepository.save(pc);
        }
    }

    public void update(Long pcId, String nome, Float prezzo, Integer disponibilita){
        Optional<PC> optPC = pcRepository.findById(pcId);
        if(optPC.isPresent()){
            PC pc = optPC.get();
            pc.setNome(nome);
            pc.setPrezzo(prezzo);
            pc.setDisponibilita(disponibilita);
            pcRepository.save(pc);
        }
        // else ERRORE
    }

    public Long cloneWithChanges(PC pc, Boolean toZero){
        if(pcRepository.existsByCodice(pc.getCodice())){
            //ERRORE
            System.out.println("Errore cloneWithChanges");
            return null;
        }
        else {
            PC newpc = new PC(pc.getCodice(), pc.getNome(), pc.getCpu(), pc.getGpu(), pc.getRam(), pc.getRom(), pc.getCasePc(), pc.getPrezzo(), pc.getDisponibilita());
            pcRepository.save(newpc);
            if(toZero){
                toZeroAvailability(pc);
            }
            return newpc.getId();
        }
    }

    public void toZeroAvailability(PC pc){
        Optional<PC> optPC = pcRepository.findById(pc.getId());
        if(optPC.isPresent()){
            PC pcToZero = optPC.get();
            pcToZero.setDisponibilita(0);
            pcRepository.save(pcToZero);
        }
        //else ERRORE
    }

}
