package com.example.projetcinemaapi.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "realisateur")
@Getter
@Setter
@ToString
public class Realisateur {
    @Id
    @Column(name = "NoRea", nullable = false)
    private Integer id;

    @Column(name = "NomRea", nullable = false, length = 20)
    private String nomRea;

    @Column(name = "PrenRea", nullable = false, length = 20)
    private String prenRea;
}