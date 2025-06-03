package pjatk.tpo.tpo6_sm_s30679.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.tpo.tpo6_sm_s30679.Models.Status;
import pjatk.tpo.tpo6_sm_s30679.Models.Zolnierz;
import pjatk.tpo.tpo6_sm_s30679.Repositories.ZolnierzRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ZolnierzService {
    @Autowired
    private ZolnierzRepository repository;
    public List<Zolnierz> wszyscy() {
        return repository.findAll();
    }

    public Zolnierz saveZolnierz(Zolnierz zolnierz) {
        return repository.save(zolnierz);
    }

    public Optional<Zolnierz> getZolnierzById(int id) {
        return repository.findById(id);
    }

    public void deleteZolnierz(int id) {
        repository.deleteById(id);
    }

    public List<Zolnierz> getAllZolnierzeSortedByNazwisko(String direction) {
        if ("desc".equalsIgnoreCase(direction)) {
            return repository.findAllByOrderByNazwiskoDesc();
        }
        return repository.findAllByOrderByNazwiskoAsc();
    }

    public List<Zolnierz> getAllZolnierzeSortedByRanga(String direction) {
        if ("desc".equalsIgnoreCase(direction)) {
            return repository.findAllByOrderByRangaDesc();
        }
        return repository.findAllByOrderByRangaAsc();
    }

    public List<Zolnierz> getAllZolnierzeSortedById(String direction) {
        if ("desc".equalsIgnoreCase(direction)) {
            return repository.findAllByOrderByIdDesc();
        }
        return repository.findAllByOrderByIdAsc();
    }

    public List<Zolnierz> getAllZolnierzeSortedByStatus(String direction) {
        if ("desc".equalsIgnoreCase(direction)) {
            return repository.findAllByOrderByStatusDesc();
        }
        return repository.findAllByOrderByStatusAsc();
    }
    public List<Zolnierz> getAllZolnierzeSortedByImie(String direction) {
        if ("desc".equalsIgnoreCase(direction)) {
            return repository.findAllByOrderByImieDesc();
        }
        return repository.findAllByOrderByImieAsc();
    }
}