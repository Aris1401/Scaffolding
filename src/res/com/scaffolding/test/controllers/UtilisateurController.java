package com.scaffolding.test.controllers;

import com.scaffolding.test.models.Utilisateur;
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
import com.scaffolding.test.service.UtilisateurService;
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
@RequestMapping("/api/v1/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("")
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurService.getAllUtilisateur();
    }

    @GetMapping(path = "/p/{page}")
    public Page<Utilisateur> getAllUtilisateurPage(@PathVariable(name = "page") int page) {
        Pageable paging = PageRequest.of(page - 1, 5);
        return utilisateurService.getAllUtilisateurPage(paging);
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable int id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping("")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateurById(@PathVariable int id) {
        utilisateurService.deleteUtilisateurById(id);
    }

    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateurById(id, utilisateur);
    }
}
