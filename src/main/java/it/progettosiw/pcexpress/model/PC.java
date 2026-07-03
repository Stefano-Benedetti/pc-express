package it.progettosiw.pcexpress.model;

import it.progettosiw.pcexpress.service.CartService;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

@Entity
public class PC {

    @Transient //per escluderlo dal mapping JPA
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private Long codice;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    private String cpu;

    @NotBlank
    private String gpu;

    @NotBlank
    private String ram;

    @NotBlank
    private String rom;

    @NotBlank
    private String casePc;

    @NotNull
    @DecimalMin(value="0.00", message="Il prezzo deve essere almeno 0.00")
    @Column(nullable = false)
    private Float prezzo;

    @NotNull
    @Min(value=0, message="La disponibilità deve essere almeno 0")
    @Column(nullable = false)
    private Integer disponibilita;

    //@Lob    //sta per large object
    @Column(columnDefinition="bytea")
    private byte[] immagine;

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

    public PC(PC pc) {
        this.codice = pc.getCodice();
        this.nome = pc.getNome();
        this.cpu = pc.getCpu();
        this.gpu = pc.getGpu();
        this.ram = pc.getRam();
        this.rom = pc.getRom();
        this.casePc = pc.getCasePc();
        this.prezzo = pc.getPrezzo();
        this.disponibilita = pc.getDisponibilita();
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

    public byte[] getImmagine() {
        return immagine;
    }

    public void setImmagine(byte[] immagine) {
        this.immagine = immagine;
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
