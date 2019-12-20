package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Odsustvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdsustvoRepository extends JpaRepository<Odsustvo, Long> {
}
