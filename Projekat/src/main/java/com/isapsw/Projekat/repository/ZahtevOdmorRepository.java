package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.ZahtevOdmor;
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
public interface ZahtevOdmorRepository extends JpaRepository<ZahtevOdmor, Long> {

    List<ZahtevOdmor> findAll();

    List<ZahtevOdmor> findAllByKlinikaId(Long id);

    Optional<ZahtevOdmor> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query("SELECT z FROM ZahtevOdmor z WHERE z.id = :id")
    Optional<ZahtevOdmor> findByIdTransaction(@Param("id") Long id);
}
