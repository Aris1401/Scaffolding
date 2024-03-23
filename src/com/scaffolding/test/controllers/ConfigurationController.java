package com.scaffolding.test.controllers;

import com.scaffolding.test.models.Configuration;
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
import com.scaffoling.test.services.ConfigurationService;
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
@RequestMapping("/api/v1/configurations")
public class ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    @GetMapping("")
    public List<Configuration> getAllConfiguration() {
        return configurationService.getAllConfiguration();
    }

    @GetMapping("/{id}")
    public Configuration getConfigurationById(@PathVariable int id) {
        return configurationService.getConfigurationById(id);
    }

    @PostMapping("")
    public Configuration saveConfiguration(@RequestBody Configuration configuration) {
        return configurationService.saveConfiguration(configuration);
    }

    @DeleteMapping("/{id}")
    public void deleteConfigurationById(@PathVariable int id) {
        configurationService.deleteConfigurationById(id);
    }

    @PutMapping("/{id}")
    public Configuration updateConfiguration(@PathVariable int id, @RequestBody Configuration configuration) {
        return configurationService.updateConfigurationById(id, configuration);
    }
}
