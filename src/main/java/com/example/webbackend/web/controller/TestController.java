package com.example.webbackend.web.controller;

import com.example.webbackend.web.entity.Word;
import com.example.webbackend.web.service.WordServiceImplement;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Random;

@Controller
@CrossOrigin
public class TestController {
    @Autowired
    private WordServiceImplement wordServiceImplement;

    public TestController() {}

    @GetMapping("/test")
    @ResponseBody
    public ArrayList<Word> GET_test(@PathParam("number") int number) {
        try {
            if (number < 5 || number >20) {
                return new ArrayList<Word>();
            }
            if (wordServiceImplement.count() > number) {
                int length = (int) wordServiceImplement.count();
                ArrayList<Word> arrayTest = new ArrayList<>();
                ArrayList<Integer> check = new ArrayList<>();
                Random r = new Random();
                int n = 0;
                for (int i = 0; i < number; i++) {
                    n = r.nextInt(length);
                    if (!check.contains(n) && wordServiceImplement.exist(n)) {
                        arrayTest.add(wordServiceImplement.getWordByID(n));
                        check.add(n);
                    } else {
                        i--;
                    }
                }
                return arrayTest;
            } else {
                return wordServiceImplement.getAllWord();
            }
        } catch (Exception e) {
            return new ArrayList<Word>();
        }
    }

}
