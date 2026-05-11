package it.progettosiw.pcexpress.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PC {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long codice;

    private String nome;

    private String cpu;

    private String gpu;

    private String ram;

    private String schedaMadre;

    private String rom;

    private String casePc;

    private String alimentatore;

    private String sistemaOperativo;

    private String dissipatore;

    private String ventole;

    @Column(nullable = false)
    private Float prezzo;

    @Column(nullable = false)
    private Integer disponibilita;

    public PC(){

    }

    public PC(Long codice, String nome, String cpu, String gpu, String ram, String schedaMadre, String rom, String casePc, String alimentatore, String sistemaOperativo, String dissipatore, String ventole, Float prezzo, Integer disponibilita) {
        this.codice = codice;
        this.nome = nome;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.schedaMadre = schedaMadre;
        this.rom = rom;
        this.casePc = casePc;
        this.alimentatore = alimentatore;
        this.sistemaOperativo = sistemaOperativo;
        this.dissipatore = dissipatore;
        this.ventole = ventole;
        this.prezzo = prezzo;
        this.disponibilita = disponibilita;
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

    public String getSchedaMadre() {
        return schedaMadre;
    }

    public void setSchedaMadre(String schedaMadre) {
        this.schedaMadre = schedaMadre;
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

    public String getAlimentatore() {
        return alimentatore;
    }

    public void setAlimentatore(String alimentatore) {
        this.alimentatore = alimentatore;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getDissipatore() {
        return dissipatore;
    }

    public void setDissipatore(String dissipatore) {
        this.dissipatore = dissipatore;
    }

    public String getVentole() {
        return ventole;
    }

    public void setVentole(String ventole) {
        this.ventole = ventole;
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
