package com.example.block1processfileandstreams;

import lombok.Data;

@Data public class Person {
    private String name;
    private String town;
    private int edad = 0;

    public Person(String elemento, String elemento1, int parseInt) {
    }

    public static Person buildFromArray(String[] elementos){
        return new Person(elementos[0], elementos[1], Integer.parseInt(elementos[2]));
    }
}
