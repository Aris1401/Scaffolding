package com.scaffolding.test.service;

import com.scaffolding.test.models.Configuration;
import com.scaffolding.test.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    public List<Configuration> getAllConfiguration() {
        return configurationRepository.findAll();
    }

    public Page<Configuration> getAllConfigurationPage(Pageable paging) {
        return configurationRepository.findAll(paging);
    }

    public Configuration getConfigurationById(int id) {
        // Implement your logic to retrieve Configuration by id
        // This is just a placeholder, you should implement according to your requirements
        return configurationRepository.findById(id).get();
    }

    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public void deleteConfigurationById(int id) {
        // Implement your logic to delete Configuration by id
        // This is just a placeholder, you should implement according to your requirements
        configurationRepository.deleteById(id);
    }

    public Configuration updateConfigurationById(int id, Configuration configuration) {
        // Implement your logic to update Configuration by id
        // This is just a placeholder, you should implement according to your requirements
        return configurationRepository.save(configuration);
    }
}
