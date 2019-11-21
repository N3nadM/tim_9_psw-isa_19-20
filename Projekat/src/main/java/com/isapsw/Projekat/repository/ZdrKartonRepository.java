package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.ZdrKarton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZdrKartonRepository extends JpaRepository<ZdrKarton, Long> {

    ZdrKarton findByPacijentId(Long id);
}
