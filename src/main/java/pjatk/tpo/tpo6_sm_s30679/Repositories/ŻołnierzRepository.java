package pjatk.tpo.tpo6_sm_s30679.Repositories;

import org.springframework.stereotype.Repository;
import pjatk.tpo.tpo6_sm_s30679.Models.Żołnierz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

@Repository
public interface ŻołnierzRepository extends JpaRepository<Żołnierz, Integer>{
    ArrayList<Żołnierz> znajdźPoNazwiskuASC();
    ArrayList<Żołnierz> znajdźPoNazwiskuDSC();
    ArrayList<Żołnierz> znajdźPoRandzeASC();
    ArrayList<Żołnierz> znajdźPoRandzeDSC();
}
