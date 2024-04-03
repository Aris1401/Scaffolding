package com.scaffolding.test.service;

import com.scaffolding.test.models.Utilisation;
import com.scaffolding.test.repository.UtilisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UtilisationService {

    @Autowired
    private UtilisationRepository utilisationRepository;

    public List<Utilisation> getAllUtilisation() {
        return utilisationRepository.findAll();
    }

    public Page<Utilisation> getAllUtilisationPage(Pageable paging) {
        return utilisationRepository.findAll(paging);
    }

    public Utilisation getUtilisationById(int id) {
        // Implement your logic to retrieve Utilisation by id
        // This is just a placeholder, you should implement according to your requirements
        return utilisationRepository.findById(id).get();
    }

    public Utilisation saveUtilisation(Utilisation utilisation) {
        return utilisationRepository.save(utilisation);
    }

    public void deleteUtilisationById(int id) {
        // Implement your logic to delete Utilisation by id
        // This is just a placeholder, you should implement according to your requirements
        utilisationRepository.deleteById(id);
    }

    public Utilisation updateUtilisationById(int id, Utilisation utilisation) {
        // Implement your logic to update Utilisation by id
        // This is just a placeholder, you should implement according to your requirements
        return utilisationRepository.save(utilisation);
    }
}
