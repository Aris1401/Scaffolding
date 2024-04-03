package com.scaffolding.test.controllers;

import com.scaffolding.test.models.Fournisseur;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.scaffolding.test.service.FournisseurService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/fournisseurs")
public class FournisseurController {
    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("")
    public List<Fournisseur> getAllFournisseur() {
        return fournisseurService.getAllFournisseur();
    }

    @GetMapping(path = "/p/{page}")
    public Page<Fournisseur> getAllFournisseurPage(@PathVariable(name = "page") int page) {
        Pageable paging = PageRequest.of(page - 1, 5);
        return fournisseurService.getAllFournisseurPage(paging);
    }

    @GetMapping("/{id}")
    public Fournisseur getFournisseurById(@PathVariable int id) {
        return fournisseurService.getFournisseurById(id);
    }

    @PostMapping("")
    public Fournisseur saveFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.saveFournisseur(fournisseur);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseurById(@PathVariable int id) {
        fournisseurService.deleteFournisseurById(id);
    }

    @PutMapping("/{id}")
    public Fournisseur updateFournisseur(@PathVariable int id, @RequestBody Fournisseur fournisseur) {
        return fournisseurService.updateFournisseurById(id, fournisseur);
    }
}
