package com.example.webbackend.web.controller;


import com.example.webbackend.web.entity.Word;
import com.example.webbackend.web.service.WordServiceImplement;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@CrossOrigin
public class WordController {
    @Autowired
    private WordServiceImplement wordServiceImplement;

    public WordController() {}

    @RequestMapping("/save")
    public void POST_save(@RequestParam("texte") String texte, @RequestParam("textvn") String textvn) {
        try {
            Word word = new Word(texte, textvn);
            wordServiceImplement.saveWord(word);
        } catch (Exception e) {}
    }

    @PutMapping("update")
    public void PUT_updateWord(@PathParam("id") int id, @RequestParam("textE") String textE, @PathParam("textVN") String textVN) {
        try {
            if (wordServiceImplement.exist(id)) {
                wordServiceImplement.updateTextE(id, textE);
                wordServiceImplement.updateTextVN(id, textVN);
            }
        } catch (Exception e) {}
    }

    @DeleteMapping("/delete")
    public void DELETE_deleteWord(@RequestParam("id") int id) {
        try {
            wordServiceImplement.deleteByID(id);
        } catch (Exception e) {
        }
    }

    @GetMapping("get-all-word-learned")
    @ResponseBody
    public ArrayList<Word> GET_getAllWordLearned() {
        try {
            return wordServiceImplement.getAllWord();
        } catch (Exception e) {
            return new ArrayList<Word>();
        }
    }

}
