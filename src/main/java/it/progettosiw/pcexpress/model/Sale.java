package it.progettosiw.pcexpress.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateOfSale;

    private Float paidMoney;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="sale_id")
    private List<SoldItem> soldItems;

    @ManyToOne
    private User user;

    public Sale() {
    }

    public Sale(LocalDateTime dateOfSale, Float paidMoney, List<SoldItem> soldItems, User user) {
        this.dateOfSale = dateOfSale;
        this.paidMoney = paidMoney;
        this.soldItems = soldItems;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(LocalDateTime dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public Float getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(Float paidMoney) {
        this.paidMoney = paidMoney;
    }

    public List<SoldItem> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(List<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
