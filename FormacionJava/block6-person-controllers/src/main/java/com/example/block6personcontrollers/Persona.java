package com.example.block6personcontrollers;

import lombok.Data;

@Data
public class Persona {

    public String name;
    public Integer age;
    public String city;

    public Persona(String name, Integer age, String city){
        this.name =  name;
        this.age = age;
        this.city = city;
    }
}
