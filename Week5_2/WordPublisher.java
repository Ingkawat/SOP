package com.example.week5_2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WordPublisher {
    protected Word words;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public WordPublisher() {
        this.words = new Word();
    }

    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.POST)
    public ArrayList<String> addBadWord(@PathVariable("word") String s) {
        this.words.badWords.add(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s) {
        this.words.badWords.remove(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.POST)
    public ArrayList<String> addGoodWord(@PathVariable("word") String s) {
        this.words.goodWords.add(s);
        return this.words.goodWords;
    }

    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s) {
        this.words.goodWords.remove(s);
        return this.words.goodWords;
    }

    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.POST)
    public void  proofSentence(@PathVariable("sentence") String s){
        int countBad = 0;
        int countGood = 0;
        for(String i : this.words.goodWords){
            if(s.indexOf(i) !=-1){
                countGood += 1;
            }
        }

        for(String i : this.words.badWords){
            if(s.indexOf(i) !=-1){
                countBad += 1;
            }
        }

        if((countGood > 0) && (countBad > 0)){
            rabbitTemplate.convertAndSend("Fanout", "", s);
        } else if (countGood > 0) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
        } else if (countBad > 0) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);
        }
    }

    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence(){

        return (Sentence) (rabbitTemplate.convertSendAndReceive("Direct", "get", ""));
    }
}
