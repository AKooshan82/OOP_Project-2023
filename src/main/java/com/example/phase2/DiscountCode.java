package com.example.phase2;

public class DiscountCode {
    String name, percent;
    DiscountCode(String name, String percent){
        this.percent = percent;
        this.name = name;
    }

    public boolean stringEquals(String s){
        return this.name.equals(s);
    }
}
