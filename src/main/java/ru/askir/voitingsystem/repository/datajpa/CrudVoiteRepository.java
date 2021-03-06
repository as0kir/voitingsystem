package ru.askir.voitingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.askir.voitingsystem.model.Voite;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoiteRepository extends JpaRepository<Voite, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Voite v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Voite save(Voite item);

    @Query("SELECT v FROM Voite v WHERE v.menu.dateSet=:dateSet ORDER BY v.dateSet DESC")
    List<Voite> getAll(@Param("dateSet") LocalDate dateSet);

    @Query("SELECT v FROM Voite v JOIN FETCH v.menu WHERE v.user.id = :userId and v.menu.dateSet = :dateSet")
    Voite get(@Param("userId") int userId, @Param("dateSet") LocalDate dateSet);

}
