package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.menu.id=:menuId")
    int delete(@Param("id") int id, @Param("menuId") int menuId);

    @Override
    @Transactional
    Dish save(Dish item);

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId ORDER BY d.name ASC")
    List<Dish> getAll(@Param("menuId") int menuId);

    @Query("SELECT d FROM Dish d JOIN FETCH d.menu WHERE d.id = ?1 and d.menu.id = ?2")
    Dish getWithMenu(int id, int menuId);
    
}
