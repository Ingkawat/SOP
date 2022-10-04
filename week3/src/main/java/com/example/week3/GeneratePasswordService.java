package com.example.week3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class GeneratePasswordService {
    @RequestMapping(value = "/{name}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("name") String name){
        Random r = new Random();
        int num = r.nextInt(999999999);
        return "Hi," + name + "\n" + "Your new password is " + num + ".";
    }
}
