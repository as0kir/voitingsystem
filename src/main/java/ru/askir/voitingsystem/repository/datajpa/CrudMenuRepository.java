package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Menu;
import ru.askir.voitingsystem.to.MenuTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer>{
    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Menu save(Menu item);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.dateSet DESC")
    List<Menu> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Menu getWithRestaurant(int id, int restaurantId);


    @Query("SELECT m FROM Menu m WHERE m.restaurant.id = ?1 and m.dateSet = ?2")
    Menu get(int restaurantId, LocalDate dateSet);

    //Menu DateSetEqualsAndRestaurant_IdEquals(LocalDate dateSet, int restaurantId);

    @Query("SELECT new ru.askir.voitingsystem.to.MenuTo(m.restaurant.id, m.restaurant.name, m.id, m.voites.size) from Menu m where m.dateSet =:dateSet")
    List<MenuTo> getAllWithVoites(@Param("dateSet") LocalDate dateSet);
}
