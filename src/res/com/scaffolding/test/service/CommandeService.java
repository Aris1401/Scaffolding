package com.scaffolding.test.service;

import com.scaffolding.test.models.Commande;
import com.scaffolding.test.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommande() {
        return commandeRepository.findAll();
    }

    public Page<Commande> getAllCommandePage(Pageable paging) {
        return commandeRepository.findAll(paging);
    }

    public Commande getCommandeById(int id) {
        // Implement your logic to retrieve Commande by id
        // This is just a placeholder, you should implement according to your requirements
        return commandeRepository.findById(id).get();
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteCommandeById(int id) {
        // Implement your logic to delete Commande by id
        // This is just a placeholder, you should implement according to your requirements
        commandeRepository.deleteById(id);
    }

    public Commande updateCommandeById(int id, Commande commande) {
        // Implement your logic to update Commande by id
        // This is just a placeholder, you should implement according to your requirements
        return commandeRepository.save(commande);
    }
}
