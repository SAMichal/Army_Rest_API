package pjatk.tpo.tpo6_sm_s30679.Controllers;

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

    @GetMapping("/data")
    public ResponseEntity<List<Zolnierz>> getAllZolnierzeApi(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir) {
        List<Zolnierz> żołnierze;
        if ("nazwisko".equalsIgnoreCase(sortBy)) {
            żołnierze = zolnierzService.getAllZolnierzeSortedByNazwisko(sortDir);
        } else if ("ranga".equalsIgnoreCase(sortBy)) {
            żołnierze = zolnierzService.getAllZolnierzeSortedByRanga(sortDir);
        } else {
            żołnierze = zolnierzService.wszyscy();
        }
        return new ResponseEntity<>(żołnierze, HttpStatus.OK);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Zolnierz> getZolnierzByIdApi(@PathVariable int id) {
        Optional<Zolnierz> żołnierz = zolnierzService.getZolnierzById(id);
        return żołnierz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/data")
    public ResponseEntity<Zolnierz> createZolnierzApi(@RequestBody Zolnierz zolnierz) {
        Zolnierz savedZolnierz = zolnierzService.saveZolnierz(zolnierz);
        return new ResponseEntity<>(savedZolnierz, HttpStatus.CREATED);
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<Zolnierz> updateZolnierzApi(@PathVariable int id, @RequestBody Zolnierz zolnierz) {
        Optional<Zolnierz> existingŻołnierz = zolnierzService.getZolnierzById(id);
        if (existingŻołnierz.isPresent()) {
            zolnierz.setId(id);
            Zolnierz updatedZolnierz = zolnierzService.saveZolnierz(zolnierz);
            return new ResponseEntity<>(updatedZolnierz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<Void> deleteZolnierzApi(@PathVariable int id) {
        if (zolnierzService.getZolnierzById(id).isPresent()) {
            zolnierzService.deleteZolnierz(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/soldiers")
    public String viewSoldiersPage(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            Model model) {
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