package com.scaffolding.test.controllers;

import com.scaffolding.test.models.Commande;
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
import com.scaffoling.test.services.CommandeService;
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
@RequestMapping("/api/v1/commandes")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @GetMapping("")
    public List<Commande> getAllCommande() {
        return commandeService.getAllCommande();
    }

    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable int id) {
        return commandeService.getCommandeById(id);
    }

    @PostMapping("")
    public Commande saveCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommandeById(@PathVariable int id) {
        commandeService.deleteCommandeById(id);
    }

    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable int id, @RequestBody Commande commande) {
        return commandeService.updateCommandeById(id, commande);
    }
}
