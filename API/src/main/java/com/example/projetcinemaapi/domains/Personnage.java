package com.example.projetcinemaapi.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "personnage")
@Getter
@Setter
@ToString
public class Personnage {
    @EmbeddedId
    private PersonnageId id;

    @MapsId("noFilm")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoFilm", nullable = false)
    private Film noFilm;

    @MapsId("noAct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoAct", nullable = false)
    private Acteur noAct;

    @Column(name = "NomPers", nullable = false, length = 30)
    private String nomPers;
}