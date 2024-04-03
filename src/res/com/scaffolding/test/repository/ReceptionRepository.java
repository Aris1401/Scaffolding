package com.scaffolding.test.repository;

import com.scaffolding.test.models.Reception;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, Integer> {

}
