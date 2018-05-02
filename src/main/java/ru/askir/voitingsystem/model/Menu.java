package ru.askir.voitingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(name = "menu_id_restaurant_date_set", columnNames = {"id_restaurant", "date_set"})})
public class Menu extends AbstractBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date_set", nullable = false)
    @NotNull
    private LocalDate dateSet;

    @OneToMany(mappedBy = "menu")
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, @NotBlank LocalDate dateSet) {
        super(id);
        this.dateSet = dateSet;
    }

    public Menu(@NotBlank LocalDate dateSet) {
        this(null, dateSet);
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getDateSet());
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
                ", dateSet=" + dateSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        if (!super.equals(o)) return false;

        Menu menu = (Menu) o;

        if (restaurant != null ? !restaurant.equals(menu.restaurant) : menu.restaurant != null) return false;
        if (dateSet != null ? !dateSet.equals(menu.dateSet) : menu.dateSet != null) return false;
        return !(dishes != null ? !dishes.equals(menu.dishes) : menu.dishes != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (dateSet != null ? dateSet.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }
}
