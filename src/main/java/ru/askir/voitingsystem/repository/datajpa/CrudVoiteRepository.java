package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.model.Voite;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoiteRepository extends JpaRepository<Voite, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Voite v WHERE v.id=:id AND v.user.id=:userId AND v.menu.id=:menuId")
    int delete(@Param("id") int id, @Param("userId") int userId, @Param("menuId") int menuId);

    @Override
    @Transactional
    Voite save(Voite item);

    @Query("SELECT v FROM Voite v WHERE v.user.id=:userId AND v.menu.id=:menuId ORDER BY v.dateSet DESC")
    List<Voite> getAll(@Param("userId") int userId, @Param("menuId") int menuId);

    @Query("SELECT d FROM Voite d JOIN FETCH d.menu WHERE d.id = ?1 and d.user.id = ?2 and d.menu.id = ?3")
    Voite getWithUserAndMenu(int id, int userId, int menuId);
}
