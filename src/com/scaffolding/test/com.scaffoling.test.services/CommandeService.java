package com.scaffoling.test.services;

import com.scaffolding.test.models.Commande;
import com.scaffolding.test.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommande() {
        return commandeRepository.findAll();
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
