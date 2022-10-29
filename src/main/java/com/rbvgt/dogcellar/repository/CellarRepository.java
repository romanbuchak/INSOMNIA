package com.rbvgt.dogcellar.repository;

import com.rbvgt.dogcellar.model.Cellar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellarRepository extends JpaRepository<Cellar, Integer> {
}
