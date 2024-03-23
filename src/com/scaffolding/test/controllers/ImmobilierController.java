package com.scaffolding.test.controllers;

import com.scaffolding.test.models.Immobilier;
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
import com.scaffoling.test.services.ImmobilierService;
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
@RequestMapping("/api/v1/immobiliers")
public class ImmobilierController {
    @Autowired
    private ImmobilierService immobilierService;

    @GetMapping("")
    public List<Immobilier> getAllImmobilier() {
        return immobilierService.getAllImmobilier();
    }

    @GetMapping("/{id}")
    public Immobilier getImmobilierById(@PathVariable int id) {
        return immobilierService.getImmobilierById(id);
    }

    @PostMapping("")
    public Immobilier saveImmobilier(@RequestBody Immobilier immobilier) {
        return immobilierService.saveImmobilier(immobilier);
    }

    @DeleteMapping("/{id}")
    public void deleteImmobilierById(@PathVariable int id) {
        immobilierService.deleteImmobilierById(id);
    }

    @PutMapping("/{id}")
    public Immobilier updateImmobilier(@PathVariable int id, @RequestBody Immobilier immobilier) {
        return immobilierService.updateImmobilierById(id, immobilier);
    }
}
