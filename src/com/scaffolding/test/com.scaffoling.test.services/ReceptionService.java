package com.scaffoling.test.services;

import com.scaffolding.test.models.Reception;
import com.scaffolding.test.repositories.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReceptionService {

    @Autowired
    private ReceptionRepository receptionRepository;

    public List<Reception> getAllReception() {
        return receptionRepository.findAll();
    }

    public Reception getReceptionById(int id) {
        // Implement your logic to retrieve Reception by id
        // This is just a placeholder, you should implement according to your requirements
        return receptionRepository.findById(id).get();
    }

    public Reception saveReception(Reception reception) {
        return receptionRepository.save(reception);
    }

    public void deleteReceptionById(int id) {
        // Implement your logic to delete Reception by id
        // This is just a placeholder, you should implement according to your requirements
        receptionRepository.deleteById(id);
    }

    public Reception updateReceptionById(int id, Reception reception) {
        // Implement your logic to update Reception by id
        // This is just a placeholder, you should implement according to your requirements
        return receptionRepository.save(reception);
    }
}
