package it.progettosiw.pcexpress.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModifyPCForm {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin(value="0.00", message="Il prezzo deve essere almeno 0.00")
    private Float prezzo;

    @NotNull
    @Min(value=0, message="La disponibilità deve essere almeno 0")
    private Integer disponibilita;

    public ModifyPCForm() {
    }

    public ModifyPCForm(Long id, String nome, Float prezzo, Integer disponibilita) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.disponibilita = disponibilita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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


}
