package it.progettosiw.pcexpress.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class SoldItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Float paidMoney;
    
    @ManyToOne //quando un pc è venduto va aggiornata la disponibilità
    private PC pc;

    public SoldItem() {
    }

    public SoldItem(Integer quantity, Float paidMoney, PC pc) {
        this.quantity = quantity;
        this.paidMoney = paidMoney;
        this.pc = pc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(Float paidMoney) {
        this.paidMoney = paidMoney;
    }

    public PC getPc() {
        return pc;
    }

    public void setPc(PC pc) {
        this.pc = pc;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoldItem soldItem = (SoldItem) o;
        return Objects.equals(id, soldItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
