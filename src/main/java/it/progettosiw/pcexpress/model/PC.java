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

    private String casePC;

    private String alimentatore;

    private String sistemaOperativo;

    private String dissipatore;

    private String ventole;

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

    public String getCasePC() {
        return casePC;
    }

    public void setCasePC(String casePC) {
        this.casePC = casePC;
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
