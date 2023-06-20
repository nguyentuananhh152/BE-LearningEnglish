package com.example.webbackend.web.service;

import com.example.webbackend.web.entity.Word;
import com.example.webbackend.web.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImplement implements WordService{
    @Autowired
    private WordRepository wordRepository;

    public WordServiceImplement() {}

    @Override
    public Word getWordByID(int id) {
        try {
            if (wordRepository.existsById(id)) {
                return wordRepository.findById(id).get();
            } else {
                return new Word();
            }
        } catch (Exception e) {
            return new Word();
        }
    }

//    @Override
//    public Word getWordByTextE(String textE) {
//        try {
//            if (wordRepository.findByTexte(textE) != null) {
//                return wordRepository.findByTexte(textE);
//            } else {
//                return new Word();
//            }
//        } catch (Exception e) {
//            return new Word();
//        }
//    }

//    @Override
//    public Word getWordByTextVN(String textVN) {
//        try {
//            if (wordRepository.findByTextvn(textVN) != null) {
//                return wordRepository.findByTextvn(textVN);
//            } else {
//                return new Word();
//            }
//        } catch (Exception e) {
//            return new Word();
//        }
//    }

    @Override
    public ArrayList<Word> getAllWord() {
        ArrayList<Word> arr = new ArrayList<>();
        try {
            List<Word> list =  wordRepository.findAll();
            list.forEach(l -> {
                arr.add(l);
            });
            return arr;
        } catch (Exception e) {
            return arr;
        }
    }

    @Override
    public ArrayList<Word> getListWord(ArrayList<Integer> list) {
        ArrayList<Word> arr = new ArrayList<>();
        try {
            List<Word> listWord =  wordRepository.findAllById(list);
            listWord.forEach(l -> {
                arr.add(l);
            });
            return arr;
        } catch (Exception e) {
            return arr;
        }
    }

    @Override
    public Word saveWord(Word word) {
        try {
            Word w =  wordRepository.save(word);
            return w;
        } catch (Exception e) {
            return new Word();
        }
    }

    @Override
    public void updateTextE(int id, String texte) {
        try {
            if (wordRepository.existsById(id)) {
                if (texte != "" && texte != null) {
                    Word word = wordRepository.findById(id).get();
                    word.setTextE(texte);
                    wordRepository.saveAndFlush(word);
                }
            }
        } catch (Exception e) {}
    }

    @Override
    public void updateTextVN(int id, String textvn) {
        try {
            if (wordRepository.existsById(id)) {
                if (textvn != "" && textvn != null) {
                    Word word = wordRepository.findById(id).get();
                    word.setTextE(textvn);
                    wordRepository.saveAndFlush(word);
                }
            }
        } catch (Exception e) {}
    }


    @Override
    public void deleteByID(int id) {
        try {
            wordRepository.deleteById(id);
        } catch (Exception e) {}
    }

    @Override
    public long count() {
        try {
            return wordRepository.count();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean exist(int id) {
        try {
            if (wordRepository.existsById(id)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
