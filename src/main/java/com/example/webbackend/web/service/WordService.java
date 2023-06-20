package com.example.webbackend.web.service;

import com.example.webbackend.web.entity.Word;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface WordService {
    public Word getWordByID(int id);

//    public Word getWordByTextE(String textE);
//
//    public Word getWordByTextVN(String textVN);

    public ArrayList<Word> getAllWord();

    public  ArrayList<Word> getListWord(ArrayList<Integer> list);

    public Word saveWord(Word word);

    public void updateTextE(int id, String texte);

    public void updateTextVN(int id, String textvn);

    public void deleteByID(int id);

    public long count();

}
