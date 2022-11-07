package com.example.block6personcontrollers;

import lombok.Data;

@Data
public class City {

    public String name;
    public Integer numHabitantes;

    public City(String name, Integer numHabitantes){
        this.name = name;
        this.numHabitantes = numHabitantes;
    }
}
