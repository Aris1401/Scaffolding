package com.scaffolding.test.repository;

import com.scaffolding.test.models.Immobilier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobilierRepository extends JpaRepository<Immobilier, Integer> {

}
