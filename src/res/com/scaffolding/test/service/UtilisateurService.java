package com.scaffolding.test.service;

import com.scaffolding.test.models.Utilisateur;
import com.scaffolding.test.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    public Page<Utilisateur> getAllUtilisateurPage(Pageable paging) {
        return utilisateurRepository.findAll(paging);
    }

    public Utilisateur getUtilisateurById(int id) {
        // Implement your logic to retrieve Utilisateur by id
        // This is just a placeholder, you should implement according to your requirements
        return utilisateurRepository.findById(id).get();
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateurById(int id) {
        // Implement your logic to delete Utilisateur by id
        // This is just a placeholder, you should implement according to your requirements
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur updateUtilisateurById(int id, Utilisateur utilisateur) {
        // Implement your logic to update Utilisateur by id
        // This is just a placeholder, you should implement according to your requirements
        return utilisateurRepository.save(utilisateur);
    }
}
