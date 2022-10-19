package com.example.block1processfileandstreams;

import lombok.Data;

import java.util.Optional;
import java.util.random.RandomGenerator;

@Data public class Person {
    private String name;
    private String town;
    private Integer edad;

    public Person() {
        this.edad=0;
    }

    public Optional<String> getTown(){
        return Optional.ofNullable(town);
    }

    public Optional<Integer> getEdad(){
        return Optional.ofNullable(edad);
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
