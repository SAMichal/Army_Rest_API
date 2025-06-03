package pjatk.tpo.tpo6_sm_s30679.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pjatk.tpo.tpo6_sm_s30679.Models.Status;
import pjatk.tpo.tpo6_sm_s30679.Models.Zolnierz;
import pjatk.tpo.tpo6_sm_s30679.Services.ZolnierzService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/data")
public class ZolnierzController {

    @Autowired
    private ZolnierzService zolnierzService;
    //Dla interfejsu graficznego
    @GetMapping("/armia")
    public String glownyInterfejs() {
        return "armia";
    }
    @GetMapping("/zolnierze")
    public String wyswietlZolnierzy() {
        return "zolnierze";
    }
    @GetMapping("/dodajZolnierza")
    public String dodajZolnierza() {
        return "dodajZolnierza";
    }
    @GetMapping("/usunZolnierza")
    public String usunZolnierza() {
        return "usunZolnierza";
    }
    @GetMapping("/edytujZolnierza")
    public String edytujZolnierzaStrona() {
        return "edytujZolnierza";
    }

    //endpointy
    //http://localhost:8080/data?sortBy=ranga&sortDir=desc
    @GetMapping()
    public ResponseEntity<List<Zolnierz>> zwroc_zolnierzy(@RequestParam(required=false) String sortBy, @RequestParam(required=false, defaultValue="asc") String sortDir)
    {
        List<Zolnierz> zolnierze;
        if("nazwisko".equalsIgnoreCase(sortBy))
        {
            zolnierze=zolnierzService.getAllZolnierzeSortedByNazwisko(sortDir);
        }
        else if("ranga".equalsIgnoreCase(sortBy))
        {
            zolnierze=zolnierzService.getAllZolnierzeSortedByRanga(sortDir);
        }
        else if("id".equalsIgnoreCase(sortBy))
        {
            zolnierze=zolnierzService.getAllZolnierzeSortedById(sortDir);
        }
        else if("status".equalsIgnoreCase(sortBy))
        {
            zolnierze=zolnierzService.getAllZolnierzeSortedByStatus(sortDir);
        }
        else if("imie".equalsIgnoreCase(sortBy))
        {
            zolnierze=zolnierzService.getAllZolnierzeSortedByImie(sortDir);
        }
        else
        {
            zolnierze=zolnierzService.wszyscy();
        }
        return new ResponseEntity<>(zolnierze, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Zolnierz> dodaj_zolnierza(@RequestBody Zolnierz zolnierz) {
        Zolnierz nowy_zolnierz=zolnierzService.saveZolnierz(zolnierz);
        try
        {
            if(zolnierz.getPesel().length()!=11 || !(zolnierz.getPesel().matches("\\d+")))
            {
                throw new Exception("Pesel składa się z 11 cyfr");
            }
            if(!(zolnierz.getStatus().equals(Status.SZPITAL_POLOWY)||zolnierz.getStatus().equals(Status.MARTWY)) && zolnierz.getObrazenia()!=null)
            {
                throw new Exception("Obrażenia mają tylko przebywający w szpitalu polowym lub martwi");
            }
            for(Zolnierz z : zolnierzService.wszyscy())
            {
                if(z.getPesel().equals(zolnierz.getPesel())) {
                    throw new Exception("Taki żołnierz już istnieje");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(zolnierz.getObrazenia()==null)
        {
            zolnierz.setObrazenia("brak");
        }
        System.out.println("Dodano żołnierza");
        return new ResponseEntity<>(nowy_zolnierz, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Zolnierz> edytuj_zolnierza(@PathVariable int id, @RequestBody Zolnierz zolnierz) {
        try {
            Optional<Zolnierz> czyistnieje_zolnierz = zolnierzService.getZolnierzById(id);
            if (czyistnieje_zolnierz.isPresent()) {
                zolnierz.setId(id);
                Zolnierz zmieniony = zolnierzService.saveZolnierz(zolnierz);
                if(zmieniony.getPesel().length()!=11 || !(zmieniony.getPesel().matches("\\d+")))
                {
                    throw new Exception("Pesel składa się z 11 cyfr");
                }
                if(!(zolnierz.getStatus().equals(Status.SZPITAL_POLOWY)||zolnierz.getStatus().equals(Status.MARTWY)) && zolnierz.getObrazenia()!=null)
                {
                    throw new Exception("Obrażenia mają tylko przebywający w szpitalu polowym lub martwi");
                }
                return new ResponseEntity<>(zmieniony, HttpStatus.OK);
            }
            else {
                System.out.println("Nie znaleziono takiego żołnierza");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> usun_zolnierza(@PathVariable int id)
    {
        Optional<Zolnierz> czyistnieje_zolnierz=zolnierzService.getZolnierzById(id);
        if(czyistnieje_zolnierz.isPresent())
        {
            zolnierzService.deleteZolnierz(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            System.out.println("Nie znaleziono takiego żołnierza");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}