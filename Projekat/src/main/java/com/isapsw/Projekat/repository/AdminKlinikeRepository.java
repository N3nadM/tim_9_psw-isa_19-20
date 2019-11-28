package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.AdminKlinike;

import com.isapsw.Projekat.domain.Klinika;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminKlinikeRepository extends JpaRepository<AdminKlinike, Long> {

    Optional<AdminKlinike> findById(Long id);

    List<AdminKlinike> findAll();

    Optional<AdminKlinike> findAdminKlinikeByKorisnikId(Long id);
}
