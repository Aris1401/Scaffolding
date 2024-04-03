package com.scaffolding.test.repository;

import com.scaffolding.test.models.Utilisation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisationRepository extends JpaRepository<Utilisation, Integer> {

}
