package com.example.projetcinemaapi.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime dateSortie;

    @Setter
    @Column(name = "Budget", nullable = false)
    private Integer budget;

    @Setter
    @Column(name = "MontantRecette", nullable = false)
    private Integer montantRecette;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NoRea", nullable = false)
    private Realisateur noRea;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CodeCat", nullable = false)
    private Categorie codeCat;
}