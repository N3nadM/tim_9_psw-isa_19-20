package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.AdminKlinCentra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKCRepository extends JpaRepository<AdminKlinCentra, Long> {


}
