package ru.askir.voitingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(name = "dishes_unique_id_menu_name", columnNames = {"id_menu", "name"})})
public class Dish extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", nullable = false)
    @NotNull
    private Menu menu;

    @Column(name = "name", nullable = false)
    private @NotBlank String name;

    @Column(name = "price", nullable = false)
    private @NotBlank Float price;

    public Dish() {
    }

    public Dish(Integer id, @NotBlank String name, @NotBlank Float price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public Dish(@NotBlank String name, @NotBlank Float price) {
        this(null, name, price);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
