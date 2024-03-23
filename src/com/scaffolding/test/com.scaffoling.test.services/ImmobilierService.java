package com.scaffoling.test.services;

import com.scaffolding.test.models.Immobilier;
import com.scaffolding.test.repositories.ImmobilierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImmobilierService {

    @Autowired
    private ImmobilierRepository immobilierRepository;

    public List<Immobilier> getAllImmobilier() {
        return immobilierRepository.findAll();
    }

    public Immobilier getImmobilierById(int id) {
        // Implement your logic to retrieve Immobilier by id
        // This is just a placeholder, you should implement according to your requirements
        return immobilierRepository.findById(id).get();
    }

    public Immobilier saveImmobilier(Immobilier immobilier) {
        return immobilierRepository.save(immobilier);
    }

    public void deleteImmobilierById(int id) {
        // Implement your logic to delete Immobilier by id
        // This is just a placeholder, you should implement according to your requirements
        immobilierRepository.deleteById(id);
    }

    public Immobilier updateImmobilierById(int id, Immobilier immobilier) {
        // Implement your logic to update Immobilier by id
        // This is just a placeholder, you should implement according to your requirements
        return immobilierRepository.save(immobilier);
    }
}