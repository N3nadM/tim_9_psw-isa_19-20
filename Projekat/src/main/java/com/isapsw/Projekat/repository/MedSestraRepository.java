package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.MedicinskaSestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedSestraRepository extends JpaRepository<MedicinskaSestra, Long> {

    MedicinskaSestra findMedicinskaSestraByKorisnikId(Long id);
}
