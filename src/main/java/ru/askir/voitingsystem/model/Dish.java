package ru.askir.voitingsystem.model;

import ru.askir.voitingsystem.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(name = "dishes_unique_id_menu_name", columnNames = {"id_menu", "name"})})
public class Dish extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", nullable = false)
    @NotNull(groups = View.Persist.class)
    private Menu menu;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "price", nullable = false)
    private @NotNull BigDecimal price;

    public Dish() {
    }

    public Dish(Integer id, String name, BigDecimal price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public Dish(String name, BigDecimal price) {
        this(null, name, price);
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice());
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
