package com.example.block1processfileandstreams;

import lombok.Data;

import java.util.random.RandomGenerator;

@Data public class Person {
    private String name;
    private String town;
    private Integer edad;

    public Person() {
        this.edad=0;
    }

    @Override
    public String toString(){
        String edadString = "Unknown";
        if (edad != null && edad != 0){
            edadString = edad.toString();
        }
        return "Name: " + name + ". Town: " + town + ". Age: " + edadString;
    }
}
