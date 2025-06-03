package pjatk.tpo.tpo6_sm_s30679.Repositories;

import org.springframework.stereotype.Repository;
import pjatk.tpo.tpo6_sm_s30679.Models.Zolnierz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ZolnierzRepository extends JpaRepository<Zolnierz, Integer>{
    List<Zolnierz> findAllByOrderByImieAsc();
    List<Zolnierz> findAllByOrderByImieDesc();
    List<Zolnierz> findAllByOrderByNazwiskoAsc();
    List<Zolnierz> findAllByOrderByNazwiskoDesc();
    List<Zolnierz> findAllByOrderByRangaAsc();
    List<Zolnierz> findAllByOrderByRangaDesc();
    List<Zolnierz> findAllByOrderByIdAsc();
    List<Zolnierz> findAllByOrderByIdDesc();
    List<Zolnierz> findAllByOrderByStatusAsc();
    List<Zolnierz> findAllByOrderByStatusDesc();
}
