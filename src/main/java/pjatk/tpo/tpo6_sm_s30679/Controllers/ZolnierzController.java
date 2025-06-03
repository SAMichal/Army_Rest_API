package pjatk.tpo.tpo6_sm_s30679.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pjatk.tpo.tpo6_sm_s30679.Models.Ranga;
import pjatk.tpo.tpo6_sm_s30679.Models.Status;
import pjatk.tpo.tpo6_sm_s30679.Models.Zolnierz;
import pjatk.tpo.tpo6_sm_s30679.Services.ZolnierzService;

import java.util.List;
import java.util.Optional;

@Controller
public class ZolnierzController {

    @Autowired
    private ZolnierzService zolnierzService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Witaj w aplikacji Spring Boot + Thymeleaf!");
        return "layout";
    }
    //http://localhost:8080/data?sortBy=ranga&sortDir=desc
    @GetMapping("/data")
    public ResponseEntity<List<Zolnierz>> getAllZolnierze(@RequestParam(required = false) String sortBy, @RequestParam(required = false, defaultValue = "asc") String sortDir)
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
        else if("ranga".equalsIgnoreCase(sortBy))
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

    @GetMapping("/data/{id}")
    public ResponseEntity<Zolnierz> getZolnierzById(@PathVariable int id) {
        Optional<Zolnierz> czyistnieje_zolnierz=zolnierzService.getZolnierzById(id);
        if(czyistnieje_zolnierz.isPresent())
        {
            return new ResponseEntity<>(czyistnieje_zolnierz.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/data")
    public ResponseEntity<Zolnierz> createZolnierz(@RequestBody Zolnierz zolnierz) {
        Zolnierz zapisanyZolnierz = zolnierzService.saveZolnierz(zolnierz);
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
        return new ResponseEntity<>(zapisanyZolnierz, HttpStatus.CREATED);
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<Zolnierz> updateZolnierz(@PathVariable int id, @RequestBody Zolnierz zolnierz) {
        Optional<Zolnierz> czyistnieje_zolnierz=zolnierzService.getZolnierzById(id);
        if(czyistnieje_zolnierz.isPresent())
        {
            zolnierz.setId(id);
            Zolnierz zmieniony=zolnierzService.saveZolnierz(zolnierz);
            return new ResponseEntity<>(zmieniony, HttpStatus.OK);
        }
        else
        {
            System.out.println("Nie znaleziono takiego żołnierza");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<Void> deleteZolnierz(@PathVariable int id)
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

    @GetMapping("/soldiers")
    public String viewSoldiersPage(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            Model model, HttpServletRequest request) {
        model.addAttribute("requestURI", request.getRequestURI());
        List<Zolnierz> żołnierze;
        if ("nazwisko".equalsIgnoreCase(sortBy)) {
            żołnierze = zolnierzService.getAllZolnierzeSortedByNazwisko(sortDir);
        } else if ("ranga".equalsIgnoreCase(sortBy)) {
            żołnierze = zolnierzService.getAllZolnierzeSortedByRanga(sortDir);
        } else {
            żołnierze = zolnierzService.wszyscy();
        }
        model.addAttribute("żołnierze", żołnierze);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        return "soldiers";
    }

    @GetMapping("/soldiers/new")
    public String showNewSoldierForm(Model model) {
        Zolnierz zolnierz = new Zolnierz();
        model.addAttribute("żołnierz", zolnierz);
        model.addAttribute("rangaOptions", Ranga.values());
        model.addAttribute("statusOptions", Status.values());
        return "soldier-form";
    }

    @PostMapping("/soldiers/save")
    public String saveSoldier(@ModelAttribute("żołnierz") Zolnierz zolnierz) {
        zolnierzService.saveZolnierz(zolnierz);
        return "redirect:/soldiers"; // Przekierowanie po zapisaniu
    }

    @GetMapping("/soldiers/edit/{id}")
    public String showEditSoldierForm(@PathVariable int id, Model model) {
        Optional<Zolnierz> żołnierz = zolnierzService.getZolnierzById(id);
        if (żołnierz.isPresent()) {
            model.addAttribute("żołnierz", żołnierz.get());
            model.addAttribute("rangaOptions", Ranga.values());
            model.addAttribute("statusOptions", Status.values());
            return "soldier-form";
        } else {
            return "redirect:/soldiers";
        }
    }

    @GetMapping("/soldiers/delete/{id}")
    public String deleteSoldier(@PathVariable int id) {
        zolnierzService.deleteZolnierz(id);
        return "redirect:/soldiers";
    }
}