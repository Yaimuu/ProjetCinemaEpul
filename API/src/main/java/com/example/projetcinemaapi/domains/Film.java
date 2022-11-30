package com.example.projetcinemaapi.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NoFilm", nullable = false)
    private Integer id;

    @Setter
    @Column(name = "Titre", nullable = false, length = 30)
    private String titre;

    @Setter
    @Column(name = "Duree", nullable = false)
    private Integer duree;

    @Setter
    @Column(name = "DateSortie", nullable = false)
    private LocalDate dateSortie;

    @Setter
    @Column(name = "Budget", nullable = false)
    private Integer budget;

    @Setter
    @Column(name = "MontantRecette", nullable = false)
    private Integer montantRecette;

    @Setter
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NoRea", nullable = false)
    private Realisateur noRea;

    @Setter
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CodeCat", nullable = false)
    private Categorie codeCat;
}