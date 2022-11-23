package com.example.block7crud.Pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String name;
    @Column
    Integer age;
    @Column
    String population;

    @Override
    public String toString() {
        return  "{\n" +
                "id : " + id + ", " + "\n" +
                "name : " + name + "," + "\n" +
                "age : " + age + "," + "\n" +
                "population : " + population + "\n" +
                "}\n";
    }
}
