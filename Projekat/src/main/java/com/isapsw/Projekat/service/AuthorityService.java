package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Authority;
import com.isapsw.Projekat.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority findByName(String name){
        return authorityRepository.findAuthorityByName(name);
    }
}
