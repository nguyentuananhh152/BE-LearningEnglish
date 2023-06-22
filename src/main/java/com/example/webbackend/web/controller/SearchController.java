package com.example.webbackend.web.controller;

import com.example.webbackend.web.entity.Question;
import com.example.webbackend.web.entity.Word;
import com.example.webbackend.web.service.WordServiceImplement;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
//import com.deepl.api.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
@Controller
@CrossOrigin
public class SearchController {
    @Autowired
    private WordServiceImplement wordServiceImplement;

    private int numberWordLearned = 0;
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
    //https://google-translate1.p.rapidapi.com/language/translate/v2/languages

    public SearchController() {}

//    public String translate(String fromLang, String toLang, String text) throws Exception {
//        // TODO: Should have used a 3rd party library to make a JSON string from an object
//        String jsonPayload = new StringBuilder()
//                .append("{")
//                .append("\"fromLang\":\"")
//                .append(fromLang)
//                .append("\",")
//                .append("\"toLang\":\"")
//                .append(toLang)
//                .append("\",")
//                .append("\"text\":\"")
//                .append(text)
//                .append("\"")
//                .append("}")
//                .toString();
//
//        URL url = new URL(ENDPOINT);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setDoOutput(true);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
//        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
//        conn.setRequestProperty("Content-Type", "application/json");
//
//        OutputStream os = conn.getOutputStream();
//        os.write(jsonPayload.getBytes());
//        os.flush();
//        os.close();
//
//        int statusCode = conn.getResponseCode();
//        System.out.println("Status Code: " + statusCode);
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
//        ));
//        String output = "";
////        while ((output = br.readLine()) != null) {
////            output += br.readLine();
////        }
//        output += br.readLine();
//        conn.disconnect();
//        return output;
//    }



    @RequestMapping("/search")
    @ResponseBody
    public Word searchByKeyword(@RequestParam("keyword") String keyword) {
        try {
            if (keyword != "" || keyword != null) {
                String target = "vi";
                String source = "en";
                keyword = keyword.trim();
                keyword = keyword.replaceAll(" ", "%");
                System.out.println(keyword);
                // https://rapidapi.com/googlecloud/api/google-translate1/
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
                        .header("content-type", "application/x-www-form-urlencoded")
                        .header("Accept-Encoding", "application/gzip")
                        .header("X-RapidAPI-Key", "c3d1d22e8dmsh43acf321320b1b8p1ca55ejsn8fe242f75372")
                        .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                        .method("POST", HttpRequest.BodyPublishers.ofString("q=" +keyword
                                + "&target=" + target
                                + "&source=" + source))
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
                String[] text1 = response.body().split("\":\"");
                String[] text2 = text1[1].split("\"");
                String textVN = text2[0];
                numberWordLearned++;
                Word w = new Word(keyword, textVN);
                wordServiceImplement.saveWord(w);
                return w;
            } else {
                return new Word("Error keyword null", "Error keyword null");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new Word("Error e", "Error v");
        }
    }

    @RequestMapping("/statistical")
    @ResponseBody
    public int GET_statistical() {
        return this.numberWordLearned;
    }





}
