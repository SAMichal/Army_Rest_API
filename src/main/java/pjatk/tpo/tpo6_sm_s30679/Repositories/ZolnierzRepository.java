package pjatk.tpo.tpo6_sm_s30679.Repositories;

import org.springframework.stereotype.Repository;
import pjatk.tpo.tpo6_sm_s30679.Models.Zolnierz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

@Repository
public interface ZolnierzRepository extends JpaRepository<Zolnierz, Integer>{
    ArrayList<Zolnierz> findAllByOrderByNazwiskoAsc();
    ArrayList<Zolnierz> findAllByOrderByNazwiskoDesc();
    ArrayList<Zolnierz> findAllByOrderByRangaAsc();
    ArrayList<Zolnierz> findAllByOrderByRangaDesc();
}
