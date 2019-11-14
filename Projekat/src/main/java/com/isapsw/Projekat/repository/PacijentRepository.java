package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Long> {

    Pacijent getPacijentByJbzo(String jbzo);

}
