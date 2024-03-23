package com.scaffolding.test.controllers;

import com.scaffolding.test.models.Reception;
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
import com.scaffoling.test.services.ReceptionService;
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
@RequestMapping("/api/v1/receptions")
public class ReceptionController {
    @Autowired
    private ReceptionService receptionService;

    @GetMapping("")
    public List<Reception> getAllReception() {
        return receptionService.getAllReception();
    }

    @GetMapping("/{id}")
    public Reception getReceptionById(@PathVariable int id) {
        return receptionService.getReceptionById(id);
    }

    @PostMapping("")
    public Reception saveReception(@RequestBody Reception reception) {
        return receptionService.saveReception(reception);
    }

    @DeleteMapping("/{id}")
    public void deleteReceptionById(@PathVariable int id) {
        receptionService.deleteReceptionById(id);
    }

    @PutMapping("/{id}")
    public Reception updateReception(@PathVariable int id, @RequestBody Reception reception) {
        return receptionService.updateReceptionById(id, reception);
    }
}
