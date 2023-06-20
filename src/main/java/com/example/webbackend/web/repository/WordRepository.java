package com.example.webbackend.web.repository;

import com.example.webbackend.web.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
//    public Word findByTexte(String textE);
//
//    public Word findByTextvn(String textvn);
}
