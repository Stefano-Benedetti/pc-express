package it.progettosiw.pcexpress.model;

import it.progettosiw.pcexpress.service.CartService;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Entity
public class PC {

    @Transient //per escluderlo dal mapping JPA
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long codice;

    private String nome;

    private String cpu;

    private String gpu;

    private String ram;

    private String rom;

    private String casePc;

    @Column(nullable = false)
    private Float prezzo;

    @Column(nullable = false)
    private Integer disponibilita;

    public PC(){

    }

    public PC(Long codice, String nome, String cpu, String gpu, String ram, String rom, String casePc, Float prezzo, Integer disponibilita) {
        this.codice = codice;
        this.nome = nome;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.rom = rom;
        this.casePc = casePc;
        this.prezzo = prezzo;
        this.disponibilita = disponibilita;
    }

    public void reduceAvailability(Integer quantity){
        if(this.disponibilita<quantity)
            logger.error("Quantità maggiore della disponibilità nel pc: "+ getNome()+", "+getId());
        this.disponibilita -= quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodice() {
        return codice;
    }

    public void setCodice(Long codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getCasePc() {
        return casePc;
    }

    public void setCasePc(String casePC) {
        this.casePc = casePC;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Integer disponibilita) {
        this.disponibilita = disponibilita;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PC pc)) return false;
        return Objects.equals(id, pc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
