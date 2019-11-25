package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.AdminKlinike;
import com.isapsw.Projekat.repository.AdminKlinikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminKlinikeService {

    @Autowired
    private AdminKlinikeRepository adminKlinikeRepository;

    public Optional<AdminKlinike> findById(Long id){
        return adminKlinikeRepository.findById(id);
    }

    public List<AdminKlinike> findAll(){
        return adminKlinikeRepository.findAll();
    }

    public AdminKlinike save(AdminKlinike ak){
        return adminKlinikeRepository.save(ak);
    }
}
