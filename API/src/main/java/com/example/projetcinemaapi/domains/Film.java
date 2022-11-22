package com.example.projetcinemaapi.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "film")
@Getter
@Setter
@ToString
public class Film {
    @Id
    @Column(name = "NoFilm", nullable = false)
    private Integer id;

    @Column(name = "Titre", nullable = false, length = 30)
    private String titre;

    @Column(name = "Duree", nullable = false)
    private Integer duree;

    @Column(name = "DateSortie", nullable = false)
    private LocalDate dateSortie;

    @Column(name = "Budget", nullable = false)
    private Integer budget;

    @Column(name = "MontantRecette", nullable = false)
    private Integer montantRecette;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoRea", nullable = false)
    private Realisateur noRea;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodeCat", nullable = false)
    private Categorie codeCat;
}