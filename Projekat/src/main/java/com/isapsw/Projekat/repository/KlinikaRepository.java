package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Klinika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KlinikaRepository extends JpaRepository<Klinika, Long> {
    List<Klinika> findAll();

    Optional<Klinika> findById(Long id);
}
