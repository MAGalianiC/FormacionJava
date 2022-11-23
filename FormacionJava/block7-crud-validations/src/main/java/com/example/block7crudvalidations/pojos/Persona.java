package com.example.block7crudvalidations.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;
    @Column
    String usuario;
    @Column
    String password;
    @Column
    String name;
    @Column
    String surname;
    @Column
    String company_email;
    @Column
    String personal_email;
    @Column
    String city;
    @Column
    Boolean active;
    @Column
    Date created_date;
    @Column
    String imagen_url;
    @Column
    Date termination_date;
}
