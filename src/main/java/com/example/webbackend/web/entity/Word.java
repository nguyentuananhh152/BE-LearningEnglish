package com.example.webbackend.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false, unique = true, length = 5)
    private int id;

    @Column
    private String textE;

    @Column
    private String textVN;

    public Word() {
    }

    public Word(String textE, String textVN) {
        this.textE = textE;
        this.textVN = textVN;
    }

    public int getId() {
        return id;
    }

    public String getTextE() {
        return textE;
    }

    public void setTextE(String textE) {
        this.textE = textE;
    }

    public String getTextVN() {
        return textVN;
    }

    public void setTextVN(String textVN) {
        this.textVN = textVN;
    }
}
