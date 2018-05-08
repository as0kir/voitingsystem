package ru.askir.voitingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(name = "restaurant_name", columnNames = "name")})
public class Restaurant extends AbstractBaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }
}
