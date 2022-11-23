package com.example.block7crudvalidations.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PersonaDTO implements Serializable {
    String idPersona;
    String usuario;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
}
