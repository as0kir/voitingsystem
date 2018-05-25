package ru.askir.voitingsystem.model;

import ru.askir.voitingsystem.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "voites", uniqueConstraints = {@UniqueConstraint(name = "voites_unique_user_menu", columnNames = {"id_user", "id_menu"})})
public class Voite extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(groups = View.Persist.class)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", nullable = false)
    @NotNull(groups = View.Persist.class)
    private Menu menu;

    @Column(name = "date_set", nullable = false)
    @NotNull
    private LocalDateTime dateSet;

    public Voite() {
    }

    public Voite(Integer id, User user, Menu menu, LocalDateTime dateSet) {
        super(id);
        this.user = user;
        this.menu = menu;
        this.dateSet = dateSet;
    }

    public Voite(User user, Menu menu, LocalDateTime dateSet) {
        this(null, user, menu, dateSet);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public LocalDateTime getDateSet() {
        return dateSet;
    }

    public void setDateSet(LocalDateTime dateSet) {
        this.dateSet = dateSet;
    }

    @Override
    public String toString() {
        return "Voite{" +
                "dateSet=" + dateSet +
                '}';
    }
}
