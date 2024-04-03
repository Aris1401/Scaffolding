package com.scaffolding.test.service;

import com.scaffolding.test.models.Fournisseur;
import com.scaffolding.test.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseur() {
        return fournisseurRepository.findAll();
    }

    public Page<Fournisseur> getAllFournisseurPage(Pageable paging) {
        return fournisseurRepository.findAll(paging);
    }

    public Fournisseur getFournisseurById(int id) {
        // Implement your logic to retrieve Fournisseur by id
        // This is just a placeholder, you should implement according to your requirements
        return fournisseurRepository.findById(id).get();
    }

    public Fournisseur saveFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public void deleteFournisseurById(int id) {
        // Implement your logic to delete Fournisseur by id
        // This is just a placeholder, you should implement according to your requirements
        fournisseurRepository.deleteById(id);
    }

    public Fournisseur updateFournisseurById(int id, Fournisseur fournisseur) {
        // Implement your logic to update Fournisseur by id
        // This is just a placeholder, you should implement according to your requirements
        return fournisseurRepository.save(fournisseur);
    }
}
