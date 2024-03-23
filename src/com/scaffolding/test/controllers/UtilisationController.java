package com.scaffolding.test.controllers;

import com.scaffolding.test.models.Utilisation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.scaffoling.test.services.UtilisationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/utilisations")
public class UtilisationController {
    @Autowired
    private UtilisationService utilisationService;

    @GetMapping("")
    public List<Utilisation> getAllUtilisation() {
        return utilisationService.getAllUtilisation();
    }

    @GetMapping("/{id}")
    public Utilisation getUtilisationById(@PathVariable int id) {
        return utilisationService.getUtilisationById(id);
    }

    @PostMapping("")
    public Utilisation saveUtilisation(@RequestBody Utilisation utilisation) {
        return utilisationService.saveUtilisation(utilisation);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisationById(@PathVariable int id) {
        utilisationService.deleteUtilisationById(id);
    }

    @PutMapping("/{id}")
    public Utilisation updateUtilisation(@PathVariable int id, @RequestBody Utilisation utilisation) {
        return utilisationService.updateUtilisationById(id, utilisation);
    }
}
