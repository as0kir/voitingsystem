package ru.askir.voitingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(name = "menu_id_restaurant_date_set", columnNames = {"id_restaurant", "date_set"})})
public class Menu extends AbstractBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date_set", nullable = false)
    @NotBlank
    private LocalDate dateSet;

    public Menu() {
    }

    public Menu(@NotNull Restaurant restaurant, @NotBlank LocalDate dateSet) {
        this.restaurant = restaurant;
        this.dateSet = dateSet;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDateSet() {
        return dateSet;
    }

    public void setDateSet(LocalDate dateSet) {
        this.dateSet = dateSet;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "restaurant=" + restaurant +
                ", dateSet=" + dateSet +
                '}';
    }
}