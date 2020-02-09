package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.ZahtevOdsustvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface ZahtevOdsustvoRepository extends JpaRepository<ZahtevOdsustvo, Long> {

    List<ZahtevOdsustvo> findAll();

    List<ZahtevOdsustvo> findAllByKlinikaId(Long id);

    Optional<ZahtevOdsustvo> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query("SELECT z FROM ZahtevOdsustvo z WHERE z.id = :id")
    Optional<ZahtevOdsustvo> findByIdTransaction(@Param("id") Long id);
}
