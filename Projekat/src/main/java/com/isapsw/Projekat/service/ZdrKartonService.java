package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.ZdrKarton;
import com.isapsw.Projekat.repository.ZdrKartonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZdrKartonService {


    @Autowired
    private ZdrKartonRepository zdrKartonRepository;

    public ZdrKarton findZdrKarton(String id) { return  zdrKartonRepository.findByPacijentId(Long.parseLong(id));}

}
