package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.ZahtevOdmor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZahtevOdmorRepository extends JpaRepository<ZahtevOdmor, Long> {

    List<ZahtevOdmor> findAll();
}
