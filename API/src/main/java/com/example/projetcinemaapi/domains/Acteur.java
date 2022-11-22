package com.example.projetcinemaapi.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "acteur")
@Getter
@Setter
@ToString
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NoAct", nullable = false)
    private Integer id;

    @Column(name = "NomAct", nullable = false, length = 20)
    private String nomAct;

    @Column(name = "PrenAct", length = 20)
    private String prenAct;

    @Column(name = "DateNaiss")
    private LocalDate dateNaiss;

    @Column(name = "DateDeces")
    private LocalDate dateDeces;
}