package com.example.week4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {
    @RequestMapping(value = "/getChange/{num}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("num") int num){
        Change c = new Change();
        c.setB1000(num/1000);
        num = num % 1000;
        c.setB500(num/500);
        num = num % 500;
        c.setB100(num/100);
        num = num % 100;
        c.setB20(num/20);
        num = num % 20;
        c.setB10(num/10);
        num = num % 10;
        c.setB5(num/5);
        num = num % 5;
        c.setB1(num);
        return c;
    }
}
