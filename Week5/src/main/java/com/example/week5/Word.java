package com.example.week5;

import java.util.ArrayList;

public class Word {
    public ArrayList<String> badWords;
    public ArrayList<String> goodWords;

    public Word() {
        this.badWords = new ArrayList();
        this.goodWords = new ArrayList();

        goodWords.add("happy");

        goodWords.add("enjoy");
        goodWords.add("like");

        badWords.add("fuck");
        badWords.add("olo");
    }
}
